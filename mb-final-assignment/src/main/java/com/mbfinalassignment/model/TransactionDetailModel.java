package com.mbfinalassignment.model;

import javax.validation.constraints.NotBlank;

public class TransactionDetailModel {
	@NotBlank
	private String customerId;

	private String customer_name;
	@NotBlank
	private String email;

	private String payment_intent;

	private String payment_method_type;
	
	@NotBlank
	private String currency;
	
	@NotBlank
	private Long amount;

	@NotBlank
	private Boolean payment_status;

	private Long createdAt;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPayment_intent() {
		return payment_intent;
	}

	public void setPayment_intent(String payment_intent) {
		this.payment_intent = payment_intent;
	}

	public String getPayment_method_type() {
		return payment_method_type;
	}

	public void setPayment_method_type(String payment_method_type) {
		this.payment_method_type = payment_method_type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Boolean getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(Boolean payment_status) {
		this.payment_status = payment_status;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

}
