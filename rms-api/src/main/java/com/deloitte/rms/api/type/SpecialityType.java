package com.deloitte.rms.api.type;

public enum SpecialityType {
	TECH("technology"),
	OTHER("other");

	private String code;

	private SpecialityType(String code) {
		this.code = code;
	}
	
	public static SpecialityType getByCode(String code) {
		for (SpecialityType specialityType : SpecialityType.values()) {
			if (specialityType.getCode().equalsIgnoreCase(code)) {
				return specialityType;
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
