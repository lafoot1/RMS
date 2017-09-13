package com.deloitte.rms.api;

import java.io.Serializable;

public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String code;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String skill) {
		this.code = skill;
	}
}
