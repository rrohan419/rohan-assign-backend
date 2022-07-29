package com.mbfinalassignment.model;

public class SuccessResponse {
	private String message;
	private int successCode;
	private Object data;
	
	
	public SuccessResponse() {
		super();
	}
	
	public SuccessResponse(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}

	public SuccessResponse(String message, int successCode, Object data) {
		super();
		this.message = message;
		this.successCode = successCode;
		this.data = data;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public int getSuccessCode() {
		return successCode;
	}



	public void setSuccessCode(int successCode) {
		this.successCode = successCode;
	}



	public Object getData() {
		return data;
	}



	public void setData(Object data) {
		this.data = data;
	}
	
	
}
