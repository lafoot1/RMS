package com.deloitte.rms.persistence.converter;

import javax.persistence.AttributeConverter;

import org.springframework.stereotype.Component;

import com.deloitte.rms.api.type.ReferralStatus;

@Component
public class ReferralTypeConverter implements AttributeConverter<ReferralStatus, String> {
	@Override
	public String convertToDatabaseColumn(ReferralStatus attribute) {
		return attribute == null?null:attribute.getCode();
	}

	@Override
	public ReferralStatus convertToEntityAttribute(String code) {
		return ReferralStatus.getByCode(code);
	}
}
