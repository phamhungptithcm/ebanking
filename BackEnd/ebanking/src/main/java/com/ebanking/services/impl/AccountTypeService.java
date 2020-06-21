package com.ebanking.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebanking.dto.AccountTypeDTO;
import com.ebanking.entities.AccountType;
import com.ebanking.repositories.AccountTypeRepository;
import com.ebanking.services.IAccountType;

@Component
public class AccountTypeService implements IAccountType {

	@Autowired
	private AccountTypeRepository accountTypeRepository;

	private Logger logger = LoggerFactory.getLogger(AccountTypeService.class);

	@Override
	public List<AccountTypeDTO> getAllAccountType() {
		List<AccountTypeDTO> listCMNDWrapper = new ArrayList<AccountTypeDTO>();
		try {
			List<AccountType> listAccountType = accountTypeRepository.findAll();

			if (listAccountType != null) {

				for (AccountType accountType : listAccountType) {
					AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
					BeanUtils.copyProperties(accountType, accountTypeDTO);

					listCMNDWrapper.add(accountTypeDTO);
				}

			}
		} catch (Exception e) {
			logger.error("Get all account type has error >>> " + e.getMessage(), e);
		}
		return listCMNDWrapper;
	}

	@Override
	public AccountTypeDTO getAccountTypeById(Integer accountTypeId) {
		AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
		try {
			Optional<AccountType> accountType = accountTypeRepository.findById(accountTypeId);
			if (accountType.isPresent()) {
				BeanUtils.copyProperties(accountType.get(), accountTypeDTO);

			}
		} catch (Exception e) {
			logger.error("Get account type by id has error >>> " + e.getMessage(), e);
		}
		return accountTypeDTO;
	}

	@Override
	public boolean createAccountType(AccountTypeDTO accountTypeDTO) {
		boolean response = false;
		try {
			AccountType accountType = new AccountType();
			accountType.setShortDescription(accountTypeDTO.getShortDescription());
			accountType.setLongDescription(accountTypeDTO.getLongDescription());

			accountTypeRepository.save(accountType);
			response = true;
		} catch (Exception e) {
			logger.error("Create account type has error >>> " + e.getMessage(), e);
		}
		return response;
	}

	@Override
	public boolean updateAccountType(AccountTypeDTO accountTypeDTO) {
		boolean response = false;
		try {
			Optional<AccountType> accountTypeDB = accountTypeRepository.findById(accountTypeDTO.getId());
			if (accountTypeDB.isPresent()) {
				AccountType accountType = accountTypeDB.get();
				accountType.setShortDescription(accountTypeDTO.getShortDescription());
				accountType.setLongDescription(accountTypeDTO.getLongDescription());

				accountTypeRepository.save(accountType);
				response = true;
			}
		} catch (Exception e) {
			logger.error("Update account type has error >>> " + e.getMessage(), e);
		}
		return response;
	}

	@Override
	public boolean deleteAccountType(Integer accountTypeId) {
		boolean response = false;
		try {
			accountTypeRepository.deleteById(accountTypeId);
			response = true;
		} catch (Exception e) {
			logger.error("Delete account type has error >>> " + e.getMessage(), e);
		}
		return response;
	}

}
