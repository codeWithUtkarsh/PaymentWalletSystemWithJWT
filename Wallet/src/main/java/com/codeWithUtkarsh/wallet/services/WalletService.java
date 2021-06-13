package com.codeWithUtkarsh.wallet.services;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.codeWithUtkarsh.wallet.config.exception.WalletServiceException;
import com.codeWithUtkarsh.wallet.model.dto.MoneyTransferDetails;
import com.codeWithUtkarsh.wallet.model.dto.PassbookDetails;
import com.codeWithUtkarsh.wallet.model.entity.Wallet;

@Component
public interface WalletService {

	public Optional<Wallet> createWallet(Wallet wallet) throws WalletServiceException;
	public Optional<Wallet> updateWallet(Wallet wallet) throws WalletServiceException;
	public Optional<Wallet> deleteWallet(Integer walletId) throws WalletServiceException;
	public Optional<Wallet> getWallet(Integer walletId) throws WalletServiceException;
	public void addMoney(String userName, Integer moneyToBeAdded) throws WalletServiceException;
	public String payToOtherUser(MoneyTransferDetails moneyTransferDetails) throws WalletServiceException;
	public PassbookDetails viewPassbook(String userName) throws WalletServiceException;
	public String getTransactionStatus(String transactionId) throws WalletServiceException;
	
}
