package com.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanking.entities.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer>{
	
}
