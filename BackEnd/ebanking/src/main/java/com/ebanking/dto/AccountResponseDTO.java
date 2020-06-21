package com.ebanking.dto;

public class AccountResponseDTO {

	private String lastName;
	private String firstName;
	private String address;
	private String city;
	private String phoneNum;
	private CMNDDTO cmndDTO;
	private String email;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public CMNDDTO getCmndDTO() {
		return cmndDTO;
	}

	public void setCmndDTO(CMNDDTO cmndDTO) {
		this.cmndDTO = cmndDTO;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
