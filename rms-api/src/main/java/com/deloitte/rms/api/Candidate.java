package com.deloitte.rms.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.deloitte.rms.api.type.GenderType;

public class Candidate extends Person {
	private static final long serialVersionUID = 1L;

	private Long rmsId;
	private String mobile;
	private String currentStatus;

	private GenderType gender;
	private String genderDesc;
	private String currentFirm;
	private String currentDesignation;
	private String currentLocation;
	private String preferredLocation;
	private String totalExperience;
	private String relevantExperience;
	private String currentCtc;
	private String expectedCtc;
	private String noticePeriod;
	private String visaStatus;
	private List<Skill> skillSet;
	private int experience;
	private List<Attachment> attachments;
	private Calendar applicationCreationDate;
	private Calendar referenceDate;
	private Calendar newReferenceDate;
	private List<Referral> referrals;

	public Long getRmsId() {
		return rmsId;
	}
	public void setRmsId(Long rmsId) {
		this.rmsId = rmsId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public List<Attachment> getAttachments() {
		if (attachments == null) {
			attachments = new ArrayList<>();
		}
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	public List<Skill> getSkillSet() {
		if (skillSet == null) {
			skillSet = new ArrayList<>();
		}

		return skillSet;
	}
	public void setSkillSet(List<Skill> skillSet) {
		this.skillSet = skillSet;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public Calendar getApplicationCreationDate() {
		return applicationCreationDate;
	}
	public void setApplicationCreationDate(Calendar applicationCreationDate) {
		this.applicationCreationDate = applicationCreationDate;
	}
	public Calendar getReferenceDate() {
		return referenceDate;
	}
	public void setReferenceDate(Calendar referenceDate) {
		this.referenceDate = referenceDate;
	}
	public Calendar getNewReferenceDate() {
		return newReferenceDate;
	}
	public void setNewReferenceDate(Calendar newReferenceDate) {
		this.newReferenceDate = newReferenceDate;
	}
	public GenderType getGender() {
		return gender;
	}
	public void setGender(GenderType gender) {
		this.gender = gender;
	}
	public String getGenderDesc() {
		return genderDesc;
	}
	public void setGenderDesc(String genderDesc) {
		this.genderDesc = genderDesc;
	}
	public String getCurrentFirm() {
		return currentFirm;
	}
	public void setCurrentFirm(String currentFirm) {
		this.currentFirm = currentFirm;
	}
	public String getCurrentDesignation() {
		return currentDesignation;
	}
	public void setCurrentDesignation(String currentDesignation) {
		this.currentDesignation = currentDesignation;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getPreferredLocation() {
		return preferredLocation;
	}
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}
	public String getTotalExperience() {
		return totalExperience;
	}
	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}
	public String getRelevantExperience() {
		return relevantExperience;
	}
	public void setRelevantExperience(String relevantExperience) {
		this.relevantExperience = relevantExperience;
	}
	public String getCurrentCtc() {
		return currentCtc;
	}
	public void setCurrentCtc(String currentCtc) {
		this.currentCtc = currentCtc;
	}
	public String getExpectedCtc() {
		return expectedCtc;
	}
	public void setExpectedCtc(String expectedCtc) {
		this.expectedCtc = expectedCtc;
	}
	public String getNoticePeriod() {
		return noticePeriod;
	}
	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}
	public String getVisaStatus() {
		return visaStatus;
	}
	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
	}
	public List<Referral> getReferrals() {
		if (referrals == null) {
			referrals = new ArrayList<>();
		}

		return referrals;
	}
	public void setReferrals(List<Referral> referrals) {
		this.referrals = referrals;
	}
}
