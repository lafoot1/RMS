package com.deloitte.rms.persistence.core.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("INT")
//@PrimaryKeyJoinColumn(name = "id")
public class InterviewerEntity extends PersonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

}
