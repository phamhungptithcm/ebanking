package com.ebanking.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// Lưu thông tin thẻ atm
@Entity
@Table(name = "CARD")
public class Card {
	
	@Id
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	
	@Column(name = "BALANCE")
	private double balance;
	
	@Column(name = "AVAILABLE_BALANCE")
	private double availableBalance;
		
	@Column(name = "OVERDRAF_TINTER_RESTRATE")
	private String overdraftTinterRestrate;
	
	@Column(name = "OVERDRAFT_LIMIT")
	private String overdraftLimit;
	
	@Column(name = "ISSUE_DATE")
	private Date issueDate;
	
	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;
	
	
	@ManyToOne
	@JoinColumn(name="ACCOUNT_TYPE_ID")
	private AccountType accountType;
	
	@ManyToOne
	@JoinColumn(name="BRANCH_ID")
	private Branch branch;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	
}
