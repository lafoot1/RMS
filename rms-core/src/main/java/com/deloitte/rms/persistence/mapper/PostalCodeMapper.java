package com.deloitte.rms.persistence.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.deloitte.rms.api.reference.PostalCode;
import com.deloitte.rms.persistence.core.entity.wf.PostalCodeEntity;

@Component
public class PostalCodeMapper {

	public PostalCode map(PostalCodeEntity entity) {
		PostalCode postalCode = new PostalCode();
		BeanUtils.copyProperties(entity, postalCode);
		return postalCode;
	}
}
