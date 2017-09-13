package com.deloitte.rms.persistence.mapper.workflow;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.rms.api.workflow.WfHistory;
import com.deloitte.rms.persistence.core.entity.wf.WfHistoryEntity;
import com.deloitte.rms.persistence.mapper.WfStatusMapper;

@Component
public class WfHistoryMapper {
	@Autowired WfStatusMapper wfStatusMapper;

	public WfHistory map(WfHistoryEntity entity) {
		WfHistory wfHistory = new WfHistory();
		BeanUtils.copyProperties(entity, wfHistory);
		wfHistory.setFromWfStatus(wfStatusMapper.map(entity.getFromWfStatus(), false));
		wfHistory.setToWfStatus(wfStatusMapper.map(entity.getToWfStatus(), false));
		wfHistory.setCreatedTs(entity.getCreatedTs());
		return wfHistory;
	}

	public WfHistoryEntity map(WfHistory wfHistory) {
		WfHistoryEntity wfHistoryEntity = new WfHistoryEntity();
		BeanUtils.copyProperties(wfHistory, wfHistoryEntity);
		return wfHistoryEntity;
	}

	public Set<WfHistoryEntity> mapCollection(Collection<WfHistory> sources) {
		Set<WfHistoryEntity> list = new HashSet<>();
		for (WfHistory source : sources) {
			list.add(map(source));
		}
		return list;
	}

	public Set<WfHistory> mapEntity(Collection<WfHistoryEntity> sources) {
		Set<WfHistory> list = new HashSet<>();
		for (WfHistoryEntity source : sources) {
			list.add(map(source));
		}
		return list;
	}
}
