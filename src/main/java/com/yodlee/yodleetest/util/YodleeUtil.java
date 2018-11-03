package com.yodlee.yodleetest.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class YodleeUtil {

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
