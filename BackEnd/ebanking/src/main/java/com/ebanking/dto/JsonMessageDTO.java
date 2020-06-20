package com.ebanking.dto;

public class JsonMessageDTO {
	private boolean statusRequest;
	private Object jsonResponse;
	private String messageStatus;
	
	public boolean isStatusRequest() {
		return statusRequest;
	}
	public void setStatusRequest(boolean statusRequest) {
		this.statusRequest = statusRequest;
	}
	public Object getJsonResponse() {
		return jsonResponse;
	}
	public void setJsonResponse(Object jsonResponse) {
		this.jsonResponse = jsonResponse;
	}
	public String getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	
	
}
