package com.mbfinalassignment.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static spark.Spark.post;

import com.mbfinalassignment.entity.TransactionDetail;
import com.mbfinalassignment.exceptionHandling.CustomException;
import com.mbfinalassignment.exceptionHandling.ErrorCode;
import com.mbfinalassignment.model.CheckoutModel;
import com.mbfinalassignment.model.SuccessResponse;
import com.mbfinalassignment.model.TransactionDetailModel;
import com.mbfinalassignment.repository.TransactionDetailRepository;
import com.mbfinalassignment.service.TransactionDeailsService;
import com.stripe.Stripe;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.EventData;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.ApiResource;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;

import spark.Response;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PaymentController {

	@Value("${stripe-secret-key}")
	String stripeKey;

	private static Gson gson = new Gson();

	@Autowired
	TransactionDeailsService deailsService;

	@Autowired
	ModelMapper mapper;

	@GetMapping
	public String paymentInitiation() {
		Stripe.apiKey = stripeKey;
		return stripeKey;

	}

	@PostMapping
	public String paymentWithCheckoutPage(@RequestBody CheckoutModel checkoutModel) {
		Stripe.apiKey = stripeKey;

		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(checkoutModel.getSuccessUrl())
				.setCancelUrl(checkoutModel.getCancleUrl())
				.addLineItem(SessionCreateParams.LineItem
						.builder().setQuantity(
								checkoutModel.getQuantity())
						.setPriceData(SessionCreateParams.LineItem.PriceData.builder()
								.setCurrency(checkoutModel.getCurrency()).setUnitAmount(checkoutModel.getAmount())
								.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
										.setName(checkoutModel.getName()).build())
								.build())
						.build())
				.build();

		Session session;

		try {
			session = Session.create(params);
		} catch (CardException e) {
			throw new CustomException("card declined", ErrorCode.BAD_REQUEST);
		} catch (RateLimitException e) {
			throw new CustomException("multiple request error", ErrorCode.AUTHENTICATION_FAILED);
		} catch (InvalidRequestException e) {
			throw new CustomException("invalid inputs", ErrorCode.NOT_FOUND);
		} catch (AuthenticationException e) {
			throw new CustomException("Authentication", ErrorCode.AUTHENTICATION_FAILED);
		} catch (ApiConnectionException e) {
			throw new CustomException("Network error from stripe server", ErrorCode.INTERNAL_SERVER_ERROR);
		} catch (StripeException e) {
			throw new CustomException("User error", ErrorCode.UNAUTHENTICATED_USER);
		} catch (Exception e) {
			throw new CustomException("error related to app backend", ErrorCode.BAD_REQUEST);
		}

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("id", session.getId());
		return gson.toJson(response);

	}

	@PostMapping("/test")
	public String testPaymentIntent(CheckoutModel checkoutModel) throws StripeException {
		Stripe.apiKey = stripeKey;

		CustomerCreateParams params = CustomerCreateParams.builder().build();

		Customer customer = Customer.create(params);

		PaymentIntentCreateParams intentparams = PaymentIntentCreateParams.builder().setCustomer(customer.getId())
				.setSetupFutureUsage(PaymentIntentCreateParams.SetupFutureUsage.OFF_SESSION)
				.setAmount(checkoutModel.getAmount()).setCurrency("usd").setAutomaticPaymentMethods(
						PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build())
				.build();
		PaymentIntent intent = PaymentIntent.create(intentparams);

		Map<String, String> map = new HashMap<String, String>();
		map.put("Client Secret", intent.getClientSecret());
		map.put("id", customer.getId());

		return gson.toJson(map);
	}

	@PostMapping("/webhook")
	public Object handle(HttpServletRequest request, Response response) throws IOException {
		String payload = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		Event event = null;
		try {
			event = ApiResource.GSON.fromJson(payload, Event.class);
		} catch (JsonSyntaxException e) {
			response.status(400);
			return "";
		}
		if ("charge.succeeded".equals(event.getType())) {
			System.out.println("In Checkout Session completed");
			TransactionDetailModel details = new TransactionDetailModel();
			EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
			StripeObject stripeObject = null;
			if (dataObjectDeserializer.getObject().isPresent()) {
				stripeObject = dataObjectDeserializer.getObject().get();
			}
			Charge charge = (Charge) stripeObject;
			details.setEmail(charge.getBillingDetails().getEmail());
			details.setCustomer_name(charge.getBillingDetails().getName());
			details.setCustomerId(charge.getCustomer());
			details.setCreatedAt(charge.getCreated());
			details.setAmount(charge.getAmount() / 100);
			details.setPayment_method_type(event.getType());
			details.setPayment_intent(charge.getPaymentIntent());
			details.setPayment_status(charge.getPaid());
			details.setCurrency(charge.getCurrency());
			deailsService.saveTransaction(details);
		}
		return "";
	}
}
