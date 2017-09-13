package com.deloitte.rms.persistence.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.deloitte.rms.api.Referer;
import com.deloitte.rms.persistence.core.entity.RefererEntity;

@Component
public class ReferrerMapper {
	public Referer map(RefererEntity entity) {
		Referer referer = new Referer();
		BeanUtils.copyProperties(entity, referer);
		return referer;
	}
	
	public RefererEntity map(Referer referer) {
		RefererEntity entity = new RefererEntity();
		//entity.setType(PersonType.REFERER);
		BeanUtils.copyProperties(referer, entity);
		return entity;
	}
}
