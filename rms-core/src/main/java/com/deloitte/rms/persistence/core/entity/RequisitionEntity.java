package com.deloitte.rms.persistence.core.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.deloitte.rms.api.type.FunctionType;
import com.deloitte.rms.api.type.ServiceLineType;
import com.deloitte.rms.api.type.SpecialityType;

@Entity
@Table(name = "requisition")
public class RequisitionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String requisitionNo;
	@Enumerated(EnumType.STRING)
	private FunctionType function;
	@Enumerated(EnumType.STRING)
	private SpecialityType speciality;
	@Enumerated(EnumType.STRING)
	private ServiceLineType serviceLine;
	private String recruiterName;
	private String recruiterAsstName;
	@OneToMany(mappedBy = "requisition")
	private Set<ReferralEntity> referrals;
	
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
	public SpecialityType getSpeciality() {
		return speciality;
	}
	public void setSpeciality(SpecialityType speciality) {
		this.speciality = speciality;
	}
	public ServiceLineType getServiceLine() {
		return serviceLine;
	}
	public void setServiceLine(ServiceLineType serviceLine) {
		this.serviceLine = serviceLine;
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
	public Set<ReferralEntity> getReferrals() {
		return referrals;
	}
	public void setReferrals(Set<ReferralEntity> referrals) {
		this.referrals = referrals;
	}
}
