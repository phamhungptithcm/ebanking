package com.ebanking.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebanking.dto.RoleDTO;
import com.ebanking.entities.Role;

@Service
public interface IRoleService {
	public List<RoleDTO> getAllRole();

	public RoleDTO getRoleById(String roleId);

	public Role createRole(RoleDTO roleDTO);

	public boolean updateRole(RoleDTO roleDTO);

	public boolean deleteRole(String roleId);
}
