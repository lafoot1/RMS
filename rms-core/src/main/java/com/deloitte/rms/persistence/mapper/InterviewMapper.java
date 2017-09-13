package com.deloitte.rms.persistence.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.rms.api.Interview;
import com.deloitte.rms.api.Interviewer;
import com.deloitte.rms.persistence.core.entity.InterviewEntity;

@Component
public class InterviewMapper {
	@Autowired CandidateMapper candidateMapper;
	@Autowired InterviewerMapper interviewerMapper;

	public Interview map(InterviewEntity entity) {
		Interview interview = new Interview();
		BeanUtils.copyProperties(entity, interview);
		interview.setCandidate(candidateMapper.map(entity.getCandidate(), true));

		Interviewer interviewer = new Interviewer();
		interviewerMapper.map(entity.getInterviewer(), interviewer);
		interview.setInterviewer(interviewer);
		return interview;
	}

	public InterviewEntity map(Interview interview) {
		InterviewEntity interviewEntity = new InterviewEntity();
		BeanUtils.copyProperties(interview, interviewEntity);
		return interviewEntity;
	}
}
