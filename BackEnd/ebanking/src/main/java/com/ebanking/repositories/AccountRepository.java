package com.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanking.entities.IdCard;

@Repository
public interface AccountRepository extends JpaRepository<IdCard, String>{

}
