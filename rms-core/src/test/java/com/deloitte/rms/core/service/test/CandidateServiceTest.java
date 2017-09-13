package com.deloitte.rms.core.service.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deloitte.rms.api.Candidate;
import com.deloitte.rms.core.service.CandidateService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CandidateServiceTest {

	@Autowired CandidateService candidateService;

	@Test @Ignore
	public void testGetInterview() {
		Candidate candidate = candidateService.getCandidateDetail(5L);
		Assert.assertNotNull(candidate.getReferrals());
	}
}
