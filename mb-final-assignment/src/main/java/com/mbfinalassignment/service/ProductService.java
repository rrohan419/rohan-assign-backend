package com.mbfinalassignment.service;

import java.util.List;

import com.mbfinalassignment.entity.Product;
import com.mbfinalassignment.model.ProductModel;

public interface ProductService {
	List<Product> getAllProducts();
	Product saveProduct(ProductModel model);
	Product getProductById(Long id);
	Product findByProductId(String id);
	
}
