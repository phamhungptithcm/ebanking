package com.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebanking.entities.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String>{

}
