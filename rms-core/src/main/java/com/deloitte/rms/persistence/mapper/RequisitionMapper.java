package com.deloitte.rms.persistence.mapper;

import static com.deloitte.rms.core.config.RmsMessages.FUNCTION_PREFIX;
import static com.deloitte.rms.core.config.RmsMessages.SERVICELINE_PREFIX;
import static com.deloitte.rms.core.config.RmsMessages.SPECIALITY_PREFIX;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.rms.api.Requisition;
import com.deloitte.rms.core.config.RmsMessages;
import com.deloitte.rms.persistence.core.entity.RequisitionEntity;

@Component
public class RequisitionMapper {
	@Autowired RmsMessages messages;
	
	public Requisition map(RequisitionEntity entity) {
		Requisition requisition = new Requisition();
		BeanUtils.copyProperties(entity, requisition);
		requisition.setSpeciality(entity.getSpeciality());
		requisition.setSpecialityDesc(messages.getPropertyValue(SPECIALITY_PREFIX + entity.getSpeciality().getCode()));
		requisition.setFunction(entity.getFunction());
		requisition.setFunctionDesc(messages.getPropertyValue(FUNCTION_PREFIX + entity.getFunction().getCode()));
		requisition.setServiceLine(entity.getServiceLine());
		requisition.setServiceLineDesc(messages.getPropertyValue(SERVICELINE_PREFIX + entity.getServiceLine().getCode()));
		return requisition;
	}
	
	public RequisitionEntity map(Requisition requisition) {
		RequisitionEntity entity = new RequisitionEntity();
		BeanUtils.copyProperties(requisition, entity);
		return entity;
	}
}
