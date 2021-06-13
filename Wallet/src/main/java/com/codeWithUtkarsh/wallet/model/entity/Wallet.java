package com.codeWithUtkarsh.wallet.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="wallet")
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer walletId;
	
	@Column(nullable = false)
	private Integer walletBalance;
	
	@OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Transaction> transactions;
	
	public Wallet() {
	}
	
	public Wallet(Integer walletBalance) {
		super();
		this.walletBalance = walletBalance;
	}

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}

	public Integer getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(Integer walletBalance) {
		this.walletBalance = walletBalance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}
