package com.ebanking.services;

import org.springframework.stereotype.Service;

import com.ebanking.dto.CardDTO;
import com.ebanking.dto.JsonMessageDTO;
import com.ebanking.dto.OTPRequestDTO;
import com.ebanking.dto.TransactionRequestDTO;
import com.ebanking.dto.TransferDTO;

@Service
public interface ICardService {
	public JsonMessageDTO getCardInfo(CardDTO request) throws Exception;
	
	public JsonMessageDTO getTransactionHistory(TransactionRequestDTO request) throws Exception;
	
	public JsonMessageDTO transfers(TransferDTO request) throws Exception;
	
	public JsonMessageDTO getCardsByAccountId(String accountId) throws Exception;

	public JsonMessageDTO sendSMS(OTPRequestDTO request) throws Exception;
	
	public JsonMessageDTO sendMail(OTPRequestDTO request) throws Exception;
	
	public JsonMessageDTO downloadTransactionHistory() throws Exception;

	public JsonMessageDTO verifyCode(OTPRequestDTO request)  throws Exception;
}
