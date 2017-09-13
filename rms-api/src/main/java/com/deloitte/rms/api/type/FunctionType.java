package com.deloitte.rms.api.type;

import com.deloitte.rms.api.type.EnumDeserializer.FunctionTypeDeserializer;
import com.deloitte.rms.api.type.EnumSerializer.FunctionTypeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = FunctionTypeDeserializer.class)
@JsonSerialize(using = FunctionTypeSerializer.class)
public enum FunctionType {
	CONSULTING("consulting"),
	OTHER("other");
	
	private String code;
	
	private FunctionType(String desc) {
		this.code = desc;
	}
	
	public static FunctionType getByCode(String code) {
		for (FunctionType functionType : FunctionType.values()) {
			if (functionType.getCode().equalsIgnoreCase(code)) {
				return functionType;
			}
		}
		
		return OTHER;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String desc) {
		this.code = desc;
	}
}
