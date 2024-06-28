package com.diego.planningpoker.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class ContextoEmissorException extends HttpRuntimeException {

    private static final long serialVersionUID = 1L;

    public ContextoEmissorException() {
        super("Erro ao buscar contexto emissor.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ContextoEmissorException(String message, HttpStatus status) {
        super(message, status);
    }

}
