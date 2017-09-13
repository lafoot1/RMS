package com.deloitte.rms.api.workflow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WfStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String wfStatus;
	private String wfStatusDesc;
	private String wfSubstatus;
	private String wfSubstatusDesc;

	private List<WfStatus> targetPaths;

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
	public String getWfStatusDesc() {
		return wfStatusDesc;
	}
	public void setWfStatusDesc(String wfStatusDesc) {
		this.wfStatusDesc = wfStatusDesc;
	}
	public String getWfSubstatus() {
		return wfSubstatus;
	}
	public void setWfSubstatus(String wfSubstatus) {
		this.wfSubstatus = wfSubstatus;
	}
	public String getWfSubstatusDesc() {
		return wfSubstatusDesc;
	}
	public void setWfSubstatusDesc(String wfSubstatusDesc) {
		this.wfSubstatusDesc = wfSubstatusDesc;
	}
	public List<WfStatus> getTargetPaths() {
		if (targetPaths == null) {
			targetPaths = new ArrayList<>();
		}

		return targetPaths;
	}
	public void setTargetPaths(List<WfStatus> targetPaths) {
		this.targetPaths = targetPaths;
	}
}
