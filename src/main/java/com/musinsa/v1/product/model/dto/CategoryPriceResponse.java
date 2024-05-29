package com.musinsa.v1.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryPriceResponse {
	private String category;
	private PriceDetail lowest;
	private PriceDetail highest;

	@Data
	@AllArgsConstructor
	public static class PriceDetail {
		private String brand;
		private double price;
	}
}
