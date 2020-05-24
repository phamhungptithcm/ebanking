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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebanking.dto.CardDTO;
import com.ebanking.dto.JsonMessageDTO;
import com.ebanking.dto.TransferDTO;
import com.ebanking.services.ICardService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "/cardcontroller", tags = "Card Controller REST API", produces = "application/json")
@RequestMapping("${ebanking.rest.unsecure.path}")
public class CardController extends HelperController{
	
	private Logger logger = LoggerFactory.getLogger(CardController.class);
	
	@Autowired
	private ICardService cardService;
	
	@PostMapping(value = "cardService/getTransactionHistory")
	public ResponseEntity<?> getTransactionHistory(@RequestBody CardDTO request) {
		JsonMessageDTO response = null;
		try {
			response = cardService.getTransactionHistory(request);
		} catch (Exception e) {
			logger.error("Login has error >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "cardService/getCardInfo")
	public ResponseEntity<?> getCardInfor(@RequestBody CardDTO request) {
		JsonMessageDTO response = null;
		try {
			response = cardService.getCardInfo(request);
		} catch (Exception e) {
			logger.error("Login has error >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "cardService/transfer")
	public ResponseEntity<?> transfer(@RequestBody TransferDTO request) {
		JsonMessageDTO response = null;
		try {
			response = cardService.transfers(request);
		} catch (Exception e) {
			logger.error("Login has error >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "cardService/getCards")
	public ResponseEntity<?> getCardsByAccountId(@RequestParam String accountId) {
		JsonMessageDTO response = null;
		try {
			response = cardService.getCardsByAccountId(accountId);
		} catch (Exception e) {
			logger.error("Login has error >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.ok(response);
	}
}
