package com.deloitte.rms.persistence.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.rms.api.Referral;
import com.deloitte.rms.core.config.RmsMessages;
import com.deloitte.rms.persistence.core.entity.ReferralEntity;
import com.deloitte.rms.persistence.mapper.workflow.WfHistoryMapper;

@Component
public class ReferralMapper {
	@Autowired RmsMessages rmsMessages;
	@Autowired CandidateMapper candidateMapper;
	@Autowired RequisitionMapper requisitionMapper;
	@Autowired ReferrerMapper referrerMapper;
	@Autowired WfStatusMapper wfStatusMapper;
	@Autowired WfHistoryMapper wfHistoryMapper;

	public List<Referral> map(Collection<ReferralEntity> entities, boolean mapCandidate) {
		List<Referral>  referrals = new ArrayList<>(entities.size());
		entities.stream().parallel().forEach(entity -> {
			Referral referral = map(entity, mapCandidate);
			referrals.add(referral);
		});

		return referrals;
	}

	public Referral map(ReferralEntity entity, boolean mapCandidate) {
		Referral referral = new Referral();
		BeanUtils.copyProperties(entity, referral);
		referral.setCandidate(mapCandidate?candidateMapper.map(entity.getCandidate(), !mapCandidate):null);
		referral.setRequisition(requisitionMapper.map(entity.getRequisition()));
		referral.setReferrer(referrerMapper.map(entity.getReferrer()));
		/*referral.setStatusDesc(rmsMessages.getPropertyValue("rms.status."+referral.getWfStatus().getWfStatus()));
		if (referral.getSubStatus() != null) {
			referral.setSubStatusDesc(rmsMessages.getPropertyValue("rms.substatus."+referral.getSubStatus().getCode()));
		}*/
		referral.setWfStatus(wfStatusMapper.map(entity.getWfStatus(), true));
		referral.setWfHistories(wfHistoryMapper.mapEntity(entity.getWfHistories()));
		return referral;
	}

	public ReferralEntity map(Referral referral) {
		ReferralEntity entity = new ReferralEntity();
		BeanUtils.copyProperties(referral, entity);
		entity.setReferrer(referrerMapper.map(referral.getReferrer()));
		entity.setCandidate(candidateMapper.map(referral.getCandidate()));
		entity.setRequisition(requisitionMapper.map(referral.getRequisition()));
		entity.setWfStatus(wfStatusMapper.map(referral.getWfStatus()));
		entity.setWfHistories(wfHistoryMapper.mapCollection(referral.getWfHistories()));
		return entity;
	}
}
