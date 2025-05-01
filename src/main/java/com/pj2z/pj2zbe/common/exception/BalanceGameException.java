package com.pj2z.pj2zbe.common.exception;

import org.springframework.http.HttpStatus;

public class BalanceGameException extends RuntimeException {
    private final HttpStatus status;

    public BalanceGameException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
