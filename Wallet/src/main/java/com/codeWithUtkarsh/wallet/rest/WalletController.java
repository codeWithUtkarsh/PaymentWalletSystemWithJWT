package com.codeWithUtkarsh.wallet.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeWithUtkarsh.wallet.config.exception.WalletServiceException;
import com.codeWithUtkarsh.wallet.model.dto.MoneyTransferDetails;
import com.codeWithUtkarsh.wallet.model.dto.PassbookDetails;
import com.codeWithUtkarsh.wallet.services.WalletService;

@RestController
public class WalletController {
		
	@Autowired
	private WalletService walletService;
	
	@GetMapping("/addMoney")
	public void addMoney(@RequestParam Integer moneyToBeAdded, @RequestParam String userName) throws WalletServiceException
	{
		walletService.addMoney(userName, moneyToBeAdded);
	}
	
	@PostMapping("/payToOtherUser")
	public String payToOtherUser(@RequestBody MoneyTransferDetails moneyTransferDetails) throws WalletServiceException
	{
		return walletService.payToOtherUser(moneyTransferDetails);
	}
	
	@GetMapping("/viewPassbook")
	public PassbookDetails viewPassbook(@RequestParam String userName) throws WalletServiceException
	{
		return walletService.viewPassbook(userName);
	}
	
	@GetMapping("/viewTransactionStatus")
	public String viewTransactionStatus(@RequestParam String transactionid) throws WalletServiceException
	{
		return walletService.getTransactionStatus(transactionid);
	}
	
}
