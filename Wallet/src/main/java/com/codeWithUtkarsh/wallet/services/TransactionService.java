package com.codeWithUtkarsh.wallet.services;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.codeWithUtkarsh.wallet.config.exception.TransactionServiceException;
import com.codeWithUtkarsh.wallet.model.entity.Transaction;

@Component
public interface TransactionService {

	public Optional<Transaction> createTransaction(Transaction transaction) throws TransactionServiceException;
	public Optional<Transaction> updateTransaction(Transaction transaction) throws TransactionServiceException;
	public Optional<Transaction> deleteTransaction(String transactionId) throws TransactionServiceException;
	public Optional<Transaction> getTransaction(String transactionId) throws TransactionServiceException;
}
