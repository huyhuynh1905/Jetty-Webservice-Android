package com.huyhuynh.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginRequest {
	private String email;
	private String pass;
	
	public LoginRequest() {
		super();
	}
	public LoginRequest(String email, String pass) {
		super();
		this.email = email;
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
