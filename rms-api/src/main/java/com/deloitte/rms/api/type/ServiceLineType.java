package com.deloitte.rms.api.type;

public enum ServiceLineType {
	DD("dd", "Deloitte Digital"), 
	AMS("ams", "Application Management System"),
	SI("si", "Systems Integration"),
	OTHER("other", "Other");

	private String code;
	private String desc;

	private ServiceLineType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static ServiceLineType getByCode(String code) {
		for (ServiceLineType serviceLineType : ServiceLineType.values()) {
			if (serviceLineType.getCode().equalsIgnoreCase(code)) {
				return serviceLineType;
			}
		}
		
		return OTHER;
	}
	
	public static ServiceLineType getByDesc(String desc) {
		for (ServiceLineType serviceLineType : ServiceLineType.values()) {
			if (serviceLineType.getDesc().equalsIgnoreCase(desc)) {
				return serviceLineType;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
