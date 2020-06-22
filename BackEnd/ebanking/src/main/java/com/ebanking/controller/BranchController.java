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

import com.ebanking.dto.BranchWrapper;
import com.ebanking.entities.Branch;
import com.ebanking.services.IBranchService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("${ebanking.rest.unsecure.path}")
@Api(value = "/branchController", tags = "Branch Controller REST API", produces = "application/json")
public class BranchController {

	@Autowired
	private IBranchService branchService;

	@GetMapping(value = "branch-service/branchs")
	public ResponseEntity<?> getAllbranch() {
		List<BranchWrapper> response = branchService.getAllBranch();
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "branch-service/branchs/{id}")
	public ResponseEntity<?> getbranchById(@PathVariable("id") String branchId) {
		BranchWrapper response = branchService.getBranchById(branchId);
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "branch-service/branchs")
	public ResponseEntity<?> createbranch(@RequestBody BranchWrapper branchwrapper) {
		Branch response = branchService.createBranch(branchwrapper);
		if (response != null && response.getId() != null) {
			return ResponseEntity.ok(response);

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@PutMapping(value = "branch-service/branchs")
	public ResponseEntity<?> updatebranch(@RequestBody BranchWrapper branchwrapper) {
		boolean response = branchService.updateBranch(branchwrapper);
		if (response) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@DeleteMapping(value = "branch-service/branchs/{id}")
	public ResponseEntity<?> deletebranch(@PathVariable("id") String branchId) {
		boolean response = branchService.deleteBranch(branchId);
		if (response) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
