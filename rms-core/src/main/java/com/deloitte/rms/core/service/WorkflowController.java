package com.deloitte.rms.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.rms.api.Referral;
import com.deloitte.rms.api.workflow.WfHistory;
import com.deloitte.rms.core.exception.RmsWorkflowException;

@RestController
@RequestMapping("/rest/secured/workflow")
public class WorkflowController {
	@Autowired WorkflowService workflowService;
	@Autowired ReferralService referralService;

	@ExceptionHandler(RmsWorkflowException.class)
	@RequestMapping(value = "/{referralId}", method = RequestMethod.GET)
	public Referral updateWorkflowStatus(@PathVariable Long referralId,
			@RequestParam(name = "statusId") Long statusId, @RequestBody WfHistory history) throws RmsWorkflowException {
		workflowService.updateStatus(referralId, statusId);
		return referralService.getReferralDetail(referralId);
	}
}
