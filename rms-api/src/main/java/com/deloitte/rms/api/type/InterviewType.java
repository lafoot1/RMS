package com.deloitte.rms.api.type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = EnumDeserializer.InterviewTypeDeserializer.class)
public enum InterviewType {
	TECH1("tech1"),
	TECH2("tech2"),
	VERSANT("versant"),
	FINAL("final"),
	OTHER("other");
	
	private String code;
	
	private InterviewType(String code) {
		this.code = code;
	}
	
	public static InterviewType getByDesc(String code) {
		for (InterviewType interviewType : InterviewType.values()) {
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
