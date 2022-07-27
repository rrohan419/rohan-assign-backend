package com.mbfinalassignment.Model;

import javax.validation.constraints.NotBlank;

public class ProductModel {
	@NotBlank
	private String name;
	@NotBlank
	private String product_id;
	@NotBlank
	private String description;
	
	public String getName() {
		return name;
	}
	
	public String getProduct_id() {
		return product_id;
	}
	
	public String getDescription() {
		return description;
	}
	
	
}
