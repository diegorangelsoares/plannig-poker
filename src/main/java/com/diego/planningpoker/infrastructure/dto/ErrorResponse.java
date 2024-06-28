package com.diego.planningpoker.infrastructure.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	private List<FieldError> fieldErrors;

	private Object detail = null;

	public ErrorResponse() {
		this.timestamp = LocalDateTime.now();
	}

	public ErrorResponse(HttpStatus httpStatus, String message, String path) {
		this.timestamp = LocalDateTime.now();
		this.status = httpStatus.value();
		this.error = httpStatus.getReasonPhrase();
		this.message = message;
		this.path = path;
	}

	public ErrorResponse(HttpStatus httpStatus, List<String> messages, String path) {
		this.timestamp = LocalDateTime.now();
		this.status = httpStatus.value();
		this.error = httpStatus.getReasonPhrase();
		this.message = messages != null ? String.join(", ", messages) : null;
		this.path = path;
	}

	public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path, List<FieldError> fieldErrors, Object detail) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.fieldErrors = fieldErrors;
		this.detail = detail;
	}

	public static ErrorResponse of(){
		return new ErrorResponse();
	}

	public ErrorResponse fieldErrors(List<FieldError> fieldErrors){
		this.fieldErrors = fieldErrors;
		return this;
	}

	public ErrorResponse timestamp(LocalDateTime timestamp){
		this.timestamp = timestamp;
		return this;
	}

	public ErrorResponse status(int status){
		this.status = status;
		return this;
	}

	public ErrorResponse error(String error){
		this.error = error;
		return this;
	}

	public ErrorResponse message(String message){
		this.message = message;
		return this;
	}

	public ErrorResponse detail(Object detail){
		this.detail = detail;
		return this;
	}
	public ErrorResponse path(String path){
		this.path = path;
		return this;
	}
	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}