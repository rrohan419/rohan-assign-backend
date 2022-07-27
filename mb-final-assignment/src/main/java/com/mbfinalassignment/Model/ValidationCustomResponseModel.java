package com.mbfinalassignment.Model;

public class ValidationCustomResponseModel {
	private String fieldName;
	private String message;

	public ValidationCustomResponseModel(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getMessage() {
		return message;
	}
}