package com.mbfinalassignment.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CustomerModel {
	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;

	private String customerId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
