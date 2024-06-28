package com.diego.planningpoker.infrastructure.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import com.diego.planningpoker.infrastructure.annotation.BusinessException;

@BusinessException(key = "multitenancy.nao-conectar", status = BAD_REQUEST)
public class TenantNotConnectionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

}
