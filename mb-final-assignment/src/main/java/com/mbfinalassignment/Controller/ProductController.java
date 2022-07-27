package com.mbfinalassignment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbfinalassignment.Model.SuccessResponse;
import com.mbfinalassignment.Service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<SuccessResponse> getAllProducts()
	{
		SuccessResponse response =new SuccessResponse();
		response.setData(productService.getAllProducts());
		response.setMessage("Successfull");
		response.setSuccessCode(HttpStatus.OK.value());
		
		return new ResponseEntity<SuccessResponse>(response,HttpStatus.OK);
	}
}
