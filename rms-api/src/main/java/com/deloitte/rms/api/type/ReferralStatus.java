package com.deloitte.rms.api.type;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = EnumDeserializer.ReferralStatusDeserializer.class)
@JsonSerialize(using = EnumSerializer.ReferralStatusSerializer.class)
public enum ReferralStatus {
	NEW("new"),
	SCREENING("screening"),
	IN_REVIEW("in_review"),
	IN_PROCESS("in_process"),
	ACCEPTED("accepted"),
	REJECTED("rejetced"),
	UNKNOWN("unknown");

	private ReferralStatus(String code) {
		this.code = code;
	}
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static ReferralStatus getByCode(String code) {
		if (code == null) {
			return ReferralStatus.UNKNOWN; 
		}
		
		for (ReferralStatus status : ReferralStatus.values()) {
			if (code.equalsIgnoreCase(status.getCode())) {
				return status;
			}
		}
		
		return ReferralStatus.UNKNOWN; 
	}
	
	public static Set<ReferralStatus> getMatchedStatuses(String match) {
		if (match == null) {
			return Collections.emptySet();
		}
		
		Set<ReferralStatus> statuses = new HashSet<>();
		for (ReferralStatus status : ReferralStatus.values()) {
			if (status.getCode().toUpperCase().contains(match.toUpperCase())) {
				statuses.add(status);
			}
		}
		
		return statuses; 
	}
}
