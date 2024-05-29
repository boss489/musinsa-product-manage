package com.musinsa.v1.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class LowestTotalResponse {

	private String brand;
	private double total;
	private List<CategoryPrice> categories;

	@Data
	@AllArgsConstructor
	public static class CategoryPrice {
		private String category;
		private double price;
	}

}
