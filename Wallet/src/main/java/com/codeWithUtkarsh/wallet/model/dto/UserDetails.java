package com.codeWithUtkarsh.wallet.model.dto;

public class UserDetails {

	private String userName;
	private String emailId;
	
	public UserDetails() {
	}

	public UserDetails(String userName, String emailId) {
		super();
		this.userName = userName;
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
}
