package com.deloitte.rms.api;

import java.io.Serializable;

import com.deloitte.rms.api.type.PersonType;

public abstract class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long empNo;
	private String firstName;
	private String lastName;
	private String email;
	private PersonType type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Long empNo) {
		this.empNo = empNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public PersonType getType() {
		return type;
	}
	public void setType(PersonType type) {
		this.type = type;
	}
}
