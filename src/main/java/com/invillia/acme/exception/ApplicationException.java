package com.invillia.acme.exception;

public final class ApplicationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable rootCause) {
		super(rootCause);
	}



}
