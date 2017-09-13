package com.deloitte.rms.persistence.core.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.deloitte.rms.api.type.GenderType;

@Entity
@DiscriminatorValue("CAN")
@SecondaryTable(name = "candidate", pkJoinColumns = @PrimaryKeyJoinColumn(name = "personId"))
@NamedEntityGraph(name = "CandidateEntity.detail", attributeNodes = @NamedAttributeNode("referrals"))
public class CandidateEntity extends PersonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(table = "candidate", unique = true)
	private Long rmsId;

	@Column(table = "candidate")
	private String mobile;

	@Column(table = "candidate")
	private GenderType gender;

	@Column(table = "candidate")
	private String currentFirm;

	@Column(table = "candidate")
	private String currentDesignation;

	@Column(table = "candidate")
	private String currentLocation;

	@Column(table = "candidate")
	private String preferredLocation;

	@Column(table = "candidate")
	private String totalExperience;

	@Column(table = "candidate")
	private String relevantExperience;

	@Column(table = "candidate")
	private String currentCtc;

	@Column(table = "candidate")
	private String expectedCtc;

	@Column(table = "candidate")
	private String noticePeriod;

	@Column(table = "candidate")
	private String visaStatus;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable
	private Set<SkillEntity> skills;

	@OneToMany
	@JoinColumn
	@Fetch(FetchMode.JOIN)
	private Set<InterviewEntity> interviews;

	@OneToMany(mappedBy = "candidate")
	private Set<ReferralEntity> referrals;

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

	public Set<ReferralEntity> getReferrals() {
		if (null == referrals) {
			referrals = Collections.emptySet();
		}

		return referrals;
	}

	public void setReferrals(Set<ReferralEntity> referrals) {
		this.referrals = referrals;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
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

	public Set<InterviewEntity> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<InterviewEntity> interviews) {
		this.interviews = interviews;
	}

	public Set<SkillEntity> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillEntity> skills) {
		this.skills = skills;
	}
}
