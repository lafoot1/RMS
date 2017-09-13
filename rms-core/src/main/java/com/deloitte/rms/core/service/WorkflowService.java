package com.deloitte.rms.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.rms.core.exception.RmsWorkflowException;
import com.deloitte.rms.persistence.core.entity.ReferralEntity;
import com.deloitte.rms.persistence.core.entity.wf.WfMasterEntity;
import com.deloitte.rms.persistence.repo.ReferralRepository;
import com.deloitte.rms.persistence.repo.WorkflowMasterRepo;
import com.deloitte.rms.persistence.repo.WorkflowStatusRepository;

@Service
public class WorkflowService {
	@Autowired WorkflowStatusRepository workflowStatusRepository;
	@Autowired WorkflowMasterRepo workflowMasterRepo;
	@Autowired ReferralRepository referralRepository;

	@Transactional
	public void updateStatus(Long id, Long statusId) throws RmsWorkflowException {
		ReferralEntity entity = referralRepository.findOne(id);
		boolean allow = false;
		List<WfMasterEntity> wfMasters = workflowMasterRepo.findByWfStatusId(entity.getWfStatus().getId());
		for (WfMasterEntity wfMaster : wfMasters) {
			if (statusId == wfMaster.getTargetWfStatusId()) {
				allow = true;
				break;
			}
		}
		if (!allow) {
			throw new RmsWorkflowException("incorrect status");
		}

		entity.setWfStatus(workflowStatusRepository.findOne(statusId));
	}
}
