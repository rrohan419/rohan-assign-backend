package com.mbfinalassignment.exceptionHandling;

import java.util.Date;
import java.util.List;

import com.mbfinalassignment.model.ValidationCustomResponseModel;

public class ErrorResponse
{
	private Date timestamp;
	private String message;
	private String details;
	private int statusCode;
	private String error;
	private List<ValidationCustomResponseModel> validationErrors;

	public ErrorResponse(Date timestamp, String message, String details, int statusCode, String error)
	{
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.statusCode = statusCode;
		this.error = error;
	}


	
	public Date getTimestamp()
	{
		return timestamp;
	}

	public String getMessage()
	{
		return message;
	}

	public String getDetails()
	{
		return details;
	}

	public int getStatusCode()
	{
		return statusCode;
	}

	public String getError()
	{
		return error;
	}

	public List<ValidationCustomResponseModel> getValidationErrors()
	{
		return validationErrors;
	}

	public void setValidationErrors(List<ValidationCustomResponseModel> validationErrors)
	{
		this.validationErrors = validationErrors;
	}
}
