package org.sltb.transportmanagement.exception;

public class InsufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException(String message, Throwable cause) {
		super(message, cause);
	}

	public InsufficientBalanceException(String message) {
		super(message);
	}

}
