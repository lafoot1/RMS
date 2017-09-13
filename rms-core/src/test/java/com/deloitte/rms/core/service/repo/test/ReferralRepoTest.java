package com.deloitte.rms.core.service.repo.test;

import java.util.Collections;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deloitte.rms.api.type.ReferralStatus;
import com.deloitte.rms.api.type.ReferralSubstatus;
import com.deloitte.rms.persistence.core.entity.ReferralEntity;
import com.deloitte.rms.persistence.repo.ReferralRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReferralRepoTest {
	private static final Logger LOG = Logger.getLogger(ReferralRepoTest.class);
	private static final Pageable p = new PageRequest(0,10);;
	@Autowired ReferralRepository repo;

	@Test
	public void testGlobalSearchByRmsId() {
		Page<ReferralEntity> page = repo.globalSearch(20943810l, null, null, null, p);
		Assert.assertTrue(!page.getContent().isEmpty());
		Assert.assertEquals(20943810l, page.getContent().iterator().next().getCandidate().getRmsId().longValue());
	}

	@Test
	public void testGlobalSearchByName() {
		String query = "pav";
		Page<ReferralEntity> page = repo.globalSearch(-1l, null, null, query, p);
		Assert.assertTrue(!page.getContent().isEmpty());
		Assert.assertTrue(page.getContent().iterator().next().getCandidate().getFirstName().toUpperCase().contains(query.toUpperCase()));
	}

	@Test
	public void testGlobalSearchByRecruiterName() {
		String query = "urva";
		Page<ReferralEntity> page = repo.globalSearch(-1l, null, null, query, p);
		Assert.assertTrue(!page.getContent().isEmpty());
		ReferralEntity firstElem = page.getContent().iterator().next();
		LOG.debug("Recruiter Name: "+ firstElem.getRequisition().getRecruiterName());
		Assert.assertTrue(firstElem.getRequisition().getRecruiterName().toUpperCase().contains(query.toUpperCase()));
	}

	@Test
	public void testGlobalSearchByStatus() {
		Page<ReferralEntity> page = repo.globalSearch(-1L, Collections.singleton(ReferralStatus.NEW.getCode()), null, null, p);
		Assert.assertTrue(!page.getContent().isEmpty());
		ReferralEntity firstElem = page.getContent().iterator().next();
		LOG.debug("Recruiter Name: "+ firstElem.getRequisition().getRecruiterName());
	}

	@Test
	public void testGlobalSearchBySubStatus() {
		Page<ReferralEntity> page = repo.globalSearch(-1l, null, Collections.singleton(ReferralSubstatus.TECH1_SCHEDULED.getCode()), null, p);
		Assert.assertTrue(!page.getContent().isEmpty());
		ReferralEntity firstElem = page.getContent().iterator().next();
		LOG.debug("Recruiter Name: "+ firstElem.getRequisition().getRecruiterName());
	}
}
