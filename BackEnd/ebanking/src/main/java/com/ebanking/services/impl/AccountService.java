package com.ebanking.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ebanking.dto.AccountCreationWrapper;
import com.ebanking.dto.AccountRequestDTO;
import com.ebanking.dto.AccountResponseDTO;
import com.ebanking.dto.CMNDDTO;
import com.ebanking.dto.CMNDWrapper;
import com.ebanking.dto.JsonMessageDTO;
import com.ebanking.entities.Account;
import com.ebanking.entities.CMND;
import com.ebanking.entities.Role;
import com.ebanking.repositories.AccountRepository;
import com.ebanking.repositories.RoleRepository;
import com.ebanking.services.IAccountService;

@Component
@Transactional
public class AccountService implements IAccountService, UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encrypt;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CMNDService cmndService;
	
	@Autowired
	private RoleRepository roleRepository;

	private Logger logger = LoggerFactory.getLogger(AccountService.class);

	private static final String LOGIN_SUCCESS = "Logged in successfully";
	private static final String LOGIN_FAIL = "Logged in failed";
	private static final String COMMON_MESSAGE_SUCCESS = "Success";
	private static final String COMMON_MESSAGE_FAIL = "Fail";

	@Override
	public JsonMessageDTO login(AccountRequestDTO request, HttpSession session) throws Exception {
		JsonMessageDTO response = new JsonMessageDTO();
		response.setStatusRequest(false);
		response.setMessageStatus(LOGIN_FAIL);
		Account account = accountRepository.findById(request.getUsername()).get();
		if (account != null) {
			if (encrypt.matches(request.getPassword(), account.getPassword())) {
				response.setStatusRequest(true);
				response.setMessageStatus(LOGIN_SUCCESS);
				response.setJsonResponse(request);
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
	public JsonMessageDTO getAccountInfo(AccountRequestDTO request) throws Exception {
		JsonMessageDTO response = new JsonMessageDTO();
		Account account = accountRepository.findById(request.getUsername()).get();
		if (account != null) {
			AccountResponseDTO dto = new AccountResponseDTO();
			CMNDDTO cmndDTO = new CMNDDTO();
			cmndDTO.setId(account.getIdCard().getId());
			cmndDTO.setDateGranted(account.getIdCard().getDateGranted());
			cmndDTO.setDateOfBirth(account.getIdCard().getDateOfBirth());
			cmndDTO.setPlaceGranted(account.getIdCard().getPlaceGranted());
			dto.setAccountId(account.getId());
			dto.setFirstName(account.getFirstName());
			dto.setLastName(account.getLastName());
			dto.setAddress(account.getAddress());
			dto.setCity(account.getCity());
			dto.setEmail(account.getEmail());
			dto.setPhoneNum(account.getPhoneNum());
			dto.setCmndDTO(cmndDTO);

			response.setStatusRequest(true);
			response.setMessageStatus(COMMON_MESSAGE_SUCCESS);
			response.setJsonResponse(dto);

			logger.info("Request getAccountInfo  status >>> Done");
		} else {
			response.setStatusRequest(false);
			response.setMessageStatus(COMMON_MESSAGE_FAIL);
		}

		return response;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account user = accountRepository.findById(username).get();
		if (user == null) {
			return null;
		}
		return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(),this.getAuthority(user.getRole().getRoleName()));
	}
	
	private List<SimpleGrantedAuthority> getAuthority(String userRole) {
		
		SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + userRole.toUpperCase());
		return Arrays.asList(role);
	}

	@Override
	public JsonMessageDTO forgotPassword(AccountRequestDTO request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean createAccount(AccountCreationWrapper accountCreationWrapper) {
		boolean result = false;

		try {

			CMNDWrapper cmndWrapper = cmndService.getCMNDById(accountCreationWrapper.getCmnd().getId());

			if (cmndWrapper == null || cmndWrapper.getId() == null) {
				CMND cmnd = cmndService.createCMND(accountCreationWrapper.getCmnd());

				Optional<Role> role = roleRepository.findById(accountCreationWrapper.getRole().getRoleId());
				if (role.isPresent()) {
					Account account = new Account();
					account.setId(accountCreationWrapper.getId());
					account.setFirstName(accountCreationWrapper.getFirstName());
					account.setLastName(accountCreationWrapper.getLastName());
					account.setAddress(accountCreationWrapper.getAddress());
					account.setCity(accountCreationWrapper.getCity());
					account.setEmail(accountCreationWrapper.getEmail());
					account.setPhoneNum(accountCreationWrapper.getPhoneNum());
					account.setPassword(encrypt.encode(accountCreationWrapper.getPassword()));
					account.setRole(role.get());
					account.setIdCard(cmnd);

					account = accountRepository.save(account);
					result = true;
				}
			}

		} catch (Exception e) {
			logger.error("create account has error >>> " + e.getMessage(), e);
		}
		return result;
	}
}
