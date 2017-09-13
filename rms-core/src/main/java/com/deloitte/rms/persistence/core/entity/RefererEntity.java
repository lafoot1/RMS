package com.deloitte.rms.persistence.core.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorValue("REF")
//@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class RefererEntity extends PersonEntity {
	private static final long serialVersionUID = 1L;
}
