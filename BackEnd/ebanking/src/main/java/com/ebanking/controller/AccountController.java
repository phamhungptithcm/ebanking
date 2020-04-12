package com.ebanking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebanking.services.IAccountService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "/accountcontroller", tags = "Account Controller REST API", produces = "application/json")
@RequestMapping("${ebanking.rest.unsecure.path}")
public class AccountController {
	
	private Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private IAccountService accountService;
	
	
}
