package com.yodlee.yodleetest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yodlee.yodleetest.modal.CountryGroupResult;
import com.yodlee.yodleetest.modal.Provider;
import com.yodlee.yodleetest.modal.UpdateProvider;
import com.yodlee.yodleetest.service.ProviderService;

@RestController
public class LoginController {

	@Autowired
	ProviderService providerService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String cobrandAndUserLogin() {
		long count = 0l;
		try {
			providerService.saveProviders();
			count = providerService.getProvidersCount();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return ex.getMessage();
		}
		return "inserted " + count + " records";
	}

	@RequestMapping(value = "/getprovider/{providerName}", method = RequestMethod.GET)
	public Provider getProviderByName(@PathVariable String providerName) {
		Provider provider = null;
		try {
			provider = providerService.getProviderByName(providerName);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return provider;
	}

	@RequestMapping(value = "/provider/update", method = RequestMethod.POST)
	public Provider updateProviderName(@RequestBody UpdateProvider updateProvider) {
		Provider provider = null;
		try {
			provider = providerService.updateProviderByName(updateProvider);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return provider;
	}

	@RequestMapping(value = "/getaggregateresult", method = RequestMethod.GET)
	public List<CountryGroupResult> getAggregatedResult() {
		List<CountryGroupResult> result = null;
		try {
			result = providerService.getProviderCountByCountryISOCode();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
}
