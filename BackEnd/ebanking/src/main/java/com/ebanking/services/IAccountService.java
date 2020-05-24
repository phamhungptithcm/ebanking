package com.ebanking.services;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ebanking.dto.AccountDTO;
import com.ebanking.dto.JsonMessageDTO;

@Service
public interface IAccountService {
	public JsonMessageDTO login(AccountDTO request, HttpSession session) throws Exception;

	public void logout(HttpSession session) throws Exception;

	public JsonMessageDTO getAccountInfo(AccountDTO request) throws Exception;
}
