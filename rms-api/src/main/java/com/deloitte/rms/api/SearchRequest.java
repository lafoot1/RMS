package com.deloitte.rms.api;

import java.io.Serializable;

import com.deloitte.rms.api.type.ReferralStatus;
import com.deloitte.rms.api.type.ReferralSubstatus;

public class SearchRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int pageNo;
	private int pageSize;
	private String candidateFirstName;
	private String candidateLastName;
	private String candidateEmail;
	private ReferralStatus status;
	private ReferralSubstatus subStatus;
	private String recruiterName;
	private String sharedName;
	private String query;
	
	public SearchRequest() {
		pageNo = 0;
		pageSize = 10;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getCandidateFirstName() {
		return candidateFirstName;
	}
	public void setCandidateFirstName(String candidateFirstName) {
		this.candidateFirstName = candidateFirstName;
	}
	public String getCandidateLastName() {
		return candidateLastName;
	}
	public void setCandidateLastName(String candidateLastName) {
		this.candidateLastName = candidateLastName;
	}
	public String getCandidateEmail() {
		return candidateEmail;
	}
	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}
	public ReferralStatus getStatus() {
		return status;
	}
	public void setStatus(ReferralStatus status) {
		this.status = status;
	}
	public ReferralSubstatus getSubStatus() {
		return subStatus;
	}
	public void setSubStatus(ReferralSubstatus subStatus) {
		this.subStatus = subStatus;
	}
	public String getRecruiterName() {
		return recruiterName;
	}
	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}
	public String getSharedName() {
		return sharedName;
	}
	public void setSharedName(String sharedName) {
		this.sharedName = sharedName;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
}
