package com.ebanking.services.impl;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebanking.dto.AccountDTO;
import com.ebanking.dto.CMNDDTO;
import com.ebanking.dto.JsonMessageDTO;
import com.ebanking.entities.Account;
import com.ebanking.repositories.AccountRepository;
import com.ebanking.services.IAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Transactional
public class AccountService implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	private ObjectMapper objectMapper = new ObjectMapper();

	private Logger logger = LoggerFactory.getLogger(AccountService.class);

	private static final String LOGIN_SUCCESS = "Logged in successfully";
	private static final String LOGIN_FAIL = "Logged in failed";
	private static final String COMMON_MESSAGE_SUCCESS = "Success";
	private static final String COMMON_MESSAGE_FAIL = "Fail";

	@Override
	public JsonMessageDTO login(AccountDTO request, HttpSession session) throws Exception {
		JsonMessageDTO response = new JsonMessageDTO();
		response.setStatusRequest(false);
		response.setMessageStatus(LOGIN_FAIL);
		Account account = accountRepository.findById(request.getUsername()).get();
		if (account != null) {
			if (request.getPassword().equals(account.getPassword())) {
				response.setStatusRequest(true);
				response.setMessageStatus(LOGIN_SUCCESS);
				response.setJsonResponse(objectMapper.writeValueAsString(request));

				// set to session
				session.setAttribute("currentUser", request);

				logger.info("Login in successfully with request >>>" + request.getUsername());

			} else {
				logger.info("Login in failed with request >>>" + request.getUsername());
			}
		} else {
			logger.info("Login in failed with request >>>" + request.getUsername());
		}

		return response;
	}

	@Override
	public void logout(HttpSession session) throws Exception {
		session.removeAttribute("currentUser");
	}

	@Override
	public JsonMessageDTO getAccountInfo(AccountDTO request) throws Exception {
		JsonMessageDTO response = new JsonMessageDTO();
		Account account = accountRepository.findById(request.getUsername()).get();
		if (account != null) {
			CMNDDTO cmndDTO = new CMNDDTO();
			cmndDTO.setId(account.getIdCard().getId());
			cmndDTO.setDateGranted(account.getIdCard().getDateGranted());
			cmndDTO.setDateOfBirth(account.getIdCard().getDateOfBirth());
			cmndDTO.setPlaceGranted(account.getIdCard().getPlaceGranted());

			request.setFirstName(account.getFirstName());
			request.setLastName(account.getLastName());
			request.setAddress(account.getAddress());
			request.setCity(account.getCity());
			request.setEmail(account.getEmail());
			request.setPhoneNum(account.getPhoneNum());
			request.setCmndDTO(cmndDTO);

			response.setStatusRequest(true);
			response.setMessageStatus(COMMON_MESSAGE_SUCCESS);
			response.setJsonResponse(objectMapper.writeValueAsString(request));

			logger.info("Request getAccountInfo  status >>> Done");
		} else {
			response.setStatusRequest(false);
			response.setMessageStatus(COMMON_MESSAGE_FAIL);
		}

		return response;
	}

}
