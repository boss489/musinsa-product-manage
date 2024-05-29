package com.musinsa.v1.product.model.dto;

import com.musinsa.v1.product.model.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	private Long id;
	private Long categoryId;
	private Long brandId;
	private double price;

	public static ProductResponse convertToProductResponse(Product product) {
		ProductResponse response = new ProductResponse();
		response.setId(product.getId());
		response.setCategoryId(product.getCategory().getId());
		response.setBrandId(product.getBrand().getId());
		response.setPrice(product.getPrice());
		return response;
	}
}
