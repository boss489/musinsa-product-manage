package com.musinsa.v1.product.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

	@NotNull(message = "categoryId is mandatory")
	private Long categoryId;

	@NotNull(message = "brandId is mandatory")
	private Long brandId;

	@Min(value = 0, message = "Price must be greater than or equal to 0")
	private double price;
}
