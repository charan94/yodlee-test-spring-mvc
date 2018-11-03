package com.yodlee.yodleetest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yodlee.yodleetest.modal.UserDetails;
import com.yodlee.yodleetest.util.YodleeConstants;


@Service
public class UserDetailService {

	public List<String> getUserLogins(){
		List<String> usersList = new ArrayList<>();
		usersList.add(YodleeConstants.USER1);
		usersList.add(YodleeConstants.USER2);
		usersList.add(YodleeConstants.USER3);
		usersList.add(YodleeConstants.USER4);
		usersList.add(YodleeConstants.USER5);
		return usersList;	
	}
	
	public List<UserDetails> getUser() {
		List<UserDetails> userList = new ArrayList<>();
		for(String user: getUserLogins()) {
			UserDetails usr = new UserDetails(user, user+"#123");
			userList.add(usr);
		}
		return userList;
	}
}
