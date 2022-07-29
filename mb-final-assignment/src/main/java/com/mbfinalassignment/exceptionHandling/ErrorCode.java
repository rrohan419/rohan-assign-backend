package com.mbfinalassignment.exceptionHandling;

public enum ErrorCode {

	INTERNAL_SERVER_ERROR(500),
	BAD_REQUEST(400),
	NOT_FOUND(404),
	UNAUTHORIZED(401),
	UNAUTHENTICATED_USER(403),
	URL_NOT_ACCESSABLE(405),
	EXPIRED_TOKEN(499),
	INVALID_TOKEN(498),
	RESOURCE_ALREADY_EXISTS(101),
	USERNAME_NOT_FOUND(103),
	AUTHENTICATION_FAILED(409);

	private final int code;
	
	ErrorCode(int code) {
		this.code=code;
	}
	
	public int getCode()
	{
		return this.code;
	}
}
