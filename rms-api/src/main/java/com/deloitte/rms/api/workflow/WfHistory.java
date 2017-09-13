package com.deloitte.rms.api.workflow;

import java.io.Serializable;
import java.util.Calendar;

public class WfHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long reference;
	private WfStatus fromWfStatus;
	private WfStatus toWfStatus;
	private String action;
	private String comment;
	private Calendar createdTs;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getReference() {
		return reference;
	}
	public void setReference(Long reference) {
		this.reference = reference;
	}
	public WfStatus getFromWfStatus() {
		return fromWfStatus;
	}
	public void setFromWfStatus(WfStatus fromWfStatus) {
		this.fromWfStatus = fromWfStatus;
	}
	public WfStatus getToWfStatus() {
		return toWfStatus;
	}
	public void setToWfStatus(WfStatus toWfStatus) {
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
