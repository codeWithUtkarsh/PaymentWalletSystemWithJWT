package com.codeWithUtkarsh.wallet.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer userId;
	
	@Column(nullable = false, unique = true)
	private String userName;
	
	@Column(nullable = false)
	@JsonIgnore
	private String password;
	
	@Column
	private String emailId;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "walletid", referencedColumnName = "walletid")
	private Wallet wallet;
	
	public User() {
	}
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
}
