package com.diego.planningpoker.infrastructure.exception;


import com.diego.planningpoker.infrastructure.annotation.BusinessException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@BusinessException(key = "multitenancy.nao-informado", status = BAD_REQUEST)
public class TenantNotInformedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
}
