package com.yodlee.yodleetest.modal;

public class User {

	public UserDetails user;
	
	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}
	
	public User(UserDetails userDetails) {
		this.user = userDetails;
	}
}
