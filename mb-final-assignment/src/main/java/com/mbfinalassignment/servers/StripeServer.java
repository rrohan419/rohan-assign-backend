package com.mbfinalassignment.servers;

import org.springframework.beans.factory.annotation.Value;

import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.param.CustomerListParams;

public class StripeServer {

			@Value("${stripe-secret-key}")
	public static String stripeKey;
	
		public static void stripeCustomer()
		{
			try {
			Stripe.apiKey=StripeServer.stripeKey;
			CustomerListParams params = CustomerListParams.builder().build();
			CustomerCollection customers = Customer.list(params);
			System.out.println(customers);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
}
