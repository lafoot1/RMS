package com.deloitte.rms.api.type;

import com.deloitte.rms.api.type.EnumDeserializer.InterviewModeTypeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = InterviewModeTypeDeserializer.class)
public enum InterviewModeType {
	TELEPHONIC("telephonic"),
	INPERSON("inperson"),
	OTHER("other");
	
	private String code;
	
	private InterviewModeType(String code) {
		this.code = code;
	}
	
	public static InterviewModeType getByCode(String code) {
		for (InterviewModeType interviewType : InterviewModeType.values()) {
			if (interviewType.getCode().equalsIgnoreCase(code)) {
				return interviewType;
			}
		}
		
		return OTHER;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
