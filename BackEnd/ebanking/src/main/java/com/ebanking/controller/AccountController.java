package com.ebanking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebanking.dto.AccountDTO;
import com.ebanking.dto.JsonMessageDTO;
import com.ebanking.services.IAccountService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "/accountcontroller", tags = "Account Controller REST API", produces = "application/json")
@RequestMapping("${ebanking.rest.unsecure.path}")
public class AccountController extends HelperController {

	private Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private IAccountService accountService;

	@PostMapping(value = "accountService/login")
	public ResponseEntity<?> login(@RequestBody AccountDTO request) {
		JsonMessageDTO response = null;
		try {
			response = accountService.login(request, session);
		} catch (Exception e) {
			logger.error("Login has error >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "accountService/logout")
	public ResponseEntity<?> logout() {
		try {
			accountService.logout(session);
		} catch (Exception e) {
			logger.error("Logout has error >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
		return ResponseEntity.ok(true);
	}
	
	@PostMapping(value = "accountService/getAccountInfo")
	public ResponseEntity<?> getAccountInfo(@RequestBody AccountDTO request) {
		JsonMessageDTO response = null;
		try {
			response = accountService.getAccountInfo(request);
		} catch (Exception e) {
			logger.error("getAccountInfo has error >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.ok(response);
	}	
}
