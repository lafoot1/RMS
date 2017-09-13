package com.deloitte.rms.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.rms.api.Candidate;
import com.deloitte.rms.core.service.CandidateService;

@RestController
@RequestMapping("/rest/secured/candidate")
public class CandidateController {
	@Autowired CandidateService candidateService;

	@RequestMapping(value = "/{id}", method = {RequestMethod.GET})
	public Candidate getCandidate(@PathVariable Long id) {
		return candidateService.getCandidateDetail(id);
	}
	
	@RequestMapping(value="/{id}", method = {RequestMethod.PUT})
	public Candidate save(@PathVariable Long id, @RequestBody Candidate candidate) {
		return candidateService.update(id, candidate);
	}
}
