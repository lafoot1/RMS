package com.deloitte.rms.persistence.core.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.deloitte.rms.persistence.core.entity.wf.WfHistoryEntity;
import com.deloitte.rms.persistence.core.entity.wf.WfStatusEntity;

@Entity
@Table(name = "referral")
public class ReferralEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String sourceType;
	private String applicationSource;
	private String rmsStatus;
	private String comments;
	private String additionalComments;
	private String sharedWith;
	private String correspondence;

	@ManyToOne
	@JoinColumn
	@Fetch(FetchMode.JOIN)
	private RefererEntity referrer;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private RequisitionEntity requisition;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn
	@Fetch(FetchMode.JOIN)
	private CandidateEntity candidate;
	@ManyToOne
	@JoinColumn
	private WfStatusEntity wfStatus;
	@OneToMany
	@JoinColumn
	private Set<InterviewEntity> interviews;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "reference_id")
	private Set<WfHistoryEntity> wfHistories;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RequisitionEntity getRequisition() {
		return requisition;
	}
	public void setRequisition(RequisitionEntity requisition) {
		this.requisition = requisition;
	}
	public RefererEntity getReferrer() {
		return referrer;
	}
	public void setReferrer(RefererEntity referrer) {
		this.referrer = referrer;
	}
	public CandidateEntity getCandidate() {
		return candidate;
	}
	public void setCandidate(CandidateEntity candidate) {
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
	public Set<InterviewEntity> getInterviews() {
		return interviews;
	}
	public void setInterviews(Set<InterviewEntity> interviews) {
		this.interviews = interviews;
	}
	public WfStatusEntity getWfStatus() {
		return wfStatus;
	}
	public void setWfStatus(WfStatusEntity wfStatus) {
		this.wfStatus = wfStatus;
	}
	public Set<WfHistoryEntity> getWfHistories() {
		if (wfHistories == null) {
			return new HashSet<>();
		}

		return wfHistories;
	}
	public void setWfHistories(Set<WfHistoryEntity> wfHistories) {
		this.wfHistories = wfHistories;
	}
}
