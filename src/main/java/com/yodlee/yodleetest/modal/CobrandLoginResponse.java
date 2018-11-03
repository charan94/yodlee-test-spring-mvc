package com.yodlee.yodleetest.modal;

import java.util.HashMap;

public class CobrandLoginResponse {

	private int cobrandId;
	private String applicationId;
	private String locale;
	private HashMap<String, String> session;
	public int getCobrandId() {
		return cobrandId;
	}
	public void setCobrandId(int cobrandId) {
		this.cobrandId = cobrandId;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public HashMap<String, String> getSession() {
		return session;
	}
	public void setSession(HashMap<String, String> session) {
		this.session = session;
	}
	
	
	
}
