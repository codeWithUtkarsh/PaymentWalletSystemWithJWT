package com.codeWithUtkarsh.wallet.services.impl;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.codeWithUtkarsh.wallet.config.exception.PaymentGatewayServiceException;
import com.codeWithUtkarsh.wallet.model.TransactionStatus;
import com.codeWithUtkarsh.wallet.model.TransactionType;
import com.codeWithUtkarsh.wallet.model.entity.Transaction;
import com.codeWithUtkarsh.wallet.services.PaymentGatewayService;
import com.codeWithUtkarsh.wallet.services.TransactionService;
import com.codeWithUtkarsh.wallet.util.PaymentActuator;

@Component
//@Transactional
public class PaymentGatewayServiceImpl implements PaymentGatewayService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentGatewayServiceImpl.class);
	
	@Autowired
	private PaymentActuator paymentActuator;

	@Autowired
	private TransactionService transactionService;
	
	@Override
	public String addMoney(String userName, Integer amount) throws PaymentGatewayServiceException
	{
		String transactionId = null;
		try
		{
			transactionId = UUID.randomUUID().toString();
			transactionService.createTransaction(
					new Transaction(transactionId, TransactionStatus.NEW, TransactionType.CREDIT, null, new Date(System.currentTimeMillis()), amount));
			LOGGER.debug("Transaction to add money started at:{}, TransactionId:{}", System.currentTimeMillis(), transactionId);
			
			paymentActuator.addMoney(transactionId, userName, amount);
		}catch (Exception e) {
			throw new PaymentGatewayServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return transactionId;
	}

	@Override
	public String transferMoney(String fromUserName, String toUserName, Integer amount) throws PaymentGatewayServiceException
	{
		String transactionId = null;
		try
		{
			transactionId = UUID.randomUUID().toString();
			transactionService.createTransaction(new Transaction(
					transactionId, TransactionStatus.NEW, TransactionType.DEBIT, new Date(System.currentTimeMillis())));
			
			LOGGER.debug("Transaction to transfer money started at:{}, TransactionId:{}", System.currentTimeMillis(), transactionId);
			paymentActuator.transferMoney(transactionId, amount, fromUserName, toUserName);
		}catch (Exception e) {
			throw new PaymentGatewayServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return transactionId;
	}

}
