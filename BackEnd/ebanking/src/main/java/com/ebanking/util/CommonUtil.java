package com.ebanking.util;

import org.apache.commons.lang3.RandomStringUtils;


public class CommonUtil {
	public static String randomStrongPassword(int length, boolean letter, boolean number) {
		return RandomStringUtils.random(length, letter, number);
	}
}
