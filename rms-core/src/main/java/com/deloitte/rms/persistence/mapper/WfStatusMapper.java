package com.deloitte.rms.persistence.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.rms.api.workflow.WfStatus;
import com.deloitte.rms.core.config.RmsMessages;
import com.deloitte.rms.persistence.core.entity.wf.WfStatusEntity;

@Component
public class WfStatusMapper {
	private static final String WF_STATUS_MSG_PREFIX = "rms.wf.status.";
	private static final String WF_SUBSTATUS_MSG_PREFIX = "rms.wf.substatus.";
	@Autowired RmsMessages messages;

	public WfStatusEntity map(WfStatus wfStatus) {
		if (wfStatus == null) {
			return null;
		}

		WfStatusEntity wfStatusEntity = new WfStatusEntity();
		BeanUtils.copyProperties(wfStatus, wfStatusEntity);
		return wfStatusEntity;
	}

	public WfStatus map(WfStatusEntity wfStatusEntity, boolean mapTarget) {
		WfStatus wfStatus = new WfStatus();
		BeanUtils.copyProperties(wfStatusEntity, wfStatus);
		wfStatus.setWfStatusDesc(messages.getPropertyValue(WF_STATUS_MSG_PREFIX + wfStatusEntity.getWfStatus()));
		wfStatus.setWfSubstatusDesc(messages.getPropertyValue(WF_SUBSTATUS_MSG_PREFIX + wfStatusEntity.getWfSubstatus()));
		if (mapTarget) {
			List<WfStatus> statuses = new ArrayList<>();
			wfStatusEntity.getWfMasters().forEach(entity->statuses.add(map(entity.getTargetWfStatus(), false)));
			wfStatus.setTargetPaths(statuses);
		}
		return wfStatus;
	}
}
