package com.example.mongo.audit.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
/**
 * 
 * @author Harikrishnan P
 * This class will inject current auditor
 */

public class AuditorAwareImpl implements AuditorAware<String>{
	
	/**
	 * Principal can be fetched from UserAuthentication context
	 */
	@Override
	public Optional<String> getCurrentAuditor() {
		
		return Optional.of("hariktd1@gmail.com");
	}

}

