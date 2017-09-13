package com.deloitte.rms.api;

import java.io.Serializable;

import com.deloitte.rms.api.type.FunctionType;
import com.deloitte.rms.api.type.ServiceLineType;
import com.deloitte.rms.api.type.SpecialityType;

public class Requisition implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String requisitionNo;
	private FunctionType function;
	private String functionDesc;
	private SpecialityType speciality;
	private String specialityDesc;
	private ServiceLineType serviceLine;
	private String serviceLineDesc;
	private String recruiterName;
	private String recruiterAsstName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRequisitionNo() {
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	public FunctionType getFunction() {
		return function;
	}
	public void setFunction(FunctionType function) {
		this.function = function;
	}
	public String getFunctionDesc() {
		return functionDesc;
	}
	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}
	public SpecialityType getSpeciality() {
		return speciality;
	}
	public void setSpeciality(SpecialityType speciality) {
		this.speciality = speciality;
	}
	public String getSpecialityDesc() {
		return specialityDesc;
	}
	public void setSpecialityDesc(String specialityDesc) {
		this.specialityDesc = specialityDesc;
	}
	public ServiceLineType getServiceLine() {
		return serviceLine;
	}
	public void setServiceLine(ServiceLineType serviceLine) {
		this.serviceLine = serviceLine;
	}
	public String getServiceLineDesc() {
		return serviceLineDesc;
	}
	public void setServiceLineDesc(String serviceLineDesc) {
		this.serviceLineDesc = serviceLineDesc;
	}
	public String getRecruiterName() {
		return recruiterName;
	}
	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}
	public String getRecruiterAsstName() {
		return recruiterAsstName;
	}
	public void setRecruiterAsstName(String recruiterAsstName) {
		this.recruiterAsstName = recruiterAsstName;
	}
}
