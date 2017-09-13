package com.deloitte.rms.core.service.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deloitte.rms.api.Attachment;
import com.deloitte.rms.api.Referral;
import com.deloitte.rms.core.service.ReferralService;
import com.deloitte.rms.core.util.ExcelUtil;
import com.deloitte.rms.core.util.UnzipUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReferralServiceTest {

	@Autowired UnzipUtil unzipUtil;
	@Autowired ExcelUtil excelUtil;
	@Autowired ReferralService referralService;
	InputStream zip = null;
	InputStream excel = null;

	@Before
	public void setUp() {
		try {
			zip = new FileInputStream(new File("src/test/resources/data/ReferralsTest.zip"));
			excel = new FileInputStream(new File("src/test/resources/data/TestData1.xlsx"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReferralUpload() throws IOException {
		List<Referral> referrals = excelUtil.readReferrals(excel);
		List<Attachment> files = unzipUtil.unzip(zip);
		Collection<Referral> failedReferrals = referralService.saveReferral(referrals, files);
		Assert.assertEquals(85,  failedReferrals.size());
	}

	@Test
	public void testGetReferralDetail() {
		Referral referral = referralService.getReferralDetail(1L);
		Assert.assertNotNull(referral.getCandidate());
		Assert.assertTrue(referral.getCandidate().getReferrals().isEmpty());
	}

	@Test
	public void testUpdateWorkflow() {
		Referral referral = referralService.getReferralDetail(1L);
		long fromStatusId = referral.getWfStatus().getId();
		long targetWfStatusId = referral.getWfStatus().getTargetPaths().iterator().next().getId();
		referral.getWfStatus().setId(referral.getWfStatus().getTargetPaths().iterator().next().getId());
		Referral updated = referralService.update(referral);
		Assert.assertEquals(targetWfStatusId, updated.getWfStatus().getId().longValue());
		Assert.assertTrue(!updated.getWfHistories().isEmpty());
		Assert.assertEquals(fromStatusId, updated.getWfHistories().iterator().next().getFromWfStatus().getId().longValue());
		Assert.assertEquals(targetWfStatusId, updated.getWfHistories().iterator().next().getToWfStatus().getId().longValue());
		Assert.assertNotNull(updated.getWfHistories().iterator().next().getCreatedTs());
	}
}
