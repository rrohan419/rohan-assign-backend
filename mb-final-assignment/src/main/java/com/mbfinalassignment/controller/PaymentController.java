package com.mbfinalassignment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbfinalassignment.model.CheckoutModel;
import com.mbfinalassignment.model.SuccessResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Value("${stripe-secret-key}")
	String stripeKey;
	
	@GetMapping
	public String paymentInitiation()
	{
		Stripe.apiKey=stripeKey;
		return stripeKey;
		
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse> paymentWithCheckoutPage(@RequestBody  CheckoutModel checkoutModel) throws StripeException
	{
		Stripe.apiKey=stripeKey;
			SuccessResponse response = new SuccessResponse();
		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(checkoutModel.getSuccessUrl())
				.setCancelUrl(checkoutModel.getCancleUrl())
				.addLineItem(SessionCreateParams.LineItem.builder().setQuantity(checkoutModel.getQuantity())
						.setPriceData(SessionCreateParams.LineItem.PriceData.builder()
								.setCurrency(checkoutModel.getCurrency()).setUnitAmount(checkoutModel.getAmount())
								.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder().setName(checkoutModel.getName()).build())
								.build())
						.build())
				.build();
			response.setData(params);
			response.setMessage("successfull");
			response.setSuccessCode(HttpStatus.OK.value());
				System.out.println("bbbbbbb"+params+"aaaaa");
				return new ResponseEntity<SuccessResponse>(response,HttpStatus.OK);
						
						
	}
	
}
