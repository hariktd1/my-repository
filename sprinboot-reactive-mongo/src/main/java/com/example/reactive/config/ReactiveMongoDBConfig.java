package com.example.reactive.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

/**
 * 
 * @author Harikrishnan P
 * Class ReactiveMongoDBConfig
 */
@Configuration
public class ReactiveMongoDBConfig {

	private static final String MONGO_DB_NAME = "local_db";

	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() throws IOException {

		MongoClient mongoClient = MongoClients.create();
		
		ReactiveMongoTemplate reactiveMongoTemplate=new ReactiveMongoTemplate(mongoClient, MONGO_DB_NAME);
		
		return reactiveMongoTemplate;

	}

}
