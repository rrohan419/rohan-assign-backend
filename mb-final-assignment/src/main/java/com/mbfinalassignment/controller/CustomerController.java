package com.mbfinalassignment.controller;

import java.util.HashMap;
import java.util.Map;

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

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Value("${stripe-secret-key}")
	String stripeKey;

	@GetMapping("/{cusid}")
	public Customer getCustomer(@RequestParam("cusid") String customerId) throws StripeException {
		Stripe.apiKey = stripeKey;

		Customer customer = Customer.retrieve(customerId);
		return customer;

	}

	@RequestMapping
	public CustomerModel addCustomer(@RequestBody @Valid CustomerModel model) throws StripeException {
		Stripe.apiKey = stripeKey;

		Map<String, Object> params = new HashMap<>();
		params.put("name", model.getName());
		params.put("email", model.getEmail());

		Customer customer = Customer.create(params);
		model.setCustomerId(customer.getId());
		return model;
	}
}
