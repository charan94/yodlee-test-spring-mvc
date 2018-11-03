package com.yodlee.yodleetest.modal;

import java.util.HashMap;

public class UserLoginResponse {

	private int id;
	private String loginName;
	private HashMap<String, String> session;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public HashMap<String, String> getSession() {
		return session;
	}
	public void setSession(HashMap<String, String> session) {
		this.session = session;
	}
	
	
}
