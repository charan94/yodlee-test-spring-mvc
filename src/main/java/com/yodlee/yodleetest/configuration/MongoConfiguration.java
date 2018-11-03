package com.yodlee.yodleetest.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration{

	@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return new MongoClient("localhost");
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "test";
	}

}
