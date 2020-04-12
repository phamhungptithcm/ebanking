package com.ebanking.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


// Lưu thông tin chứng minh nhân dân đăng kí làm thẻ
@Entity
@Table(name = "IDCARD")
public class IdCard {
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "DATE_GRANTED")
	private Date dateGranted;
	
	@Column(name = "PLACE_GRANTED")
	private String placeGranted;
	
	@Column(name = "FULLNAME")
	private String fullname;
	
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "idCard")
	private Account account;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateGranted() {
		return dateGranted;
	}

	public void setDateGranted(Date dateGranted) {
		this.dateGranted = dateGranted;
	}

	public String getPlaceGranted() {
		return placeGranted;
	}

	public void setPlaceGranted(String placeGranted) {
		this.placeGranted = placeGranted;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
