package com.yodlee.yodleetest.modal;

import java.util.HashMap;

public class UserDetails {

	private String loginName;
	private String password;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserDetails(String loginName, String password){
		this.loginName = loginName;
		this.password = password;
	}
}
