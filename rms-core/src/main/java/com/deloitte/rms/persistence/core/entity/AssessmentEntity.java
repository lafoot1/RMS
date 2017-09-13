package com.deloitte.rms.persistence.core.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "interview_assessment")
public class AssessmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private AssessmentTypeEntity assessmentType;
	private String feedback;
	private Integer rating;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AssessmentTypeEntity getAssessmentType() {
		return assessmentType;
	}
	public void setAssessmentType(AssessmentTypeEntity assessmentType) {
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
