package com.gcu.lab1api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gcu.lab1api.dto.ProductDTO;
import com.gcu.lab1api.model.Product;
import com.gcu.lab1api.repository.ProductRepositoryInterface;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	// mocked so the service can be tested without the real repository
	@Mock
	private ProductRepositoryInterface productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	void getAllProductsReturnsDTOs() {
		when(productRepository.findAll()).thenReturn(List.of(
				new Product(1L, "Mouse", "desc", "Accessories", 20.0, 5),
				new Product(2L, "Keyboard", "desc", "Accessories", 50.0, 3)));

		List<ProductDTO> result = productService.getAllProducts();

		assertEquals(2, result.size());
		assertEquals("Mouse", result.get(0).name());
	}

	@Test
	void getProductByIdReturnsEmptyWhenMissing() {
		when(productRepository.findById(99L)).thenReturn(Optional.empty());

		assertTrue(productService.getProductById(99L).isEmpty());
	}

	@Test
	void createProductSavesAndReturnsNewId() {
		when(productRepository.save(any(Product.class))).thenAnswer(call -> {
			Product p = call.getArgument(0);
			p.setId(11L);
			return p;
		});

		ProductDTO created = productService.createProduct(new ProductDTO(null, "Monitor", "desc", "Displays", 200.0, 4));

		assertEquals(11L, created.id());
		verify(productRepository).save(any(Product.class));
	}

	@Test
	void deleteProductReturnsFalseWhenMissing() {
		when(productRepository.deleteById(99L)).thenReturn(false);

		assertFalse(productService.deleteProduct(99L));
	}
}
