package com.ebanking.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebanking.dto.BranchWrapper;
import com.ebanking.entities.Branch;

@Service
public interface IBranchService {
	public List<BranchWrapper> getAllBranch();

	public BranchWrapper getBranchById(String branchId);

	public Branch createBranch(BranchWrapper branchWrapper);

	public boolean updateBranch(BranchWrapper branchWrapper);

	public boolean deleteBranch(String branchId);
}
