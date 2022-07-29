package com.mbfinalassignment.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbfinalassignment.dao.ProductDao;
import com.mbfinalassignment.entity.Product;
import com.mbfinalassignment.exceptionHandling.CustomException;
import com.mbfinalassignment.exceptionHandling.ErrorCode;
import com.mbfinalassignment.model.ProductModel;
import com.mbfinalassignment.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService {

//	@Autowired
//	ProductRepository repository;
	
	@Autowired
	private ProductDao dao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<Product> getAllProducts() {

		List<Product> pro = dao.allProducts();
		
		if(pro.isEmpty())
		{
			throw new CustomException("No product Found",ErrorCode.NOT_FOUND);
		}
		return dao.allProducts();
	}

	@Override
	public Product saveProduct(ProductModel model) {
		if(dao.existsByProductId(model.getProductid())){
			throw new CustomException(model.getProductid()+" already exists", ErrorCode.RESOURCE_ALREADY_EXISTS);
		}
		
		Product product = mapper.map(model,Product.class);
		
		return dao.saveProduct(product);
	}

}
