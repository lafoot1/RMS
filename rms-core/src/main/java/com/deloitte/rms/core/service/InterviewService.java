package com.deloitte.rms.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.rms.api.Interview;
import com.deloitte.rms.core.util.ClockUtil;
import com.deloitte.rms.core.util.PrincipalHelper;
import com.deloitte.rms.persistence.core.entity.InterviewEntity;
import com.deloitte.rms.persistence.core.entity.ReferralEntity;
import com.deloitte.rms.persistence.mapper.InterviewMapper;
import com.deloitte.rms.persistence.repo.InterviewRepository;
import com.deloitte.rms.persistence.repo.ReferralRepository;

@Service
public class InterviewService {
	@Autowired ReferralRepository referralRepository;
	@Autowired InterviewRepository interviewRepository;
	@Autowired InterviewMapper interviewMapper;
	@Autowired ClockUtil clockUtil;
	@Autowired PrincipalHelper contextUtil;

	public Interview initiateInterview(Interview interview) {
		ReferralEntity referral = referralRepository.findOne(interview.getReferralId());
		InterviewEntity interviewEntity = new InterviewEntity();
		interviewEntity = interviewMapper.map(interview);
		interviewEntity.setCandidate(referral.getCandidate());
		//interviewEntity.setCandidateId(referral.getCandidate().getId());
		/*interviewEntity.setInterviewDate(interview.getInterviewDate());
		interviewEntity.setInterviewType(interview.getInterviewType());
		interviewEntity.setReferralId(interview.getReferralId());*/
		interviewEntity.setCreatedTs(clockUtil.getCurrentTime());
		interviewEntity.setUpdatedTs(clockUtil.getCurrentTime());
		Long currentUserId = contextUtil.getCurrentUser().getId();
		interviewEntity.setCreatedBy(currentUserId);
		interviewEntity.setUpdatedBy(currentUserId);
		InterviewEntity savedEntity = interviewRepository.save(interviewEntity);
		return interviewMapper.map(savedEntity);
	}

	public Interview getInterviewDetail(Long interviewId) {
		return interviewMapper.map(interviewRepository.findOne(interviewId));
	}
}
