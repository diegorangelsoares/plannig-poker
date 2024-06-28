package com.diego.planningpoker.infrastructure.exception;



import com.diego.planningpoker.infrastructure.annotation.BusinessException;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@BusinessException(key = "recurso.integracao.indisponivel", status = SERVICE_UNAVAILABLE)
public class ServicoIndisponivelException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public ServicoIndisponivelException(String message) {
        super(message);
    }

}

