package com.ebanking.services;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ebanking.dto.AccountCreationWrapper;
import com.ebanking.dto.AccountRequestDTO;
import com.ebanking.dto.JsonMessageDTO;
import com.ebanking.entities.Account;

@Service
public interface IAccountService {
	public JsonMessageDTO login(AccountRequestDTO request, HttpSession session) throws Exception;

	public void logout(HttpSession session) throws Exception;

	public JsonMessageDTO getAccountInfo(AccountRequestDTO request) throws Exception;

	public JsonMessageDTO forgotPassword(AccountRequestDTO request)  throws Exception;
	
	public boolean createAccount(AccountCreationWrapper accountCreationWrapper);

	public JsonMessageDTO updatePassword(AccountRequestDTO request);
}
