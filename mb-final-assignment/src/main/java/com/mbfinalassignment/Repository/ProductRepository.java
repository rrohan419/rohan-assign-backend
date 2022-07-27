package com.mbfinalassignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbfinalassignment.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
