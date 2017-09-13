package com.deloitte.rms.api;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import com.deloitte.rms.api.type.InterviewModeType;
import com.deloitte.rms.api.type.InterviewType;

public class Interview implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private InterviewType interviewType;
	private InterviewModeType interviewModeType;
	private Candidate candidate;
	private Referral referral;
	private Long referralId;
	private Interviewer interviewer;
	private Set<Assessment> skills;
	private String commentsForNextLevel;
	private Boolean nextLevelRecomentation;
	//other fields to be added
	private Calendar interviewDate;
	private String location;

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
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public Referral getReferral() {
		return referral;
	}
	public void setReferral(Referral referral) {
		this.referral = referral;
	}
	public Long getReferralId() {
		return referralId;
	}
	public void setReferralId(Long referralId) {
		this.referralId = referralId;
	}
	public Interviewer getInterviewer() {
		return interviewer;
	}
	public void setInterviewer(Interviewer interviewer) {
		this.interviewer = interviewer;
	}
	public Set<Assessment> getSkills() {
		return skills;
	}
	public void setSkills(Set<Assessment> skills) {
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
