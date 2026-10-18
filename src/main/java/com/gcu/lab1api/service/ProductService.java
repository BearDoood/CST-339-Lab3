package com.gcu.lab1api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gcu.lab1api.dto.ProductDTO;
import com.gcu.lab1api.model.Product;
import com.gcu.lab1api.repository.ProductRepositoryInterface;

@Service
public class ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	private final ProductRepositoryInterface productRepository;

	// spring hands us whatever bean implements the interface
	public ProductService(ProductRepositoryInterface productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductDTO> getAllProducts() {
		log.info("Fetching all products");
		return productRepository.findAll().stream()
				.map(this::toDTO)
				.toList();
	}

	public Optional<ProductDTO> getProductById(Long id) {
		log.info("Fetching product {}", id);
		return productRepository.findById(id).map(this::toDTO);
	}

	public ProductDTO createProduct(ProductDTO dto) {
		log.info("Creating product {}", dto.name());
		Product saved = productRepository.save(toEntity(dto));
		return toDTO(saved);
	}

	public Optional<ProductDTO> updateProduct(Long id, ProductDTO dto) {
		log.info("Updating product {}", id);
		return productRepository.update(id, toEntity(dto)).map(this::toDTO);
	}

	public boolean deleteProduct(Long id) {
		log.info("Deleting product {}", id);
		return productRepository.deleteById(id);
	}

	private ProductDTO toDTO(Product p) {
		return new ProductDTO(p.getId(), p.getName(), p.getDescription(), p.getCategory(), p.getPrice(), p.getQuantity());
	}

	private Product toEntity(ProductDTO dto) {
		return new Product(null, dto.name(), dto.description(), dto.category(), dto.price(), dto.quantity());
	}
}
