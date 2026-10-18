package com.gcu.lab1api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductDTO(
		Long id,
		@NotBlank String name,
		String description,
		String category,
		@PositiveOrZero double price,
		@PositiveOrZero int quantity) {
}
