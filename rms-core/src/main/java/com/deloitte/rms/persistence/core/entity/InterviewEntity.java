package com.deloitte.rms.persistence.core.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.deloitte.rms.api.type.InterviewModeType;
import com.deloitte.rms.api.type.InterviewType;

@Entity
@Table(name = "interview")
public class InterviewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Enumerated(EnumType.STRING)
	private InterviewType interviewType;
	@Enumerated(EnumType.STRING)
	private InterviewModeType interviewModeType;
	@ManyToOne
	private CandidateEntity candidate;
	@ManyToOne
	private ReferralEntity referralEntity;
	private Long referralId;
	@OneToOne
	@JoinColumn(nullable = false, insertable = false, updatable = false)
	private InterviewerEntity interviewer;
	@OneToMany
	private Set<AssessmentEntity> skills;
	private String commentsForNextLevel;
	private Boolean nextLevelRecomentation;
	//other fields to be added
	private Calendar interviewDate;
	private String location;

	private Calendar createdTs;
	private Long createdBy;
	private Calendar updatedTs;
	private Long updatedBy;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public InterviewType getInterviewType() {
		return interviewType;
	}
	public void setInterviewType(InterviewType interviewType) {
		this.interviewType = interviewType;
	}
	public InterviewModeType getInterviewModeType() {
		return interviewModeType;
	}
	public void setInterviewModeType(InterviewModeType interviewModeType) {
		this.interviewModeType = interviewModeType;
	}
	public CandidateEntity getCandidate() {
		return candidate;
	}
	public void setCandidate(CandidateEntity candidate) {
		this.candidate = candidate;
	}
	public ReferralEntity getReferralEntity() {
		return referralEntity;
	}
	public void setReferralEntity(ReferralEntity referralEntity) {
		this.referralEntity = referralEntity;
	}
	public Long getReferralId() {
		return referralId;
	}
	public void setReferralId(Long referralId) {
		this.referralId = referralId;
	}
	public InterviewerEntity getInterviewer() {
		return interviewer;
	}
	public void setInterviewer(InterviewerEntity interviewer) {
		this.interviewer = interviewer;
	}
	public Set<AssessmentEntity> getSkills() {
		return skills;
	}
	public void setSkills(Set<AssessmentEntity> skills) {
		this.skills = skills;
	}
	public String getCommentsForNextLevel() {
		return commentsForNextLevel;
	}
	public void setCommentsForNextLevel(String commentsForNextLevel) {
		this.commentsForNextLevel = commentsForNextLevel;
	}
	public Boolean getNextLevelRecomentation() {
		return nextLevelRecomentation;
	}
	public void setNextLevelRecomentation(Boolean nextLevelRecomentation) {
		this.nextLevelRecomentation = nextLevelRecomentation;
	}
	public Calendar getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(Calendar interviewDate) {
		this.interviewDate = interviewDate;
	}
	public Calendar getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(Calendar createdTs) {
		this.createdTs = createdTs;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Calendar getUpdatedTs() {
		return updatedTs;
	}
	public void setUpdatedTs(Calendar updatedTs) {
		this.updatedTs = updatedTs;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
