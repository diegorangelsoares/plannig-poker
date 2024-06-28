package com.diego.planningpoker.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
class ResponseServiceImpl implements ResponseService {

	@Autowired
	private MessageService messageService;

	public <T> ResponseEntity<T> create(T data) {
		return ResponseEntity.status(HttpStatus.CREATED).body(data);
	}

	public <T> ResponseEntity<T> ok(T data) {
		return ResponseEntity.ok(data);
	}

	public <T> ResponseEntity<String> ok(String messageSource) {
		return ResponseEntity.ok(messageService.getMessage(messageSource));
	}

	public <T> ResponseEntity<String> ok(String messageSource, String text) {
		return ResponseEntity.ok(messageService.getMessage(messageSource) + text);
	}

	public <T> ResponseEntity<T> notFound() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
