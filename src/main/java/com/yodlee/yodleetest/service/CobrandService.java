package com.yodlee.yodleetest.service;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yodlee.yodleetest.modal.Cobrand;
import com.yodlee.yodleetest.modal.CobrandLoginResponse;
import com.yodlee.yodleetest.util.YodleeConstants;

@Service
public class CobrandService {
	
	private  HttpEntity<Object> getCobrandEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json;charset=UTF-8");
		headers.set("Api-Version", "1.1");
		headers.set("Cobrand-Name", "restserver");
		HashMap<String,Cobrand> body = new HashMap<>();
		body.put("cobrand", getCobrand());
		return new HttpEntity<Object>(body, headers);
	}
	
	public Cobrand getCobrand() {
		Cobrand cobrand = new Cobrand();
		cobrand.setCobrandLogin(YodleeConstants.COBRAND_ID);
		cobrand.setCobrandPassword(YodleeConstants.COBRAND_PWD);
		return cobrand;
	}
	
	public CobrandLoginResponse getLoginResponse() throws Exception {
		CobrandLoginResponse response;
		RestTemplate restTemplate = new RestTemplate();
		try {
			response = restTemplate.exchange(YodleeConstants.BASE_URL + "/cobrand/login", HttpMethod.POST, getCobrandEntity(), CobrandLoginResponse.class).getBody();
		}
		catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return response;
	}
}
