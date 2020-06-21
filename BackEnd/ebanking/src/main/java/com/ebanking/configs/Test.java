package com.ebanking.configs;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
	
	private static BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
	
	public static void main(String[] args) {
		String encode = encrypt.encode("1"); 
		System.out.println(encode);
	}
}
