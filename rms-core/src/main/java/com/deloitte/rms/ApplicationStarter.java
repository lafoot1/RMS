package com.deloitte.rms;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.deloitte.rms.persistence.core.entity", "com.deloitte.rms.persistence.core.referral.entity"})
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
@EnableMongoRepositories(basePackages = {"com.deloitte.rms.persistence.repo"})
public class ApplicationStarter {

	public static void main(String[] args) {
    	TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        SpringApplication.run(ApplicationStarter.class, args);
    }

	//added for testing -- not for pord use
	/*@Bean(destroyMethod="close")
	public Mongo mongo() throws IOException {
	    return new EmbeddedMongoBuilder()
	            .version("2.4.5")
	            .bindIp("127.0.0.1")
	            .port(27017)
	            .build();
	}*/
}
