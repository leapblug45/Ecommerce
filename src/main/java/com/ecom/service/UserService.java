package com.ecom.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.ecom.model.User;
import com.ecom.pojo.LoginDto;
import com.ecom.pojo.LoginResponseDto;
import com.ecom.pojo.RegisterDto;
import com.ecom.pojo.ResponseDto;

@Service
public interface UserService {

	//signup method for Registration process
	public ResponseDto signUp(RegisterDto registerDto, String siteURL) throws UnsupportedEncodingException, MessagingException;
	
	//login method
	public LoginResponseDto login(LoginDto loginDto);
	
	//send verification email
	public void sendVerificationEmail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;

	//login and verify from verification email sent
	public LoginResponseDto verify(LoginDto loginDto);
}