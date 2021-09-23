package com.data.parser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.data.parser.processor.StringProcessor;
/**
 * 
 * @author harikrishnan
 *
 */
@Configuration
public class StringProcessorConfiguration {

	@Bean
	public StringProcessor stringProcessor() {
		StringProcessor stringProcessor = new StringProcessor();
		return stringProcessor;
	}
}
