package com.ebanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EbankingApplication {

	public static void main(String[] args) {
		long  startTime = System.currentTimeMillis();
		
		SpringApplication.run(EbankingApplication.class, args);
		long  endTime = System.currentTimeMillis();
		System.out.println("System started in " + (endTime -startTime)/1000 +" seconds");
	}
	
}
