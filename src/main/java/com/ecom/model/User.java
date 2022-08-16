package com.ecom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;


@Entity
@Table(name = "user")
@SecondaryTable(name = "user_roles")
public class User {

	//Model for Users; Allows setup of account in database
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer user_id;
	
	@Column(name = "first_name")
	private String fname;
	
	@Column(name = "last_name")
	private String lname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email_verified")
	private Integer verify;
	
	@Column(table = "user_roles")
	private Integer role_id = 1;

	//Getter and Setter
	public Integer getId() {
		return user_id;
	}

	public void setId(Integer id) {
		this.user_id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getVerify() {
		return verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
	}
	
	public Integer getRole() {
		return role_id;
	}
	
	public void setRole(Integer role_id) {
		this.role_id = role_id;
	}
//Constructor
	public User(String fname, String lname, String email, String username, String password) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.username = username;
		this.password = password;
	
	}
	
	public User() {
		
	}
	
	
}

