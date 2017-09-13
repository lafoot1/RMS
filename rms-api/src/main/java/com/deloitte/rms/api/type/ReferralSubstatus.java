package com.deloitte.rms.api.type;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.deloitte.rms.api.type.EnumDeserializer.ReferralSubstatusDeserializer;
import com.deloitte.rms.api.type.EnumSerializer.ReferralSubstatusSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = ReferralSubstatusDeserializer.class)
@JsonSerialize(using = ReferralSubstatusSerializer.class)
public enum ReferralSubstatus {
	TECH1_SCHEDULED("tech1_scheduled"),
	TECH2_SCHEDULED("tech2_scheduled"),
	SENT_FOR_BUS_SCREENING("sent_for_screening"),
	RESCHEDULE_TECH1("reschedule_tech1"),
	RESCHEDULE_TECH2("reschedule_tech2"),
	RESCHEDULE_FINAL("reschedule_final"),
	POSITION_ON_HOLD("position_on_hold"),
	CANDIDATE_ON_HOLD("candidate_on_hold"),
	NO_RESPONSE("no_response"),
	JOINED("joined"),
	TECH1_REJECT("tech1_reject"),
	TECH2_REJECT("tech2_reject"),
	HR_REJECT("hr_reject"),
	UNKNOWN("unknown");
	
	private ReferralSubstatus(String code) {
		this.code = code;
	}
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static ReferralSubstatus getByCode(String code) {
		if (code == null) {
			return ReferralSubstatus.UNKNOWN; 
		}
		
		for (ReferralSubstatus substatus : ReferralSubstatus.values()) {
			if (code.equalsIgnoreCase(substatus.getCode())) {
				return substatus;
			}
		}
		
		return ReferralSubstatus.UNKNOWN; 
	}
	
	public static Set<ReferralSubstatus> getMatchedSubStatuses(String match) {
		if (match == null) {
			return Collections.emptySet();
		}
		
		Set<ReferralSubstatus> subStatuses = new HashSet<>();
		for (ReferralSubstatus status : ReferralSubstatus.values()) {
			if (status.getCode().toUpperCase().contains(match.toUpperCase())) {
				subStatuses.add(status);
			}
		}
		
		return subStatuses; 
	}
}
