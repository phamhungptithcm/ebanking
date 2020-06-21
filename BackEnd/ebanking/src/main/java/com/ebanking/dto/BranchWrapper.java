package com.ebanking.dto;

public class BranchWrapper {
	private String id;

	private String branchName;

	private String placeGranted;

	private String contactNumber;

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

}
