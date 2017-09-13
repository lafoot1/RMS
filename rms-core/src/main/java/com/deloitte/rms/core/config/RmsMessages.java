package com.deloitte.rms.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:rms-messages.properties")
public class RmsMessages {
	@Autowired Environment env;
	
	public static final String SPECIALITY_PREFIX = "rms.speciality.";
	public static final String FUNCTION_PREFIX = "rms.function.";
	public static final String SERVICELINE_PREFIX = "rms.serviceline.";
	
	public String getPropertyValue(String key) {
		return env.getProperty(key);
	}
}
