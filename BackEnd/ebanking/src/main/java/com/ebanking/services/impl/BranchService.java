package com.ebanking.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebanking.dto.BranchWrapper;
import com.ebanking.entities.Branch;
import com.ebanking.repositories.BranchRepository;
import com.ebanking.services.IBranchService;

@Component
public class BranchService implements IBranchService {

	private Logger logger = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	private BranchRepository branchRepository;

	@Override
	public List<BranchWrapper> getAllBranch() {
		List<BranchWrapper> listbranchWrapper = new ArrayList<BranchWrapper>();
		try {
			List<Branch> listBranch = branchRepository.findAll();

			if (listBranch != null) {

				for (Branch branch : listBranch) {
					BranchWrapper branchWrapper = new BranchWrapper();
					BeanUtils.copyProperties(branch, branchWrapper);

					listbranchWrapper.add(branchWrapper);
				}

			}
		} catch (Exception e) {
			logger.error("Get all role has error >>> " + e.getMessage(), e);
		}
		return listbranchWrapper;
	}

	@Override
	public BranchWrapper getBranchById(String branchId) {
		BranchWrapper branch = new BranchWrapper();
		try {
			Optional<Branch> branchDB = branchRepository.findById(branchId);
			if (branchDB.isPresent()) {
				BeanUtils.copyProperties(branchDB.get(), branch);

			}
		} catch (Exception e) {
			logger.error("Get role by id has error >>> " + e.getMessage(), e);
		}
		return branch;
	}

	@Override
	public Branch createBranch(BranchWrapper branchWrapper) {
		Branch branch = new Branch();
		try {
			branch.setId(branchWrapper.getId());
			branch.setBranchName(branchWrapper.getBranchName());
			branch.setContactNumber(branchWrapper.getContactNumber());
			branch.setPlaceGranted(branchWrapper.getPlaceGranted());

			branch = branchRepository.save(branch);
		} catch (Exception e) {
			logger.error("Create role has error >>> " + e.getMessage(), e);
		}
		return branch;
	}

	@Override
	public boolean updateBranch(BranchWrapper branchWrapper) {
		boolean response = false;
		try {
			Optional<Branch> branchDB = branchRepository.findById(branchWrapper.getId());
			if (branchDB.isPresent()) {
				Branch branch = branchDB.get();
				branch.setId(branchWrapper.getId());
				branch.setBranchName(branchWrapper.getBranchName());
				branch.setContactNumber(branchWrapper.getContactNumber());
				branch.setPlaceGranted(branchWrapper.getPlaceGranted());

				branchRepository.save(branch);
				response = true;
			}
		} catch (Exception e) {
			logger.error("Update role has error >>> " + e.getMessage(), e);
		}
		return response;
	}

	@Override
	public boolean deleteBranch(String branchId) {
		boolean response = false;
		try {
			branchRepository.deleteById(branchId);
			response = true;
		} catch (Exception e) {
			logger.error("Delete role has error >>> " + e.getMessage(), e);
		}
		return response;
	}

}
