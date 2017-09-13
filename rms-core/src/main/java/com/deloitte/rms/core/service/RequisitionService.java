package com.deloitte.rms.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deloitte.rms.api.PageResponse;
import com.deloitte.rms.api.Requisition;
import com.deloitte.rms.api.SearchRequest;
import com.deloitte.rms.persistence.core.entity.RequisitionEntity;
import com.deloitte.rms.persistence.mapper.RequisitionMapper;
import com.deloitte.rms.persistence.repo.RequisitionRepository;

@Service
public class RequisitionService {
	@Autowired RequisitionRepository requisitionRepository;
	@Autowired RequisitionMapper requisitionMapper;
	
	public Requisition getRequisitionById(Long id) {
		return requisitionMapper.map(requisitionRepository.findOne(id));
	}
	
	public PageResponse<Requisition> getRequisitions(SearchRequest search) {
		Pageable pageable = new PageRequest(search.getPageNo(), search.getPageSize());
		List<Requisition> requisitions = new ArrayList<>(search.getPageSize());
		Page<RequisitionEntity> requisitionPage = null;
		
		if (StringUtils.isBlank(search.getQuery())) {
			requisitionPage = requisitionRepository.findAll(pageable); 
			requisitionPage.forEach(requisition -> requisitions.add(requisitionMapper.map(requisition)));
		} else {
			Long idQuery = NumberUtils.toLong(search.getQuery(), -1);
			requisitionPage = requisitionRepository.globalSearch(idQuery, search.getQuery(),pageable);
			requisitionPage.forEach(requisition -> requisitions.add(requisitionMapper.map(requisition)));
		}
		
		return new PageResponse<Requisition>(requisitions, requisitionPage);
	}
}
