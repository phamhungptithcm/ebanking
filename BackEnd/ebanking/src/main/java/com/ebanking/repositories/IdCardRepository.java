package com.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanking.entities.Account;

@Repository
public interface IdCardRepository extends JpaRepository<Account, String>{

}
