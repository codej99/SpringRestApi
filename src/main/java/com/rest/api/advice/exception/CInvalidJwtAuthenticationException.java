package com.rest.api.advice.exception;

public class CInvalidJwtAuthenticationException extends RuntimeException {
    public CInvalidJwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CInvalidJwtAuthenticationException(String msg) {
        super(msg);
    }

    public CInvalidJwtAuthenticationException() {
        super();
    }
}
