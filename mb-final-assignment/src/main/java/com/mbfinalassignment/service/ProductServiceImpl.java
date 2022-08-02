package com.mbfinalassignment.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbfinalassignment.dao.ProductDao;
import com.mbfinalassignment.entity.Product;
import com.mbfinalassignment.exceptionHandling.CustomException;
import com.mbfinalassignment.exceptionHandling.ErrorCode;
import com.mbfinalassignment.model.ProductModel;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao dao;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Product> getAllProducts() {

		List<Product> pro = dao.allProducts();

		if (pro.isEmpty()) {
			throw new CustomException("No product Found", ErrorCode.NOT_FOUND);
		}
		return dao.allProducts();
	}

	@Override
	public Product saveProduct(ProductModel model) {

		if (dao.existsByProductId(model.getProductid())) {

			throw new CustomException(model.getProductid() + " already exists", ErrorCode.RESOURCE_ALREADY_EXISTS);
		}
		Product product = mapper.map(model, Product.class);

		return dao.saveProduct(product);
	}

	@Override
	public Product getProductById(Long id) {

		Product product = dao.getProductByid(id);

		if (product == null) {
			throw new CustomException(id + " not present ", ErrorCode.NOT_FOUND);
		}
		return product;
	}

	@Override
	public Product findByProductId(String id) {
		Product product = dao.findByProductId(id);
		if (product == null) {
			throw new CustomException(id + " not present ", ErrorCode.NOT_FOUND);
		}
		return product;

	}

}
