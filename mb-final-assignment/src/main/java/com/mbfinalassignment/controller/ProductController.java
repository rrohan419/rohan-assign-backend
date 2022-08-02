package com.mbfinalassignment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbfinalassignment.entity.Product;
import com.mbfinalassignment.model.ProductModel;
import com.mbfinalassignment.model.SuccessResponse;
import com.mbfinalassignment.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<SuccessResponse> getAllProducts() {
		SuccessResponse response = new SuccessResponse();
		response.setData(productService.getAllProducts());
		response.setMessage("Successfull");
		response.setSuccessCode(HttpStatus.OK.value());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/single/{id}")
	public ResponseEntity<SuccessResponse> getProductByid(@RequestParam(name = "id", required = true) String id) {
		SuccessResponse response = new SuccessResponse();
		Product product = productService.findByProductId(id);
		response.setData(product);
		response.setMessage("successfull");
		response.setSuccessCode(HttpStatus.OK.value());
		return new ResponseEntity<SuccessResponse>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SuccessResponse> saveProduct(@RequestBody @Valid ProductModel model) {
		SuccessResponse response = new SuccessResponse();
		response.setData(productService.saveProduct(model));
		response.setMessage("successfull");
		response.setSuccessCode(HttpStatus.OK.value());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
