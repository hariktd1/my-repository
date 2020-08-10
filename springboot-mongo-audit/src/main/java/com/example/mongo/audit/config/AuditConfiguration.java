package com.example.mongo.audit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * 
 * @author Harikrishnan P
 * Class AuditConfiguration
 *
 */
@Configuration
@EnableMongoAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfiguration {
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
}
