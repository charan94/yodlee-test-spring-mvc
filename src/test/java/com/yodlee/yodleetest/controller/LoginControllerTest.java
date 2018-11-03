package com.yodlee.yodleetest.controller;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.yodlee.yodleetest.service.ProviderService;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(content().string("inserted "+ mockCount + " records"))
		.andDo(print());
		
	}
	
	@Test
	public void getProviderByNameTest() throws Exception {
		String mockProviderName = "Midwest Express Frequent Flyer";
		String mockResult = "423 Midwest Express Frequent Flyer US http://www.midwestexpress.com/";
		when(providerService.getProviderByName(mockProviderName)).thenCallRealMethod();
		mockMvc.perform(get("/getprovider"+mockProviderName))
		.andExpect(content().string(mockResult))
		.andDo(print());
	}
	
	@Test
	public void getAggregateResult() throws Exception {
		String mockResult = ", IL 5";
		when(providerService.getProviderCountByCountryISOCode()).thenCallRealMethod();
		mockMvc.perform(get("/getaggregateresult"))
		.andExpect(content().string(containsString(mockResult)))
		.andDo(print());
	}
}
