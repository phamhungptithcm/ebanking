package com.ebanking.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebanking.dto.BranchDTO;
import com.ebanking.dto.CardDTO;
import com.ebanking.dto.JsonMessageDTO;
import com.ebanking.dto.OTPRequestDTO;
import com.ebanking.dto.TransactionHistoryResponseDTO;
import com.ebanking.dto.TransactionRequestDTO;
import com.ebanking.dto.TransferDTO;
import com.ebanking.entities.Branch;
import com.ebanking.entities.Card;
import com.ebanking.entities.Transaction;
import com.ebanking.repositories.CardRepository;
import com.ebanking.repositories.TransactionRepository;
import com.ebanking.services.ICardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Transactional
public class CardService implements ICardService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	private Logger logger = LoggerFactory.getLogger(CardService.class);

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public JsonMessageDTO getCardInfo(CardDTO request) throws Exception {
		JsonMessageDTO response = new JsonMessageDTO();
		try {
			Card card = cardRepository.findById(request.getCardNumber()).get();
			if (card != null) {
				CardDTO cardDTO = new CardDTO();
				BranchDTO branchDTO = null;
				Branch branch = card.getBranch();
				if (branch != null) {
					branchDTO = new BranchDTO();
					branchDTO.setBranchName(branch.getBranchName());
					branchDTO.setContactNumber(branch.getContactNumber());
					branchDTO.setPlaceGranted(branch.getPlaceGranted());
				}
				if (card.getAccount() != null) {
					cardDTO.setFullname(card.getAccount().getFirstName() + " " + card.getAccount().getLastName());
				}
				cardDTO.setCardNumber(card.getAccountNumber());
				cardDTO.setBalance(card.getBalance());
				cardDTO.setAvailableBalance(card.getAvailableBalance());
				cardDTO.setIssueDate(card.getIssueDate());
				cardDTO.setOverdraftLimit(card.getOverdraftLimit());
				cardDTO.setOverdraftTinterRestrate(card.getOverdraftTinterRestrate());
				cardDTO.setBranchDTO(branchDTO);

				response.setStatusRequest(true);
				response.setMessageStatus("Success");
				response.setJsonResponse(cardDTO);
			}
		} catch (Exception e) {
			response.setStatusRequest(false);
			response.setMessageStatus("Fail >>> " + e.getMessage());
			logger.error("Reqest get card info has error >>> " + e.getMessage(), e);
		}
		return response;
	}

	@Override
	public JsonMessageDTO getTransactionHistory(TransactionRequestDTO request) throws Exception {
		JsonMessageDTO response = new JsonMessageDTO();
		try {
			Transaction transaction = transactionRepository
					.findByCardAccountNumberOrderByTransactionDateDesc(request.getCardNumber());
			if (transaction != null) {
				TransactionHistoryResponseDTO historyDTO = new TransactionHistoryResponseDTO();

				historyDTO.setTransactionDate(transaction.getTransactionDate());
				historyDTO.setTransactionDescription(transaction.getTransactionDescription());
				historyDTO.setTransactionId(transaction.getTransactionId());
				historyDTO.setTransactionMessage(transaction.getTransactionMessage());
				historyDTO.setTransactionStatus(transaction.getTransactionStatus());

				response.setStatusRequest(true);
				response.setMessageStatus("Success");
				response.setJsonResponse(historyDTO);
			}
		} catch (Exception e) {
			response.setStatusRequest(false);
			response.setMessageStatus("Fail >>> " + e.getMessage());
			logger.error("Reqest get card info has error >>> " + e.getMessage(), e);
		}
		return response;
	}

	@Override
	public JsonMessageDTO transfers(TransferDTO request) throws Exception {

		JsonMessageDTO response = new JsonMessageDTO();
		try {
			Card receiver = cardRepository.findById(request.getCardNumberReceiver()).get();
			Card transfer = cardRepository.findById(request.getCardNumberTransfer()).get();
			if (receiver != null && transfer != null) {
				Transaction transaction = new Transaction();
				transaction.setCard(transfer);
				transaction.setCardFrom(transfer.getAccountNumber());
				transaction.setCardTo(receiver.getAccountNumber());
				transaction.setTransactionDate(new Date());
				transaction.setTransactionId("TI" + new Date().getTime());
				transaction.setTransactionMessage(request.getMessage());
				transaction.setTransactionAmount(request.getTransferAmount());

				/// setup infor response for user
				TransferDTO transferDTO = new TransferDTO();
				transferDTO.setBranchNameReceiver(
						receiver.getBranch() != null ? receiver.getBranch().getBranchName() : "");
				transferDTO.setBranchNameReceiver(
						transfer.getBranch() != null ? transfer.getBranch().getBranchName() : "");
				transferDTO.setNameReceiver(transfer.getAccount() != null
						? transfer.getAccount().getFirstName() + " " + transfer.getAccount().getLastName()
						: "");
				transferDTO.setNameReceiver(receiver.getAccount() != null
						? receiver.getAccount().getFirstName() + " " + receiver.getAccount().getLastName()
						: "");
				transferDTO.setTransactionId(transaction.getTransactionId());
				transferDTO.setTransactionDate(new Date());
				transferDTO.setTransferAmount(request.getTransferAmount());
				transferDTO.setMessage(request.getMessage());

				if (request.getTransferAmount() < transfer.getBalance()) {
					transaction.setTransactionStatus(true);

					response.setStatusRequest(true);
					response.setMessageStatus("Success");
					response.setJsonResponse(transferDTO);
				} else {
					response.setStatusRequest(false);
					response.setMessageStatus("Balance in the account is not enough");
					response.setJsonResponse(transferDTO);

					transaction.setTransactionDescription("Account Balance is not enough.");
					transaction.setTransactionStatus(false);

				}
				transactionRepository.save(transaction);
			} else {
				throw new Exception("Trading account information does not exist.");
			}
		} catch (Exception e) {
			response.setStatusRequest(false);
			response.setMessageStatus("Fail >>> " + e.getMessage());
			logger.error("Reqest get card info has error >>> " + e.getMessage(), e);
		}

		return response;
	}

	@Override
	public JsonMessageDTO getCardsByAccountId(String accountId) throws Exception {
		JsonMessageDTO response = new JsonMessageDTO();
		try {
			List<Card> cards = cardRepository.findByAccountId(accountId);
			List<CardDTO> cardDTOs = new ArrayList<CardDTO>();
			for (Card card : cards) {
				BranchDTO branchDTO = null;
				Branch branch = card.getBranch();
				if (branch != null) {
					branchDTO = new BranchDTO();
					branchDTO.setBranchName(branch.getBranchName());
					branchDTO.setContactNumber(branch.getContactNumber());
					branchDTO.setPlaceGranted(branch.getPlaceGranted());
				}
				
				CardDTO cardDTO = new CardDTO();
				
				cardDTO.setBalance(card.getBalance());
				cardDTO.setCardNumber(card.getAccountNumber());
				cardDTO.setFullname(card.getAccount() != null
						? card.getAccount().getFirstName() + " " + card.getAccount().getLastName()
						: "");
				cardDTO.setIssueDate(card.getIssueDate());
				cardDTO.setOverdraftLimit(card.getOverdraftLimit());
				cardDTO.setOverdraftTinterRestrate(card.getOverdraftTinterRestrate());
				cardDTO.setBranchDTO(branchDTO);
				
				cardDTOs.add(cardDTO);
			}
			response.setStatusRequest(true);
			response.setMessageStatus("Success");
			response.setJsonResponse(cardDTOs);
		} catch (Exception e) {
			response.setStatusRequest(false);
			response.setMessageStatus("Fail >>> " + e.getMessage());
			logger.error("Reqest get card info has error >>> " + e.getMessage(), e);
		}
		return response;
	}

	@Override
	public JsonMessageDTO sendSMS(OTPRequestDTO request) throws Exception {
		
		return null;
	}

	@Override
	public JsonMessageDTO sendMail(OTPRequestDTO request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonMessageDTO downloadTransactionHistory() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonMessageDTO verifyCode(OTPRequestDTO request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
