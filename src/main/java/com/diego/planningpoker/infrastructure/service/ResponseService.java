package com.diego.planningpoker.infrastructure.service;

import org.springframework.http.ResponseEntity;

public interface ResponseService {
	
	<T> ResponseEntity<T> create(T data);

	<T> ResponseEntity<T> ok(T data);

	<T> ResponseEntity<String> ok(String messageSource);

	<T> ResponseEntity<String> ok(String messageSource, String text);

	<T> ResponseEntity<T> notFound();

}
