package com.ecom.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.pojo.LoginDto;
import com.ecom.pojo.LoginResponseDto;
import com.ecom.pojo.RegisterDto;
import com.ecom.pojo.ResponseDto;
import com.ecom.repository.UserRepository;
import com.ecom.service.UserServiceImpl;

import com.ecom.model.User;

@RequestMapping("user")
@RestController
public class UserController {

	@Autowired
	UserServiceImpl userService;
	
	@Autowired UserRepository userRepo;
	
	//End-points for signup/signin
	
	@PostMapping("/register")
	public ResponseDto signup(@RequestBody RegisterDto registerDto, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		return userService.signUp(registerDto, getSiteUrl(request));
	}
	
	private String getSiteUrl(HttpServletRequest request) {
		// Generate a string for site url
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

	//Log-in
	
	@PostMapping("/login")
	public LoginResponseDto login(@RequestBody LoginDto loginDto) {
		return userService.login(loginDto);
	}
	
	//Email verification
	@PostMapping("/verify")
	public LoginResponseDto verify(@RequestBody LoginDto loginDto) {
		return userService.verify(loginDto);
	}
	
	//Return a list of users registered
	@GetMapping("/admin/get")
	public List<User> getUsers() {
		return  userRepo.findAll();
	}
}
	
