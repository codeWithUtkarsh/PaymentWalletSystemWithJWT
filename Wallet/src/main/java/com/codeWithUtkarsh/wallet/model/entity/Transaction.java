package com.codeWithUtkarsh.wallet.model.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.codeWithUtkarsh.wallet.model.TransactionStatus;
import com.codeWithUtkarsh.wallet.model.TransactionType;

@Entity
@Table(name="transaction")
public class Transaction
{
	@Id
	private String transactionId;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	
	@Enumerated(EnumType.STRING)
	private TransactionStatus status;
	
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	@Column
	private Integer amount;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wallet_id", nullable = false)
	private Wallet wallet;
	
	public Transaction() {
	}
	
	public Transaction(String transactionId, TransactionStatus status, TransactionType type, Wallet wallet, Date transactionDate, Integer amount) {
		super();
		this.transactionId = transactionId;
		this.status = status;
		this.type = type;
		this.wallet = wallet;
		this.transactionDate = transactionDate;
		this.amount = amount;
	}
	
	public Transaction(String transactionId, TransactionStatus status, TransactionType type, Date transactionDate) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.status = status;
		this.type = type;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
