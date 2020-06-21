package com.ebanking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebanking.dto.AccountTypeDTO;
import com.ebanking.services.IAccountType;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("${ebanking.rest.unsecure.path}")
@Api(value = "/acountTypeController", tags = "Account Type Controller REST API", produces = "application/json")
public class AccountTypeController {

	@Autowired
	private IAccountType accountTypeService;

	@GetMapping(value = "accounttype-service/accounttype")
	public ResponseEntity<?> getAllAccountType() {
		List<AccountTypeDTO> response = accountTypeService.getAllAccountType();
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "accounttype-service/accounttype/{id}")
	public ResponseEntity<?> getAccountTypeById(@PathVariable("id") Integer accountTypeId) {
		AccountTypeDTO response = accountTypeService.getAccountTypeById(accountTypeId);
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "accounttype-service/accounttype")
	public ResponseEntity<?> createAccountType(@RequestBody AccountTypeDTO AccountTypeDTO) {
		boolean response = accountTypeService.createAccountType(AccountTypeDTO);
		if (response) {
			return ResponseEntity.ok(response);

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@PutMapping(value = "accounttype-service/accounttype")
	public ResponseEntity<?> updateAccountType(@RequestBody AccountTypeDTO AccountTypeDTO) {
		boolean response = accountTypeService.updateAccountType(AccountTypeDTO);
		if (response) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@DeleteMapping(value = "accounttype-service/accounttype/{id}")
	public ResponseEntity<?> deleteAccountType(@PathVariable("id") Integer accountTypeId) {
		boolean response = accountTypeService.deleteAccountType(accountTypeId);
		if (response) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
