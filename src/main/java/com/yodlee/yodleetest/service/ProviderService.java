package com.yodlee.yodleetest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yodlee.yodleetest.dao.ProviderRepository;
import com.yodlee.yodleetest.modal.CobrandLoginResponse;
import com.yodlee.yodleetest.modal.CountryGroupResult;
import com.yodlee.yodleetest.modal.Provider;
import com.yodlee.yodleetest.modal.ProviderList;
import com.yodlee.yodleetest.modal.UpdateProvider;
import com.yodlee.yodleetest.util.YodleeConstants;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.List;

@Service
public class ProviderService {

	@Autowired
	CobrandService cobrandService;

	@Autowired
	UserService userService;

	@Autowired
	UserDetailService userDetailService;

	@Autowired
	ProviderRepository providerRepo;

	@Autowired
	MongoTemplate mongoTemplate;

	public HttpEntity<String> getEntityForProviders(String cobrandToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json;charset=UTF-8");
		headers.set("Api-Version", "1.1");
		headers.set("Cobrand-Name", "restserver");
		String authorization = "cobSession=" + cobrandToken;
		headers.set("Authorization", authorization);
		return new HttpEntity<String>(headers);
	}

	public void saveProviders() throws Exception {
		ProviderList providerList = new ProviderList();
		CobrandLoginResponse cobrandResponse;
		RestTemplate restTemplate = new RestTemplate();
		try {
			cobrandResponse = cobrandService.getLoginResponse();
			System.out.println("Cobrand Login Successful");
			for (int i = 0; i < 10000; i = i + 500) {
				System.out.println("Getting providers list" + i);
				providerList = restTemplate.exchange(YodleeConstants.BASE_URL + "/providers?skip=" + i, HttpMethod.GET,
						getEntityForProviders(cobrandResponse.getSession().get("cobSession")), ProviderList.class)
						.getBody();
				System.out.println("Got providers list" + i);
				System.out.println("Saving providers list" + i);
				providerRepo.saveAll(providerList.getProvider());
				System.out.println("Saved Providers list" + i);
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	public Provider getProviderByName(String name) throws Exception {
		Provider provider;
		try {
			provider = providerRepo.findByName(name);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return provider;
	}

	public Provider updateProviderByName(UpdateProvider providerMap) throws Exception {
		Provider provider;
		try {
			provider = getProviderByName(providerMap.getProvider().getName());
			if (provider != null) {
				provider.setName(providerMap.getProvider().getUpdatedName());
				providerRepo.save(provider);
			} else {
				throw new Exception("No Provider found by the name " + providerMap.getProvider().getName());
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return provider;
	}

	public List<CountryGroupResult> getProviderCountByCountryISOCode() throws Exception {
		List<CountryGroupResult> result;
		try {
			Aggregation agg = newAggregation(group("countryISOCode").count().as("total"),
					project("total").and("countryISOCode").previousOperation());

			AggregationResults<CountryGroupResult> groupResults = mongoTemplate.aggregate(agg, Provider.class,
					CountryGroupResult.class);
			result = groupResults.getMappedResults();
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return result;
	}
	
	public long getProvidersCount() throws Exception {
		long count = 0l;
		try {
			count = providerRepo.count();
		}
		catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		
		return count;
	}
}
