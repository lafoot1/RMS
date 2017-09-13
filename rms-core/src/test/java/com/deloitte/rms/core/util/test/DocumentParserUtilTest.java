package com.deloitte.rms.core.util.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deloitte.rms.api.Attachment;
import com.deloitte.rms.api.Candidate;
import com.deloitte.rms.core.util.DocumentParserUtil;
import com.deloitte.rms.core.util.ExcelUtil;
import com.deloitte.rms.core.util.UnzipUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DocumentParserUtilTest {
	private static final Logger LOG = Logger.getLogger(DocumentParserUtilTest.class);
	@Autowired UnzipUtil unzipUtil;
	@Autowired DocumentParserUtil docUtil;
	@Autowired ExcelUtil excelUtil;
	List<Attachment> attachments;
	final Map<String, Candidate> candidateMap = new HashMap<>();

	@Before
	public void setUp() {
		try {
			InputStream is = new FileInputStream(new File("src/test/resources/data/ReferralsTest.zip"));
			attachments = unzipUtil.unzip(is);

			FileInputStream fis = new FileInputStream(new File("src/test/resources/data/TestData1.xlsx"));
			excelUtil.readReferrals(fis).forEach(referral -> candidateMap.put(referral.getCandidate().getEmail(), referral.getCandidate()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testRetrieveEmail() {
		int mobileCtr = 0;
		for (Attachment attachment : attachments) {
			docUtil.updateCandidateRecords(attachment, candidateMap);
		}

		for (Candidate candidate : candidateMap.values()) {
			if (StringUtils.isNotBlank(candidate.getMobile()) && candidate.getMobile().length() > 6) {
				mobileCtr++;
			}
		};

		LOG.trace("Mobile Extracted: "+ mobileCtr);
		float successRate = (float)mobileCtr*100/candidateMap.size();
		LOG.trace("success Rate: "+ successRate);
		Assert.assertTrue(successRate > 80);
	}
}
