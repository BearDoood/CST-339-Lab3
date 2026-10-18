package com.gcu.lab1api.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gcu.lab1api.model.Product;

@Repository
public class ProductRepository implements ProductRepositoryInterface {

	private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

	// in memory list standing in for a real database for now
	private final List<Product> products = new ArrayList<>();
	private long nextId = 1;

	public ProductRepository() {
		save(new Product(null, "Wireless Mouse", "Ergonomic 2.4GHz mouse", "Accessories", 24.99, 50));
		save(new Product(null, "Mechanical Keyboard", "RGB keyboard with brown switches", "Accessories", 89.99, 30));
		save(new Product(null, "27in Monitor", "1440p 165Hz IPS display", "Displays", 279.99, 15));
		save(new Product(null, "USB-C Hub", "7 in 1 hub with HDMI and card reader", "Accessories", 39.99, 40));
		save(new Product(null, "Gaming Headset", "Surround sound headset with mic", "Audio", 69.99, 25));
		save(new Product(null, "Webcam", "1080p webcam with autofocus", "Video", 49.99, 35));
		save(new Product(null, "External SSD", "1TB portable USB-C SSD", "Storage", 109.99, 20));
		save(new Product(null, "Laptop Stand", "Adjustable aluminum stand", "Accessories", 29.99, 45));
		save(new Product(null, "Desk Microphone", "USB condenser microphone", "Audio", 79.99, 18));
		save(new Product(null, "Ethernet Cable", "10ft Cat6 cable", "Networking", 9.99, 100));
		log.info("Product repository loaded with {} products", products.size());
	}

	@Override
	public List<Product> findAll() {
		return new ArrayList<>(products);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return products.stream()
				.filter(p -> p.getId().equals(id))
				.findFirst();
	}

	@Override
	public Product save(Product product) {
		product.setId(nextId++);
		products.add(product);
		return product;
	}

	@Override
	public Optional<Product> update(Long id, Product updated) {
		return findById(id).map(existing -> {
			existing.setName(updated.getName());
			existing.setDescription(updated.getDescription());
			existing.setCategory(updated.getCategory());
			existing.setPrice(updated.getPrice());
			existing.setQuantity(updated.getQuantity());
			return existing;
		});
	}

	@Override
	public boolean deleteById(Long id) {
		return products.removeIf(p -> p.getId().equals(id));
	}
}
