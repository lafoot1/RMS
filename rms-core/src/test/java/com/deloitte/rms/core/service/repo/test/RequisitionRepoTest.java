package com.deloitte.rms.core.service.repo.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deloitte.rms.persistence.core.entity.RequisitionEntity;
import com.deloitte.rms.persistence.repo.RequisitionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RequisitionRepoTest {
	private static final Pageable p = new PageRequest(0,10);
	@Autowired RequisitionRepository repo;
	
	@Test
	public void testGlobalSearchByRecruiterName() {
		String recruiterName = "durg";
		Page<RequisitionEntity> page = repo.globalSearch(-1L, recruiterName, p);
		Assert.assertTrue(!page.getContent().isEmpty());
		RequisitionEntity fElem = page.getContent().iterator().next();
		Assert.assertTrue(fElem.getRecruiterName().toUpperCase().contains(recruiterName.toUpperCase()));
	}
	
	@Test
	public void testGlobalSearchByRequisitionNo() {
		String recruiterName = "E18HCC";
		Page<RequisitionEntity> page = repo.globalSearch(-1L, recruiterName, p);
		Assert.assertTrue(!page.getContent().isEmpty());
		RequisitionEntity fElem = page.getContent().iterator().next();
		Assert.assertTrue(fElem.getRequisitionNo().toUpperCase().contains(recruiterName.toUpperCase()));
	}
}
