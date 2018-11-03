package com.yodlee.yodleetest.modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="providers")
public class Provider {

	private static final long serialVersionUID = 8176956997678024172L;
	
	@Id
	@Field("id")
	public String id;
	
	@Field("name")
	public String name;
	
	@Field("baseUrl")
	public String baseUrl;
	
	@Field("countryISOCode")
	public String countryISOCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getCountryISOCode() {
		return countryISOCode;
	}

	public void setCountryISOCode(String countryISOCode) {
		this.countryISOCode = countryISOCode;
	}
	
	
}
