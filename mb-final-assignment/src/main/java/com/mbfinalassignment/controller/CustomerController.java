package com.mbfinalassignment.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbfinalassignment.model.CustomerModel;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.param.CustomerListParams;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Value("${stripe-secret-key}")
	String stripeKey;
	
	@GetMapping
	public void getCustomers() throws StripeException
	{
		Stripe.apiKey=stripeKey;

		CustomerListParams params = CustomerListParams.builder().build();
		CustomerCollection customers = Customer.list(params);

	}
	
	@GetMapping("/{cusid}")
	public void getCustomer(@RequestParam("cusid") String customerId) throws StripeException
	{
		Stripe.apiKey = stripeKey;

		Customer customer = Customer.retrieve(customerId);
		System.out.println(customer + "abcbsc");
		
		
		
	}
	
	@RequestMapping
	public CustomerModel addCustomer(@RequestBody @Valid CustomerModel model) throws StripeException
	{
		Stripe.apiKey=stripeKey;
		
		Map<String, Object> params = new HashMap<>();
		params.put("name" , model.getName());
		params.put("email" , model.getEmail());
//		params.put("customerId" , model.getCustomerId());

		Customer customer = Customer.create(params);
		model.setCustomerId(customer.getId());
		return model;
	}
}
