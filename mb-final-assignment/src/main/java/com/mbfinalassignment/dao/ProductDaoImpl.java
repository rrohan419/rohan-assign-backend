package com.mbfinalassignment.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbfinalassignment.entity.Product;
import com.mbfinalassignment.exceptionHandling.CustomException;
import com.mbfinalassignment.exceptionHandling.ErrorCode;
import com.mbfinalassignment.repository.ProductRepository;

@Service
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	public List<Product> allProducts() {
		try {
			return productRepository.findAll();
		}
		catch (Exception e) {
			throw new CustomException("error while featching data",ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public boolean existsByProductId(String productId) {
		try {
			return productRepository.existsByProductid(productId);
		}
		catch (Exception e) {
			throw new CustomException("Product id ("+productId+") not found",ErrorCode.NOT_FOUND);
		}
	}

	@Override
	public Product saveProduct(Product product) {
		try {
			return productRepository.save(product);
		}
		catch (Exception e) {
			throw new CustomException("error while saving "+product.getName(), ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Product getProductByid(Long id) {
		try {
			return productRepository.findById(id).get();
		} catch (Exception e) {
			throw new CustomException("error while retreving product by id", ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Product findByProductId(String id) {
		try {
			return productRepository.findByProductid(id);
		}catch (Exception e) {
			throw new CustomException("error while retreving the product by productId", ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

}
