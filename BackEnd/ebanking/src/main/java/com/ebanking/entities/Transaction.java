package com.ebanking.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {
	
	@Id
	@Column(name ="TRANSACTION_ID")
	private String transactionId;
	
	@Column(name ="TRANSACTION_DESCRIPTION")
	private String transactionDescription;
	
	@Column(name ="TRANSACTION_DATE")
	private Date transactionDate;
	
	@Column(name ="TRANSACTION_MESSAGE")
	private String transactionMessage;
	
	@Column(name ="TRANSACTION_STATUS")
	private Boolean transactionStatus;
	
	@Column(name ="CARD_FROM")
	private String cardFrom;
	
	@Column(name ="CARD_TO")
	private String cardTo;
	
	@Column(name ="TRANSACTION_AMOUT")
	private Double transactionAmount;
	
	@ManyToOne
	@JoinColumn(name="CARD_ID")
	private Card card;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionMessage() {
		return transactionMessage;
	}

	public void setTransactionMessage(String transactionMessage) {
		this.transactionMessage = transactionMessage;
	}

	public Boolean getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(Boolean transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getCardFrom() {
		return cardFrom;
	}

	public void setCardFrom(String cardFrom) {
		this.cardFrom = cardFrom;
	}

	public String getCardTo() {
		return cardTo;
	}

	public void setCardTo(String cardTo) {
		this.cardTo = cardTo;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	
}
