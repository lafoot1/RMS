package com.deloitte.rms.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.rms.api.Candidate;
import com.deloitte.rms.persistence.core.entity.CandidateEntity;
import com.deloitte.rms.persistence.mapper.AttachmentDetailMapper;
import com.deloitte.rms.persistence.mapper.CandidateMapper;
import com.deloitte.rms.persistence.repo.AttachmentDetailRepository;
import com.deloitte.rms.persistence.repo.CandidateRepository;

@Service
@Transactional
public class CandidateService {
	@Autowired CandidateRepository candidateRepository;
	@Autowired AttachmentDetailRepository attachmentDetailRepository;

	@Autowired CandidateMapper candidateMapper;
	@Autowired AttachmentDetailMapper attachmentDetailMapper;

	public Candidate save(Candidate candidate) {
		CandidateEntity entity = candidateMapper.map(candidate);
		return candidateMapper.map(candidateRepository.save(entity), true);
	}

	public Candidate getCandidateDetail(Long candidateId) {
		Candidate candidate =  candidateMapper.map(candidateRepository.findOne(candidateId), true);
		candidate.setAttachments(attachmentDetailMapper.map(attachmentDetailRepository.findByReferenceId(candidateId), false));
		return candidate;
	}

	public Candidate update(Long id, Candidate candidate) {
		CandidateEntity entity = candidateRepository.findOne(id);
		entity = candidateMapper.map(candidate, entity);
		return candidateMapper.map(candidateRepository.save(entity), true);
	}
}
