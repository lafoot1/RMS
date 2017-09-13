package com.deloitte.rms.api;

public enum ReferenceType {
	CANDIDATE("CAN"),
	UNKNOWN("UNK");
	
	private String code;
	
	private ReferenceType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static ReferenceType getByCode(String code) {
		if (code == null) {
			return UNKNOWN;
		}
		for (ReferenceType type : ReferenceType.values()) {
			if (type.getCode().equalsIgnoreCase(code)) {
				return type;
			}
		}
		
		return UNKNOWN;
	}
}
