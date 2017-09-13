package com.deloitte.rms.persistence.mapper;

import org.springframework.beans.BeanUtils;

import com.deloitte.rms.api.Person;
import com.deloitte.rms.persistence.core.entity.PersonEntity;

public abstract class PersonMapper<U extends Person, V extends PersonEntity> {
	public V map(U source, V target) {
		if (source == null) {
			return target;
		}
		
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	public U map(V source, U target) {
		if (source == null) {
			return target;
		}
		
		BeanUtils.copyProperties(source, target);
		return target;
	}
}
