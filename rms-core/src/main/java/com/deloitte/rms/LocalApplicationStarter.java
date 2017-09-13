package com.deloitte.rms;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mongodb.Mongo;

import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;

@Configuration
@Profile("local")
public class LocalApplicationStarter {
	@Bean(destroyMethod="close")
	public Mongo mongo() throws IOException {
	    return new EmbeddedMongoBuilder()
	            .version("2.4.5")
	            .bindIp("127.0.0.1")
	            .port(27017)
	            .build();
	}
}
