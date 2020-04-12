package com.ebanking.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// Lưu chi nhánh ngân hàng đã đăng kí làm thẻ
@Entity
@Table(name = "BRANCH")
public class Branch {
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "BRANCH_NAME")
	private String branchName;
	
	@Column(name = "ADDRESS")
	private String placeGranted;
	
	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "branch")
	private List<Card> cards;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getPlaceGranted() {
		return placeGranted;
	}

	public void setPlaceGranted(String placeGranted) {
		this.placeGranted = placeGranted;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	
}
