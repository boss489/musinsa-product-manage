package com.musinsa.v1.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LowestPriceResponse {
	private String category;
	private String brand;
	private double price;
}
