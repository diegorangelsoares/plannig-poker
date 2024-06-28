package com.diego.planningpoker.api.exception;

import com.diego.planningpoker.infrastructure.annotation.BusinessException;
import org.springframework.http.HttpStatus;

@BusinessException(key = "recurso.informacao-nao-encontrada", status = HttpStatus.NOT_FOUND, returnMessageException = true)
public class TesteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TesteException(String message) {
        super(message);
    }
}
