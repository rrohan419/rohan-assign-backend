package com.mbfinalassignment.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="products")
public class Product {
	
	
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@Column
	private String name;
	
	@Column 
	private String company_name;
	
	@Column
	private String productid;
	
	@Column
	private String price;
	
	@Column
	private String description;
	
	
	
	public Product() {
		super();
	}



	public Product(String name, String company_name, String productid, String price, String description) {
		super();
		this.name = name;
		this.company_name = company_name;
		this.productid = productid;
		this.price = price;
		this.description = description;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCompany_name() {
		return company_name;
	}



	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}



	public String getProductid() {
		return productid;
	}



	public void setProductid(String productid) {
		this.productid = productid;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



			
}
