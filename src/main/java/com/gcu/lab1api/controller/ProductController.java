package com.gcu.lab1api.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.lab1api.dto.ProductDTO;
import com.gcu.lab1api.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "CRUD operations for products")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	@Operation(summary = "Get all products")
	public List<ProductDTO> getAll() {
		log.info("GET /api/products");
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get a product by id")
	public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
		log.info("GET /api/products/{}", id);
		return productService.getProductById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@Operation(summary = "Create a new product")
	public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO product) {
		log.info("POST /api/products");
		ProductDTO created = productService.createProduct(product);
		return ResponseEntity.created(URI.create("/api/products/" + created.id())).body(created);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update an existing product")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO product) {
		log.info("PUT /api/products/{}", id);
		return productService.updateProduct(id, product)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a product")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		log.info("DELETE /api/products/{}", id);
		return productService.deleteProduct(id)
				? ResponseEntity.noContent().build()
				: ResponseEntity.notFound().build();
	}
}
