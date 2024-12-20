package com.musinsa.v1.product.dto;

import java.math.BigDecimal;
import java.util.List;

import com.musinsa.v1.brand.entity.Brand;
import com.musinsa.v1.product.entity.Category;

import lombok.Data;

@Data
public class LowestPriceByCategoryResponseDto {

	private BigDecimal totPrice;
	private List<CategoryLowestPriceDto> categoryLowestPriceDtoList; // 최저가 카테고리 목록

	public LowestPriceByCategoryResponseDto(List<CategoryLowestPriceDto> categoryList, BigDecimal totalPrice) {
		categoryLowestPriceDtoList = categoryList;
		totPrice = totalPrice;
	}

	@Data
	public static class CategoryLowestPriceDto {
		private String categoryNm;
		private String brandNm;
		private BigDecimal price;

		public CategoryLowestPriceDto(Category category, Brand brand, BigDecimal price) {
			this.categoryNm = category.getName();
			this.brandNm = brand.getName();
			this.price = price;
		}
	}
}
