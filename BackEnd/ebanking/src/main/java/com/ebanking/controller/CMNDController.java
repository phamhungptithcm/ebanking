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

import com.ebanking.dto.CMNDWrapper;
import com.ebanking.entities.CMND;
import com.ebanking.services.ICMNDService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("${ebanking.rest.unsecure.path}")
@Api(value = "/cmndController", tags = "CMND Controller REST API", produces = "application/json")
public class CMNDController {

	@Autowired
	private ICMNDService cmndService;

	@GetMapping(value = "cmnd-service/cmnd")
	public ResponseEntity<?> getAllCMND() {
		List<CMNDWrapper> response = cmndService.getAllCMND();
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "cmnd-service/cmnd/{id}")
	public ResponseEntity<?> getCMNDById(@PathVariable("id") String cmndId) {
		CMNDWrapper response = cmndService.getCMNDById(cmndId);
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "cmnd-service/cmnd")
	public ResponseEntity<?> createCMND(@RequestBody CMNDWrapper cmndWrapper) {
		CMND response = cmndService.createCMND(cmndWrapper);
		if (response != null && response.getId() != null) {
			return ResponseEntity.ok(response);

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@PutMapping(value = "cmnd-service/cmnd")
	public ResponseEntity<?> updateCMND(@RequestBody CMNDWrapper cmndWrapper) {
		boolean response = cmndService.updateCMND(cmndWrapper);
		if (response) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@DeleteMapping(value = "cmnd-service/cmnd/{id}")
	public ResponseEntity<?> deleteCMND(@PathVariable("id") String cmndId) {
		boolean response = cmndService.deleteCMND(cmndId);
		if (response) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
