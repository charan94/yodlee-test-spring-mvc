package com.yodlee.yodleetest.controller;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.yodlee.yodleetest.dao.ProviderRepository;
import com.yodlee.yodleetest.modal.CountryGroupResult;
import com.yodlee.yodleetest.modal.Provider;
import com.yodlee.yodleetest.modal.UpdateProvider;
import com.yodlee.yodleetest.modal.UpdateProviderDetails;
import com.yodlee.yodleetest.service.ProviderService;
import com.yodlee.yodleetest.util.YodleeUtil;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginControllerTest {

	@InjectMocks
	private LoginController loginController;

	@Mock
	private ProviderService providerService;

	@Mock
	private ProviderRepository providerRepo;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}

	@Test
	public void getCountOfRecordsInsertedTest() throws Exception {
		long mockCount = 10000l;
		when(providerService.getProvidersCount()).thenReturn(mockCount);
		mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(content().string("inserted " + mockCount + " records")).andDo(print());
	}

	@Test
	public void getProviderByNameTest() throws Exception {
		String mockProviderName = "Great Western Bank";
		String mockResult = "{\"id\":\"424\",\"name\":\"Great Western Bank\",\"baseUrl\":\"http://www.greatwesternbank.com/\",\"countryISOCode\":\"US\"}";
		Provider provider = new Provider();
		provider.setId("424");
		provider.setName(mockProviderName);
		provider.setCountryISOCode("US");
		provider.setBaseUrl("http://www.greatwesternbank.com/");
		when(providerService.getProviderByName(mockProviderName)).thenReturn(provider);
		mockMvc.perform(get("/getprovider/{name:.*}", "{name:\"" + mockProviderName + "\"}")).andExpect(status().isOk())
				.andExpect(content().string(mockResult)).andDo(print());
	}

	@Test
	public void getAggregateResult() throws Exception {
		String mockResult = "[{\"countryISOCode\":\"CO\",\"total\":7}]";
		List<CountryGroupResult> result = new ArrayList<>();
		CountryGroupResult groupResult = new CountryGroupResult();
		groupResult.setCountryISOCode("CO");
		groupResult.setTotal(7);
		result.add(groupResult);
		when(providerService.getProviderCountByCountryISOCode()).thenReturn(result);
		mockMvc.perform(get("/getaggregateresult")).andExpect(content().string(mockResult)).andDo(print());
	}

	@Test
	public void UpdateProviderName() throws Exception {
		UpdateProvider mockBody = new UpdateProvider();
		UpdateProviderDetails mockProviderDetails = new UpdateProviderDetails();
		mockProviderDetails.setName("Great Western Bank");
		mockProviderDetails.setUpdatedName("Great Western Bank Test");
		mockBody.setProvider(mockProviderDetails);
		Provider provider = new Provider();
		provider.setId("424");
		provider.setName("Great Western Bank Test");
		provider.setCountryISOCode("US");
		provider.setBaseUrl("http://www.greatwesternbank.com/");
		String mockResult = "{\"id\":\"424\",\"name\":\"Great Western Bank Test\",\"baseUrl\":\"http://www.greatwesternbank.com/\",\"countryISOCode\":\"US\"}";
		Gson gson = new Gson();

		when(providerService.getProviderByName(mockProviderDetails.getName())).thenReturn(provider);
		when(providerService.updateProviderByName(mockBody)).thenReturn(provider);
		when(providerRepo.save(provider)).thenReturn(provider);

		mockMvc.perform(post("/provider/update").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(mockBody)))
				.andExpect(status().isOk()).andExpect(content().string("")).andDo(print());
	}
}
