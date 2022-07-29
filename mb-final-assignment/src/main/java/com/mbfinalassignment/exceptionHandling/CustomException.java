package com.mbfinalassignment.exceptionHandling;

public class CustomException extends RuntimeException {

	/**
	 This POJO class is for creating Custom Exception that the developer
	 wants to  create
	 */
	private static final long serialVersionUID = 1L;
	
	private final ErrorCode errorCode;
	
	public CustomException(String message, ErrorCode errorCode)
	{
		super(message);
		this.errorCode = errorCode;
	}
	public CustomException(String message, Throwable t,ErrorCode errorCode)
	{
		super(message,t);
		this.errorCode=errorCode;
	}
	
	
	 
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	 
	 
}
