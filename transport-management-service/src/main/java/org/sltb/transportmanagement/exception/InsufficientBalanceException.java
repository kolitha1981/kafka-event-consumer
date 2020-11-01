package org.sltb.transportmanagement.exception;

public class InsufficientBalanceException extends RuntimeException {

	public InsufficientBalanceException(String message, Throwable cause) {
		super(message, cause);
	}

	public InsufficientBalanceException(String message) {
		super(message);
	}

}
