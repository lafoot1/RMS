package com.deloitte.rms.core.service.repo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.rms.persistence.core.entity.wf.WfMasterEntity;
import com.deloitte.rms.persistence.core.entity.wf.WfStatusEntity;
import com.deloitte.rms.persistence.repo.WorkflowMasterRepo;
import com.deloitte.rms.persistence.repo.WorkflowStatusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WorkflowRepoTest {
	@Autowired WorkflowMasterRepo routeRepo;
	@Autowired WorkflowStatusRepository statusRepo;

	@Test
	public void testGetRoutes() {
		List<String> expectedStatuses = new ArrayList<>(Arrays.asList("tech2_scheduled", "reschedule_tech1", "no_response", "tech1_reject", "reschedule_final"));
		List<WfMasterEntity> routes = routeRepo.findByWfStatusId(4L);
		routes.forEach(route-> Assert.assertTrue(expectedStatuses.remove(route.getTargetWfStatus().getWfSubstatus())));
		Assert.assertEquals(0, expectedStatuses.size());
	}

	@Test @Transactional
	public void testGetWfStatus() {
		WfStatusEntity entity = statusRepo.findOne(1L);
		Assert.assertTrue(!entity.getWfMasters().isEmpty());
		Assert.assertEquals("screening", entity.getWfMasters().iterator().next().getTargetWfStatus().getWfStatus());
	}

	@Test @Transactional
	public void testGetWfPaths() {
		WfStatusEntity entity = statusRepo.findOne(1L);
		Assert.assertTrue(!entity.getWfMasters().isEmpty());
		Assert.assertEquals("screening", entity.getWfMasters().iterator().next().getTargetWfStatus().getWfStatus());
	}
}
