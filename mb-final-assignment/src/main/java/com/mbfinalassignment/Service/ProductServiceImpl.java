package com.mbfinalassignment.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbfinalassignment.Entity.Product;
import com.mbfinalassignment.ExceptionHandling.CustomException;
import com.mbfinalassignment.ExceptionHandling.ErrorCode;
import com.mbfinalassignment.Repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repository;
	
	@Override
	public List<Product> getAllProducts() {
		
		List<Product> products = new ArrayList<Product>();
		products = repository.findAll();
		if(products.isEmpty())
		{
			throw new CustomException("database empty", ErrorCode.NOT_FOUND);
		}
		return products;
	}

}
