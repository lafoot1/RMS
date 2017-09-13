package com.deloitte.rms.api;

import java.io.Serializable;

public class Assessment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private AssessmentType assessmentType;
	private String feedback;
	private Integer rating;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AssessmentType getAssessmentType() {
		return assessmentType;
	}
	public void setAssessmentType(AssessmentType assessmentType) {
		this.assessmentType = assessmentType;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
