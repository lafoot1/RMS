package com.deloitte.rms.api;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.deloitte.rms.api.workflow.WfHistory;
import com.deloitte.rms.api.workflow.WfStatus;

public class Referral implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Requisition requisition;
	private Referer referrer;
	private Candidate candidate;
	private String sourceType;
	private String applicationSource;
	private String rmsStatus;
	private String comments;
	private String additionalComments;
	private String sharedWith;
	private String correspondence;
	private WfStatus wfStatus;
	private Set<Interview> interviews;
	private Set<WfHistory> wfHistories;
	private String wfComment;
	private String wfActionType;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Requisition getRequisition() {
		return requisition;
	}
	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
	}
	public Referer getReferrer() {
		return referrer;
	}
	public void setReferrer(Referer referrer) {
		this.referrer = referrer;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getApplicationSource() {
		return applicationSource;
	}
	public void setApplicationSource(String applicationSource) {
		this.applicationSource = applicationSource;
	}
	public String getRmsStatus() {
		return rmsStatus;
	}
	public void setRmsStatus(String rmsStatus) {
		this.rmsStatus = rmsStatus;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getAdditionalComments() {
		return additionalComments;
	}
	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}
	public String getSharedWith() {
		return sharedWith;
	}
	public void setSharedWith(String sharedWith) {
		this.sharedWith = sharedWith;
	}
	public String getCorrespondence() {
		return correspondence;
	}
	public void setCorrespondence(String correspondence) {
		this.correspondence = correspondence;
	}
	public WfStatus getWfStatus() {
		return wfStatus;
	}
	public void setWfStatus(WfStatus wfStatus) {
		this.wfStatus = wfStatus;
	}
	public Set<Interview> getInterviews() {
		if (interviews == null) {
			interviews = new HashSet<>();
		}

		return interviews;
	}
	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}
	public Set<WfHistory> getWfHistories() {
		if (wfHistories == null) {
			wfHistories = new HashSet<>();
		}

		return wfHistories;
	}
	public void setWfHistories(Set<WfHistory> wfHistories) {
		this.wfHistories = wfHistories;
	}
	public String getWfComment() {
		return wfComment;
	}
	public void setWfComment(String wfComment) {
		this.wfComment = wfComment;
	}
	public String getWfActionType() {
		return wfActionType;
	}
	public void setWfActionType(String wfActionType) {
		this.wfActionType = wfActionType;
	}
}
