package com.ecom.service;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ecom.exception.AuthenticationFailedException;
import com.ecom.exception.CustomException;
import com.ecom.model.User;
import com.ecom.pojo.LoginDto;
import com.ecom.pojo.LoginResponseDto;
import com.ecom.pojo.RegisterDto;
import com.ecom.pojo.ResponseDto;
import com.ecom.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	JavaMailSender mailSender;

	@Override
	public ResponseDto signUp(RegisterDto registerDto, String siteURL) throws UnsupportedEncodingException, MessagingException {
		
		//Save User in user table/Check if user info is already in database
		if (Objects.nonNull(userRepo.findByEmail(registerDto.getEmail()))) {
			
			throw new CustomException("user already registered");
		}
		
		User user = new User(registerDto.getFname(), registerDto.getLname(), registerDto.getEmail(), registerDto.getUsername(), registerDto.getPassword());
		
		try {
			user.setVerify(0);
			//Save
			userRepo.save(user);
			sendVerificationEmail(user, siteURL);
		} catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		ResponseDto responseDto = new ResponseDto("success", "User Account created");
		
		return responseDto;
	}
	
	//Method to send verification email, once user clicks verify update database with a non-zero int in email_verify to show they have verified their account 
	@Override
	public void sendVerificationEmail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException {
		String toAddress = user.getEmail();
		String fromAddress = "E-commerce+MDH@test.com";
		String senderName = "HCL";
		String subject = "Please verify account creation";
		String content = "Dear [[name]],<br>"
				+ "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
				+"Thank you!<br>";
		
		//Create MimeSender
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);
		
		content = content.replace("[[name]]", user.getFname());
		String verifyURL = siteURL + "/verify";
		
		content = content.replace("[[URL]]", verifyURL);
		
		helper.setText(content, true);
		
		mailSender.send(message);
	}
	//Login method
	@Override
	public LoginResponseDto login(LoginDto loginDto) {
		//locate user via email
		User user = userRepo.findByEmail(loginDto.getEmail());
		
		if (Objects.isNull(user)) {
			throw new AuthenticationFailedException("Email is not registered");
		}
		
		//compare input password
		
			//Password does not match
			if (!user.getPassword().equals(loginDto.getPassword())) {
				throw new AuthenticationFailedException("Incorrect Password");
			}
			else {
				//Password match
				LoginResponseDto logResponseDto = new LoginResponseDto("Successfully Authenticated " + "User Role is: " + user.getRole());
				return logResponseDto;
			}
	
		
	}
	//Verify method [Login method that changes verify tinyint value to show it's verified
	@Override
	public LoginResponseDto verify(LoginDto loginDto) {
		//locate user via email
		User user = userRepo.findByEmail(loginDto.getEmail());
		
		if (Objects.isNull(user)) {
			throw new AuthenticationFailedException("Email is not registered");
		}
		
		//compare input password
		
			//Password does not match
			if (!user.getPassword().equals(loginDto.getPassword())) {
				throw new AuthenticationFailedException("Incorrect Password");
			}
			else {
				//Password match
				user.setVerify(1);
				userRepo.save(user);
				LoginResponseDto logResponseDto = new LoginResponseDto("Successfully Authenticated and Verified");
				return logResponseDto;
			}
	
		
	}
}
