package com.deloitte.rms.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.rms.api.Attachment;
import com.deloitte.rms.api.Candidate;
import com.deloitte.rms.api.PageResponse;
import com.deloitte.rms.api.ReferenceType;
import com.deloitte.rms.api.Referral;
import com.deloitte.rms.api.SearchRequest;
import com.deloitte.rms.api.Skill;
import com.deloitte.rms.core.util.DocumentParserUtil;
import com.deloitte.rms.persistence.core.entity.CandidateEntity;
import com.deloitte.rms.persistence.core.entity.RefererEntity;
import com.deloitte.rms.persistence.core.entity.ReferralEntity;
import com.deloitte.rms.persistence.core.entity.SkillEntity;
import com.deloitte.rms.persistence.core.entity.wf.WfHistoryEntity;
import com.deloitte.rms.persistence.core.entity.wf.WfMasterEntity;
import com.deloitte.rms.persistence.core.entity.wf.WfStatusEntity;
import com.deloitte.rms.persistence.mapper.AttachmentDetailMapper;
import com.deloitte.rms.persistence.mapper.ReferralMapper;
import com.deloitte.rms.persistence.mapper.RequisitionMapper;
import com.deloitte.rms.persistence.mongo.entity.AttachmentDetailEntity;
import com.deloitte.rms.persistence.repo.AttachmentDetailRepository;
import com.deloitte.rms.persistence.repo.CandidateRepository;
import com.deloitte.rms.persistence.repo.ReferralRepository;
import com.deloitte.rms.persistence.repo.ReferrerRepository;
import com.deloitte.rms.persistence.repo.RequisitionRepository;
import com.deloitte.rms.persistence.repo.SkillRepository;
import com.deloitte.rms.persistence.repo.WorkflowMasterRepo;
import com.deloitte.rms.persistence.repo.WorkflowStatusRepository;

@Service
public class ReferralService {
	@Autowired ReferralRepository referralRepo;
	@Autowired ReferrerRepository referrerRepository;
	@Autowired CandidateRepository candidateRepository;
	@Autowired AttachmentDetailRepository attachmentDetailRepository;
	@Autowired CandidateRepository candidateRepo;
	@Autowired SkillRepository skillRepository;
	@Autowired RequisitionRepository requisitionRepository;
	@Autowired WorkflowMasterRepo workflowMasterRepo;
	@Autowired WorkflowStatusRepository workflowStatusRepository;

	@Autowired AttachmentService attachmentService;
	@Autowired CandidateService candidateService;

	@Autowired ReferralMapper referralMapper;
	@Autowired AttachmentDetailMapper attachmentDetailMapper;
	@Autowired RequisitionMapper requisitionMapper;

	@Autowired DocumentParserUtil documentParserUtil;

	public List<Referral> saveReferral(Collection<Referral> referrals, Collection<Attachment> files) {
		if (referrals == null || referrals.isEmpty()) {
			return Collections.emptyList();
		}

		final Map<String, Candidate> candidateMap = new HashMap<>();
		referrals.forEach(referral -> candidateMap.put(referral.getCandidate().getEmail(), referral.getCandidate()));
		files.forEach(attachment -> documentParserUtil.updateCandidateRecords(attachment, candidateMap));

		List<Referral> fails = new ArrayList<>();
		referrals.forEach(referral -> {
			try {
				save(referral);
			}catch(Exception ex) {
				Candidate candidate = new Candidate();
				candidate.setRmsId(referral.getCandidate().getRmsId());
				referral.setCandidate(candidate);
				referral.setRequisition(null);
				referral.setReferrer(null);
				fails.add(referral);
				ex.printStackTrace();
			}
		});
		return fails;
	}

	@Transactional
	private Referral save(Referral referral) {
		ReferralEntity entity = referralMapper.map(referral);
		WfStatusEntity wfStatusEntity = new WfStatusEntity();
		wfStatusEntity.setId(1L);
		entity.setWfStatus(wfStatusEntity);
		Example<RefererEntity> example = Example.of(entity.getReferrer());
		if (!referrerRepository.exists(example)) {
			RefererEntity referrer = referrerRepository.save(entity.getReferrer());
			entity.setReferrer(referrer);
		} else {
			entity.setReferrer(referrerRepository.findOne(example));
		}

		Set<SkillEntity> skills = new HashSet<>();
		for (Skill  skill : referral.getCandidate().getSkillSet()) {
			Set<SkillEntity> set = skillRepository.findByCodeIgnoreCaseContaining(skill.getCode());
			if (!set.isEmpty()) {
				skills.add(set.iterator().next());
			} else {
				SkillEntity skillEntity = new SkillEntity();
				skillEntity.setCode(skill.getCode());
				skills.add(skillEntity);
				skillRepository.save(skillEntity);
			}
		}
		entity.getCandidate().setSkills(skills);
		entity.setWfStatus(workflowStatusRepository.findOne(1L));
		ReferralEntity savedEntity = referralRepo.save(entity);
		referral.getCandidate().getAttachments()
			.forEach(attachment ->  {
				attachment.setReferenceId(entity.getCandidate().getId());
				attachment.setReferenceType(ReferenceType.CANDIDATE);
			});

		if (!referral.getCandidate().getAttachments().isEmpty()) {
			Collection<Attachment> attachments = referral.getCandidate().getAttachments();
			List<AttachmentDetailEntity> entities = new ArrayList<>();
			attachments.forEach(attachment -> entities.add(attachmentDetailMapper.map(attachment)));
			List<AttachmentDetailEntity> savedAttachmentDetails = attachmentDetailRepository.save(entities);

			CandidateEntity candidateEntity = entity.getCandidate();
			referral.getCandidate().setAttachments(attachmentDetailMapper.map(savedAttachmentDetails, false));
			candidateRepository.save(candidateEntity);

			WfMasterEntity targetStatusEntity = entity.getWfStatus().getWfMasters().iterator().next();
			entity.setWfStatus(targetStatusEntity.getTargetWfStatus());
			referralRepo.save(entity);
		}

		return referralMapper.map(savedEntity, true);
	}

	public PageResponse<Referral> getReferrals(SearchRequest search) {
		Pageable pageable = new PageRequest(search.getPageNo(), search.getPageSize());
		List<Referral> referrals = new ArrayList<>(search.getPageSize());
		Page<ReferralEntity> referralPage = null;

		if (StringUtils.isBlank(search.getQuery())) {
			referralPage = referralRepo.findAll(pageable);
			referralPage.forEach(referral -> referrals.add(referralMapper.map(referral, true)));
		} else {
			Long idQuery = NumberUtils.toLong(search.getQuery(), -1);
			Set<String> statuses = Collections.singleton(search.getQuery());
			Set<String> subStatuses = Collections.singleton(search.getQuery());
			referralPage = referralRepo.globalSearch(
					idQuery,
					statuses.isEmpty()?null:statuses,
					subStatuses.isEmpty()?null:subStatuses,
					search.getQuery(),
					pageable
			);
			referralPage.forEach(referral -> referrals.add(referralMapper.map(referral, true)));
		}

		return new PageResponse<Referral>(referrals, referralPage);
	}

	@Transactional(readOnly = true)
	public Referral getReferralDetail(Long rmsId) {
		Referral referral = referralMapper.map(referralRepo.findOne(rmsId), true);
		Collection<AttachmentDetailEntity> attachmentDetails = attachmentDetailRepository.findByReferenceId(referral.getCandidate().getId());
		referral.getCandidate().setAttachments(attachmentDetailMapper.map(attachmentDetails, false));
		return referral;
	}

	@Transactional
	public Referral update(Referral referral) {
		ReferralEntity existing = referralRepo.findOne(referral.getId());
		ReferralEntity entity = referralMapper.map(referral);

		if (existing.getWfStatus().getId() != entity.getWfStatus().getId()) {
			//status change
			WfHistoryEntity history = new WfHistoryEntity();
			history.setFromWfStatus(existing.getWfStatus());
			history.setToWfStatus(entity.getWfStatus());
			history.setAction(referral.getWfActionType());
			history.setComment(referral.getWfComment());
			history.setReference(entity);
			entity.getWfHistories().add(history);
		}

		ReferralEntity savedEntity = referralRepo.save(entity);
		return referralMapper.map(savedEntity, true);
	}
}
