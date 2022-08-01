package com.mbfinalassignment.dao;

import java.util.List;

import com.mbfinalassignment.entity.Product;

public interface ProductDao {
	List<Product> allProducts();
	boolean existsByProductId(String productId);
	Product saveProduct(Product product);
	Product getProductByid(Long id);
	Product findByProductId(String id);
	
}
