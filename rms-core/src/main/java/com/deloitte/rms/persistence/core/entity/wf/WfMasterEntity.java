package com.deloitte.rms.persistence.core.entity.wf;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wf_master")
public class WfMasterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	@Column(name = "wf_status_id")
	private Long wfStatusId;
	@Column(name = "target_wf_status_id")
	private Long targetWfStatusId;

	@ManyToOne
	@JoinColumn(name = "wf_status_id", insertable = false, updatable = false)
	private WfStatusEntity wfStatusEntity;
	@ManyToOne
	@JoinColumn(name = "target_wf_status_id", insertable = false, updatable = false)
	private WfStatusEntity targetWfStatus;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWfStatusId() {
		return wfStatusId;
	}
	public void setWfStatusId(Long wfStatusId) {
		this.wfStatusId = wfStatusId;
	}
	public Long getTargetWfStatusId() {
		return targetWfStatusId;
	}
	public void setTargetWfStatusId(Long targetWfStatusId) {
		this.targetWfStatusId = targetWfStatusId;
	}
	public WfStatusEntity getWfStatusEntity() {
		return wfStatusEntity;
	}
	public void setWfStatusEntity(WfStatusEntity wfStatusEntity) {
		this.wfStatusEntity = wfStatusEntity;
	}
	public WfStatusEntity getTargetWfStatus() {
		return targetWfStatus;
	}
	public void setTargetWfStatus(WfStatusEntity targetWfStatus) {
		this.targetWfStatus = targetWfStatus;
	}
}
