package com.deloitte.rms.api.type;

import com.deloitte.rms.api.type.EnumDeserializer.GenderTypeDeserializer;
import com.deloitte.rms.api.type.EnumSerializer.GenderTypeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = GenderTypeDeserializer.class)
@JsonSerialize(using = GenderTypeSerializer.class)
public enum GenderType {
	MALE("male"),
	FEMALE("female"),
	NA("not-available");
	
	private String code;
	
	private GenderType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static GenderType getByCode(String code) {
		for (GenderType genderType : GenderType.values()) {
			if (genderType.getCode().equalsIgnoreCase(code)) {
				return genderType;
			}
		}
		
		return NA;
	}
}
