package com.ebanking.dto;

import java.util.Date;

public class TransferDTO {
	private String cardNumberReceiver;
	
	private String nameReceiver;
	
	private String cardNumberTransfer;
	
	private String nameTransfer;
	
	private Double transferAmount;
	
	private String branchNameReceiver;
	
	private String message;
	
	private String transactionId;
	
	private Date transactionDate;
	
	private boolean status;
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCardNumberReceiver() {
		return cardNumberReceiver;
	}

	public void setCardNumberReceiver(String cardNumberReceiver) {
		this.cardNumberReceiver = cardNumberReceiver;
	}

	public String getCardNumberTransfer() {
		return cardNumberTransfer;
	}

	public void setCardNumberTransfer(String cardNumberTransfer) {
		this.cardNumberTransfer = cardNumberTransfer;
	}

	public Double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(Double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNameReceiver() {
		return nameReceiver;
	}

	public void setNameReceiver(String nameReceiver) {
		this.nameReceiver = nameReceiver;
	}

	public String getNameTransfer() {
		return nameTransfer;
	}

	public void setNameTransfer(String nameTransfer) {
		this.nameTransfer = nameTransfer;
	}

	public String getBranchNameReceiver() {
		return branchNameReceiver;
	}

	public void setBranchNameReceiver(String branchNameReceiver) {
		this.branchNameReceiver = branchNameReceiver;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
}
