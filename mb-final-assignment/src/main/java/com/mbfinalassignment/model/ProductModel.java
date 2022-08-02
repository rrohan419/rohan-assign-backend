package com.mbfinalassignment.model;

import javax.validation.constraints.NotBlank;

public class ProductModel {

	@NotBlank
	private String name;

	@NotBlank
	private String productid;

	@NotBlank
	private String company_name;

	@NotBlank
	private String price;

	public String getPrice() {
		return price;
	}

	@NotBlank
	private String description;

	public String getName() {
		return name;
	}

	public String getProductid() {
		return productid;
	}

	public String getCompany_name() {
		return company_name;
	}

	public String getDescription() {
		return description;
	}

}
