package com.musinsa.v1.product.dto;

import java.math.BigDecimal;
import java.util.List;

import com.musinsa.v1.brand.entity.Brand;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryPriceResponseDto {

	private String categoryNm;
	private CategoryLowestHighestPriceDto lowestPrice;
	private CategoryLowestHighestPriceDto highestPrice;

	@Data
	public static class CategoryLowestHighestPriceDto {
		private String brandNm;
		private BigDecimal price;

		public CategoryLowestHighestPriceDto(String brandNm, BigDecimal price) {
			this.brandNm = brandNm;
			this.price = price;
		}
	}
}
