package com.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanking.entities.CMND;

@Repository
public interface CMNDRepository extends JpaRepository<CMND, String> {

}
