package com.gcu.lab1api.repository;

import java.util.List;
import java.util.Optional;

import com.gcu.lab1api.model.Product;

public interface ProductRepositoryInterface {

	List<Product> findAll();

	Optional<Product> findById(Long id);

	Product save(Product product);

	Optional<Product> update(Long id, Product product);

	boolean deleteById(Long id);
}
