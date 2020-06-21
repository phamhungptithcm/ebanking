package com.ebanking.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebanking.dto.AccountTypeDTO;

@Service
public interface IAccountType {
	public List<AccountTypeDTO> getAllAccountType();

	public AccountTypeDTO getAccountTypeById(Integer accountTypeId);

	public boolean createAccountType(AccountTypeDTO accountTypeDTO);

	public boolean updateAccountType(AccountTypeDTO accountTypeDTO);

	public boolean deleteAccountType(Integer accountTypeId);
}
