package com.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanking.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

}
