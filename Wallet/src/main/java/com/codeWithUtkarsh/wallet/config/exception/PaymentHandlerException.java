package com.codeWithUtkarsh.wallet.config.exception;

import org.springframework.http.HttpStatus;

public class PaymentHandlerException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String message;
	private final HttpStatus httpStatus;

	public PaymentHandlerException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
