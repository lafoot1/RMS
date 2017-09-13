package com.deloitte.rms.persistence.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.rms.api.Candidate;
import com.deloitte.rms.api.type.PersonType;
import com.deloitte.rms.core.config.RmsMessages;
import com.deloitte.rms.persistence.core.entity.CandidateEntity;

@Component
public class CandidateMapper extends PersonMapper<Candidate, CandidateEntity> {
	private static final String GENDER_MSG_PREFIX = "rms.gender.";
	@Autowired ReferralMapper referralMapper;
	@Autowired SkillMapper skillMapper;
	@Autowired RmsMessages rmsMessages;

	public List<CandidateEntity> mapCandidateEntities(Collection<Candidate> candidates) {
		if (candidates == null || candidates.isEmpty()) {
			return Collections.emptyList();
		}

		List<CandidateEntity> entities = new ArrayList<>(candidates.size());
		candidates.forEach(candidate->entities.add(map(candidate)));
		return entities;
	}

	public CandidateEntity map(Candidate candidate) {
		CandidateEntity entity = new CandidateEntity();
		candidate.setType(PersonType.CANDATE);
		BeanUtils.copyProperties(candidate, entity);
		super.map(candidate, entity);
		entity.setSkills(skillMapper.mapSkillEntities(candidate.getSkillSet()));
		return entity;
	}

	@Override
	public CandidateEntity map(Candidate candidate, CandidateEntity entity) {
		candidate.setType(PersonType.CANDATE);
		BeanUtils.copyProperties(candidate, entity);
		return super.map(candidate, entity);
	}

	public List<Candidate> mapCandidates(Collection<CandidateEntity> candidateEntities, boolean mapReferral) {
		List<Candidate> candidates = new ArrayList<>(candidateEntities.size());
		candidateEntities.forEach(entity->candidates.add(map(entity, mapReferral)));
		return candidates;
	}

	public Candidate map(CandidateEntity candidateEntity, boolean mapReferral) {
		Candidate candidate = new Candidate();
		BeanUtils.copyProperties(candidateEntity, candidate);
		super.map(candidateEntity, candidate);
		candidate.setReferrals(mapReferral?referralMapper.map(candidateEntity.getReferrals(), mapReferral):null);
		candidate.setSkillSet(skillMapper.map(candidateEntity.getSkills()));
		if (candidate.getGender() != null) {
			candidate.setGenderDesc(rmsMessages.getPropertyValue(GENDER_MSG_PREFIX + candidate.getGender().getCode()));
		}
		return candidate;
	}
}
