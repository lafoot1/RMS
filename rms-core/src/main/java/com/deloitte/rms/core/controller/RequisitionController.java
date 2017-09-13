package com.deloitte.rms.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.rms.api.PageResponse;
import com.deloitte.rms.api.Requisition;
import com.deloitte.rms.api.SearchRequest;
import com.deloitte.rms.core.service.RequisitionService;

@RestController
@RequestMapping("/rest/secured/requisition")
public class RequisitionController {
	@Autowired RequisitionService requisitionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Requisition getRequisition(@PathVariable Long id) {
		return requisitionService.getRequisitionById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public PageResponse<Requisition> getRequisitions(@RequestBody SearchRequest search) {
		return requisitionService.getRequisitions(search);
	} 
}
