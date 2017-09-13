package com.deloitte.rms.persistence.core.entity.wf;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "wf_status")
public class WfStatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	@Column(updatable = false, insertable = false)
	private String wfStatus;
	@Column(updatable = false, insertable = false)
	private String wfSubstatus;
	@OneToMany
	@JoinColumn(name = "wf_status_id")
	private List<WfMasterEntity> wfMasters;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWfStatus() {
		return wfStatus;
	}
	public void setWfStatus(String wfStatus) {
		this.wfStatus = wfStatus;
	}
	public String getWfSubstatus() {
		return wfSubstatus;
	}
	public void setWfSubstatus(String wfSubstatus) {
		this.wfSubstatus = wfSubstatus;
	}
	public List<WfMasterEntity> getWfMasters() {
		return wfMasters;
	}
	public void setWfMasters(List<WfMasterEntity> wfMasters) {
		this.wfMasters = wfMasters;
	}
}
