package com.diego.planningpoker.api.exception;

import com.diego.planningpoker.infrastructure.annotation.BusinessException;
import org.springframework.http.HttpStatus;

@BusinessException(key = "recurso.atributo-repetido", status = HttpStatus.CONFLICT, returnMessageException = true)
public class AtributoRepetidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AtributoRepetidoException(String message) {
        super(message);
    }
}
