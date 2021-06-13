package com.codeWithUtkarsh.wallet.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.codeWithUtkarsh.wallet.config.exception.TransactionServiceException;
import com.codeWithUtkarsh.wallet.model.entity.Transaction;
import com.codeWithUtkarsh.wallet.repository.TransactionRepository;
import com.codeWithUtkarsh.wallet.services.TransactionService;

@Component
public class TransactionServiceImpl implements TransactionService{

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Optional<Transaction> createTransaction(Transaction transaction) throws TransactionServiceException
	{
		try
		{
			if(transaction != null)
			{
				LOGGER.debug("Transaction recorded at:{}", System.currentTimeMillis());
				return Optional.of(transactionRepository.save(transaction));
			}
		}catch (Exception e) {
			throw new TransactionServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return Optional.empty();
	}

	@Override
	public Optional<Transaction> updateTransaction(Transaction transaction) throws TransactionServiceException
	{
		try
		{
			if(transaction != null)
			{
				LOGGER.debug("Transaction updated at:{}", System.currentTimeMillis());
				return Optional.of(transactionRepository.save(transaction));
			}
		}catch (Exception e) {
			throw new TransactionServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return Optional.empty();
	}

	@Override
	public Optional<Transaction> deleteTransaction(String transactionId) throws TransactionServiceException
	{
		try
		{
			Optional<Transaction> fetchedTransaction = transactionRepository.findById(transactionId);
			if(fetchedTransaction.isPresent())
			{
				LOGGER.debug("Transaction deleted at:{}", System.currentTimeMillis());
				transactionRepository.delete(fetchedTransaction.get());
			}
		}catch (Exception e) {
			throw new TransactionServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return Optional.empty();
	}

	@Override
	public Optional<Transaction> getTransaction(String transactionId) throws TransactionServiceException
	{
		try
		{
			if(transactionId != null)
			{
				LOGGER.debug("Transaction retrieval at:{}", System.currentTimeMillis());
				return transactionRepository.findById(transactionId);
			}
		}catch (Exception e) {
			throw new TransactionServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return Optional.empty();
	}

}
