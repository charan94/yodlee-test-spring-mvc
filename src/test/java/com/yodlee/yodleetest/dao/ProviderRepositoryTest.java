package com.yodlee.yodleetest.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.yodlee.yodleetest.modal.Provider;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ProviderRepositoryTest {

	@Mock
	ProviderRepository providerRepo;
	
	@Test
	public void getCountOfRecordsInsertedTest() throws Exception {
		long mockCount = 10000l;
		when(providerRepo.count()).thenReturn(mockCount);
		assertEquals(mockCount, 10000l);
	}
	
	@Test 
	public void findProviderByNameTest() {
		String mockName = "Great Western Bank";
		String mockResult = "424";
		Provider provider = new Provider();
		provider.setId("424");
		provider.setName(mockName);
		provider.setCountryISOCode("US");
		provider.setBaseUrl("http://www.greatwesternbank.com/");
		when(providerRepo.findByName(mockName)).thenReturn(provider);
		assertEquals(mockResult, provider.getId());
	}
}
