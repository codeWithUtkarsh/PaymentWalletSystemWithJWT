package com.codeWithUtkarsh.wallet.model.dto;

import java.util.List;
import java.util.Map;

public class PassbookDetails {

	private List<String> columnHeaders;
	private List<Map<String, Object>> passbookData;
	private Integer currentBalance;
	
	public PassbookDetails() {
	}
	public List<String> getColumnHeaders() {
		return columnHeaders;
	}
	public void setColumnHeaders(List<String> columnHeaders) {
		this.columnHeaders = columnHeaders;
	}
	public List<Map<String, Object>> getPassbookData() {
		return passbookData;
	}
	public void setPassbookData(List<Map<String, Object>> passbookData) {
		this.passbookData = passbookData;
	}
	public Integer getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(Integer currentBalance) {
		this.currentBalance = currentBalance;
	}
}
