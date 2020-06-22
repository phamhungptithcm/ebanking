package com.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanking.entities.TransactionHistory;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionHistory, String>{
	
	public TransactionHistory findByCardAccountNumberOrderByTransactionDateDesc(String cardId);
}
