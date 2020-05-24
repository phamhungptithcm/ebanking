package com.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanking.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{
	
	public Transaction findByCardAccountNumberOrderByTransactionDateDesc(String cardId);
}
