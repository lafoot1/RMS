package com.deloitte.rms.persistence.core.entity.wf;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.deloitte.rms.persistence.core.entity.ReferralEntity;

@Entity
@Table(name = "wf_history")
public class WfHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private ReferralEntity reference;
	@ManyToOne
	private WfStatusEntity fromWfStatus;
	@ManyToOne
	private WfStatusEntity toWfStatus;
	private String action;
	private String comment;
	private @CreationTimestamp Calendar createdTs;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ReferralEntity getReference() {
		return reference;
	}
	public void setReference(ReferralEntity reference) {
		this.reference = reference;
	}
	public WfStatusEntity getFromWfStatus() {
		return fromWfStatus;
	}
	public void setFromWfStatus(WfStatusEntity fromWfStatus) {
		this.fromWfStatus = fromWfStatus;
	}
	public WfStatusEntity getToWfStatus() {
		return toWfStatus;
	}
	public void setToWfStatus(WfStatusEntity toWfStatus) {
		this.toWfStatus = toWfStatus;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Calendar getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(Calendar createdTs) {
		this.createdTs = createdTs;
	}
}
