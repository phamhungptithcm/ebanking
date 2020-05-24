package com.ebanking.dto;

import java.util.Date;

public class CardDTO {
	private String cardNumber;
	private double balance;
	private double availableBalance;
	private String overdraftTinterRestrate;
	private String overdraftLimit;
	private Date issueDate;
	private String fullname;
	private BranchDTO branchDTO;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getOverdraftTinterRestrate() {
		return overdraftTinterRestrate;
	}

	public void setOverdraftTinterRestrate(String overdraftTinterRestrate) {
		this.overdraftTinterRestrate = overdraftTinterRestrate;
	}

	public String getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(String overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public BranchDTO getBranchDTO() {
		return branchDTO;
	}

	public void setBranchDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
	}

}
