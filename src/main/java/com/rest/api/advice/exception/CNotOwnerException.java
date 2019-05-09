package com.rest.api.advice.exception;

public class CNotOwnerException extends RuntimeException {

	private static final long serialVersionUID = 2241549550934267615L;

	public CNotOwnerException(String msg, Throwable t) {
		super(msg, t);
	}

	public CNotOwnerException(String msg) {
		super(msg);
	}

	public CNotOwnerException() {
		super();
	}
}
