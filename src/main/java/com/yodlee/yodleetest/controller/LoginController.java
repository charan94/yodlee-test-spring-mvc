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
	public String getProviderByName(@PathVariable String providerName) {
		String result = "";
		try {
			Provider provider = providerService.getProviderByName(providerName);
			result = provider.getId() + " " + provider.getName() + " " + provider.getCountryISOCode() + " "
					+ provider.getBaseUrl();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return ex.getMessage();
		}
		return result;
	}

	@RequestMapping(value = "/provider/update", method = RequestMethod.POST)
	public String updateProviderName(@RequestBody UpdateProvider updateProvider) {
		String result = "";
		try {
			Provider provider = providerService.updateProviderByName(updateProvider);
			result = "Changed name from " + updateProvider.getProvider().getName() + " to " + provider.getName();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return ex.getMessage();
		}
		return result;
	}

	@RequestMapping(value = "/getaggregateresult", method = RequestMethod.GET)
	public String getAggregatedResult() {
		String str = "";
		try {
			List<CountryGroupResult> result = providerService.getProviderCountByCountryISOCode();
			for (CountryGroupResult res : result) {
				str = str + ", " + res.getCountryISOCode() + " : " + res.getTotal() + " \n";
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return ex.getMessage();
		}
		return str;
	}
}
