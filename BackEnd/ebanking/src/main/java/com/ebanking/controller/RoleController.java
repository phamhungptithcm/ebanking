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

import com.ebanking.dto.RoleDTO;
import com.ebanking.entities.Role;
import com.ebanking.services.IRoleService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("${ebanking.rest.unsecure.path}")
@Api(value = "/roleController", tags = "Role Controller REST API", produces = "application/json")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@GetMapping(value = "role-service/roles")
	public ResponseEntity<?> getAllRole() {
		List<RoleDTO> response = roleService.getAllRole();
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "role-service/roles/{id}")
	public ResponseEntity<?> getRoleById(@PathVariable("id") String roleId) {
		RoleDTO response = roleService.getRoleById(roleId);
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "role-service/roles")
	public ResponseEntity<?> createRole(@RequestBody RoleDTO roleDTO) {
		Role response = roleService.createRole(roleDTO);
		if (response != null) {
			return ResponseEntity.ok(response);

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@PutMapping(value = "role-service/roles")
	public ResponseEntity<?> updateRole(@RequestBody RoleDTO roleDTO) {
		boolean response = roleService.updateRole(roleDTO);
		if (response) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@DeleteMapping(value = "role-service/roles/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable("id") String roleId) {
		boolean response = roleService.deleteRole(roleId);
		if (response) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
