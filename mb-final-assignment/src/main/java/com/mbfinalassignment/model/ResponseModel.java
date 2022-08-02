package com.mbfinalassignment.model;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mbfinalassignment.exceptionHandling.ErrorResponse;

public class ResponseModel {
	private String error;
	private Object data;
	private Integer statusCode;
	private String message;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseEntity<Object> validationErrorsResponse(String message,
			List<ValidationCustomResponseModel> validationErros) {

		ErrorResponse error = new ErrorResponse(new Date(), message, "cant proceed", HttpStatus.NOT_ACCEPTABLE.value(),
				message);
		error.setValidationErrors(validationErros);

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	public static ResponseModel getInstance() {
		ResponseModel response = new ResponseModel();
		response.setStatusCode(HttpStatus.OK.value());
		return response;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
