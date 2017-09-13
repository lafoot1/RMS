package com.deloitte.rms.core.util;

import org.springframework.stereotype.Component;

import com.deloitte.rms.api.Person;
import com.deloitte.rms.api.type.PersonType;

@Component
public class PrincipalHelper {
	public Person getCurrentUser() {
		Person person = new Person() {
		private static final long serialVersionUID = 1L; };
		person.setEmail("asd@asd.com");
		person.setFirstName("Test");
		person.setLastName("Poiuy");
		person.setId(1L);
		person.setType(PersonType.RECRUITER);
		return person;
	}
}
