package com.elite.agents.server.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.elite.agents.server.repository")
@EnableMongoAuditing
public class MongoConfiguration {
}
