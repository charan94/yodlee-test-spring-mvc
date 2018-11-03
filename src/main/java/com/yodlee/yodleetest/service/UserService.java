package com.yodlee.yodleetest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yodlee.yodleetest.modal.CobrandLoginResponse;
import com.yodlee.yodleetest.modal.User;
import com.yodlee.yodleetest.modal.UserDetails;
import com.yodlee.yodleetest.modal.UserLoginResponseDetails;
import com.yodlee.yodleetest.util.YodleeConstants;

@Service
public class UserService {

	@Autowired
	CobrandService cobrandService;
	
	private  HttpEntity<Object> getEntityForUser(User user, CobrandLoginResponse response) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json;charset=UTF-8");
		headers.set("Api-Version", "1.1");
		headers.set("Cobrand-Name", "restserver");
		String authorization = "cobSession="+response.getSession().get("cobSession");
		headers.set("Authorization", authorization);
		return new HttpEntity<Object>(user, headers);
	}
	
	public UserLoginResponseDetails authenticateUser(UserDetails userDetail, CobrandLoginResponse cobrandLoginResponse) {
		User user = new User(userDetail);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(YodleeConstants.BASE_URL+"/user/login", HttpMethod.POST, getEntityForUser(user, cobrandLoginResponse), UserLoginResponseDetails.class).getBody();
	}
}
