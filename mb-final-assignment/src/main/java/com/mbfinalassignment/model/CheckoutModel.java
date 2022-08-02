package com.mbfinalassignment.model;

import javax.validation.constraints.NotBlank;

public class CheckoutModel {

	@NotBlank
	private String name;

	@NotBlank
	private String currency;

	@NotBlank
	private String successUrl;

	@NotBlank
	private String cancleUrl;

	@NotBlank
	private Long amount;

	@NotBlank
	private Long quantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getCancleUrl() {
		return cancleUrl;
	}

	public void setCancleUrl(String cancleUrl) {
		this.cancleUrl = cancleUrl;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
