package com.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanking.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, String>{

}
