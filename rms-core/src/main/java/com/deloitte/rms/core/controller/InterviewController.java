package com.deloitte.rms.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.rms.api.Interview;
import com.deloitte.rms.core.service.InterviewService;

@RestController
@RequestMapping("/rest/secured/interview")
public class InterviewController {
	@Autowired InterviewService interviewService;
	
	@RequestMapping(value = "/initiate/{rmsId}", method = {RequestMethod.POST}, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Interview getAllCandidates(@PathVariable Long rmsId, @RequestBody Interview interview) {
		interview.setReferralId(rmsId);
		return interviewService.initiateInterview(interview);
	}
	
	@RequestMapping(value = "/{interviewId}", method = {RequestMethod.GET}, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Interview getAllCandidates(@PathVariable Long interviewId) {
		return interviewService.getInterviewDetail(interviewId);
	}
}
