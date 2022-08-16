package com.ecom.pojo;

public class ResponseDto {

	private String status;
	private String message;
	
	public ResponseDto(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	//Get Set
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
