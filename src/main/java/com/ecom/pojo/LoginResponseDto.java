package com.ecom.pojo;

public class LoginResponseDto {
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public LoginResponseDto(String status) {
		this.status = status;
	}
}
