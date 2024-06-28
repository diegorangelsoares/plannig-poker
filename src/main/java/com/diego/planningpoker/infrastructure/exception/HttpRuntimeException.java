package com.diego.planningpoker.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class HttpRuntimeException extends RuntimeException{

    private final HttpStatus httpStatus;

    public HttpRuntimeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
