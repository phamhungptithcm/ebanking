package com.ebanking.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebanking.dto.RoleDTO;
import com.ebanking.entities.Role;
import com.ebanking.repositories.RoleRepository;
import com.ebanking.services.IRoleService;

@Component
public class RoleService implements IRoleService {

	private Logger logger = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<RoleDTO> getAllRole() {
		List<RoleDTO> listRoleDTO = new ArrayList<RoleDTO>();
		try {
			List<Role> listRole = roleRepository.findAll();

			if (listRole != null) {

				for (Role role : listRole) {
					RoleDTO roleDTO = new RoleDTO();
					BeanUtils.copyProperties(role, roleDTO);

					listRoleDTO.add(roleDTO);
				}

			}
		} catch (Exception e) {
			logger.error("Get all role has error >>> " + e.getMessage(), e);
		}
		return listRoleDTO;
	}

	@Override
	public RoleDTO getRoleById(String roleId) {
		RoleDTO role = new RoleDTO();
		try {
			Optional<Role> roleDB = roleRepository.findById(roleId);
			if (roleDB.isPresent()) {
				BeanUtils.copyProperties(roleDB.get(), role);

			}
		} catch (Exception e) {
			logger.error("Get role by id has error >>> " + e.getMessage(), e);
		}
		return role;
	}

	@Override
	public boolean createRole(RoleDTO roleDTO) {
		boolean response = false;
		try {
			Role role = new Role();
			role.setRoleId(roleDTO.getRoleId());
			role.setActive(roleDTO.getActive());
			role.setRoleName(roleDTO.getRoleName());

			roleRepository.save(role);
			response = true;
		} catch (Exception e) {
			logger.error("Create role has error >>> " + e.getMessage(), e);
		}
		return response;
	}

	@Override
	public boolean updateRole(RoleDTO roleDTO) {
		boolean response = false;
		try {
			Optional<Role> roleDB = roleRepository.findById(roleDTO.getRoleId());
			if (roleDB.isPresent()) {
				Role role = roleDB.get();
				role.setRoleId(roleDTO.getRoleId());
				role.setActive(roleDTO.getActive());
				role.setRoleName(roleDTO.getRoleName());

				roleRepository.save(role);
				response = true;
			}
		} catch (Exception e) {
			logger.error("Update role has error >>> " + e.getMessage(), e);
		}
		return response;
	}

	@Override
	public boolean deleteRole(String roleId) {
		boolean response = false;
		try {
			roleRepository.deleteById(roleId);
			response = true;
		} catch (Exception e) {
			logger.error("Delete role has error >>> " + e.getMessage(), e);
		}
		return response;
	}

}
