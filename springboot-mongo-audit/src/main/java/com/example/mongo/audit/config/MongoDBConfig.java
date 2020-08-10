package com.example.mongo.audit.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * 
 * @author Harikrishnan P
 * Class MongoDBConfig
 */
@Configuration
public class MongoDBConfig {

	private static final String MONGO_DB_NAME = "local_db";

	@Bean
	public MongoTemplate mongoTemplate() throws IOException {

		MongoClient mongoClient = MongoClients.create();

		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);

		return mongoTemplate;

	}

}
