package com.codeWithUtkarsh.wallet.model.dto;

public class MoneyTransferDetails {

	private String toUserName;
	private String fromUserName;
	
	private Integer amount;
	
	public MoneyTransferDetails() {
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
