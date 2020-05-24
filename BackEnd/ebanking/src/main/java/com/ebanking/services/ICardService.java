package com.ebanking.services;

import org.springframework.stereotype.Service;

import com.ebanking.dto.CardDTO;
import com.ebanking.dto.JsonMessageDTO;
import com.ebanking.dto.TransferDTO;

@Service
public interface ICardService {
	public JsonMessageDTO getCardInfo(CardDTO request) throws Exception;
	
	public JsonMessageDTO getTransactionHistory(CardDTO request) throws Exception;
	
	public JsonMessageDTO transfers(TransferDTO request) throws Exception;
	
	public JsonMessageDTO getCardsByAccountId(String accountId) throws Exception;
}
