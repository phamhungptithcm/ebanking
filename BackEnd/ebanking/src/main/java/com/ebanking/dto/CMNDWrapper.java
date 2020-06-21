package com.ebanking.dto;

import java.util.Date;

public class CMNDWrapper {

	private String id;

	private Date dateGranted;

	private String placeGranted;

	private String fullname;

	private Date dateOfBirth;

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
}
