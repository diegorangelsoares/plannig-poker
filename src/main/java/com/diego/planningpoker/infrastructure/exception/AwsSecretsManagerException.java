package com.diego.planningpoker.infrastructure.exception;



import com.diego.planningpoker.infrastructure.annotation.BusinessException;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@BusinessException(key = "aws.secrets-manager", status = SERVICE_UNAVAILABLE)
public class AwsSecretsManagerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}

