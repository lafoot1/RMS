package com.deloitte.rms.api.type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = EnumDeserializer.PersonTypeDeserializer.class)
public enum PersonType {
	CANDATE("CAN"),
	REFERER("REF"),
	RECRUITER("REC"),
	INTERVIEWER("INT"),
	UNKNOWN("UNK");
	
	private String code;
	
	private PersonType(String type) {
		this.code = type;
	}
	
	public String getCode() {
		return code;
	}

	public static PersonType getByCode(String type) {
		for (PersonType personType : PersonType.values()) {
			if (personType.getCode().equalsIgnoreCase(type)) {
				return personType;
			}
		}
		
		return UNKNOWN;
	}
}
