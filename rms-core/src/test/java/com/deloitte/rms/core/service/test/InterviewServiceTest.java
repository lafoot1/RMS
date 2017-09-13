package com.deloitte.rms.core.service.test;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deloitte.rms.api.Interview;
import com.deloitte.rms.api.Interviewer;
import com.deloitte.rms.api.type.InterviewModeType;
import com.deloitte.rms.api.type.InterviewType;
import com.deloitte.rms.core.service.InterviewService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InterviewServiceTest {

	@Autowired InterviewService interviewService;

	@Test @Ignore
	public void initiateInterview() {
		Long rmsId = 1L;
		Calendar interviewDate = Calendar.getInstance();
		interviewDate.set(Calendar.YEAR, 2017);
		interviewDate.set(Calendar.MONTH, Calendar.SEPTEMBER);
		interviewDate.set(Calendar.DATE, 22);
		Interview interview = new Interview();
		interview.setInterviewType(InterviewType.TECH1);
		interview.setInterviewModeType(InterviewModeType.TELEPHONIC);
		interview.setInterviewDate(interviewDate);
		interview.setReferralId(rmsId);
		Interviewer interviewer = new Interviewer();
		interviewer.setId(3L);
		interview.setInterviewer(interviewer);
		Interview savedInterview = interviewService.initiateInterview(interview);
		Assert.assertNotNull(savedInterview.getInterviewType());
	}

	@Test @Ignore
	public void testGetInterview() {
		Interview interview = interviewService.getInterviewDetail(1L);
		Assert.assertNotNull(interview.getInterviewer());
		Assert.assertNotNull(interview.getInterviewDate());
		Assert.assertNotNull(interview.getInterviewModeType());
	}
}
