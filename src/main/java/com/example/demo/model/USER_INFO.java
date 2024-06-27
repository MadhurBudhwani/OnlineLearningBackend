package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class USER_INFO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	
	private String username;
	private String password;
	private String userRole;
	private String email;
	
	public int getUserId() {
		return user_id;
	}
	
	public String getUserName() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getUserRole() {
		return userRole;
		
	}
	public String getEmail() {
		return email;
		
	}
	
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public void setUserId(int id) {
		this.user_id=id;
	}
	public void setUserName(String name) {
		this.username=name;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public void setUserRole(String role) {
		this.userRole=role;
	}
}
