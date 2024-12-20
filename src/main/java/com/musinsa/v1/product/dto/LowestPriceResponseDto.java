package com.musinsa.v1.product.dto;


import static com.musinsa.v1.product.entity.QProduct.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.musinsa.v1.brand.entity.Brand;
import com.querydsl.core.Tuple;

import lombok.Data;

@Data
public class LowestPriceResponseDto {

	private LowestPriceDto lowestPriceDto;

	public LowestPriceResponseDto(Brand cheapestBrand, BigDecimal brandTotPrice, List<Tuple> categoryDetails) {
		this.lowestPriceDto = new LowestPriceDto(cheapestBrand.getName(), categoryDetails, brandTotPrice);
	}

	@Data
	public static class LowestPriceDto {

		private String brandNm;
		private List<CategoryPriceDto> categoryPriceDtoList;
		private BigDecimal totPrice;

		public LowestPriceDto(String brandNm, List<Tuple> categoryDetails, BigDecimal brandTotPrice) {
			this.brandNm = brandNm;
			this.totPrice = brandTotPrice;
			this.categoryPriceDtoList = getCategoryPriceDtoList(categoryDetails);
		}

		private List<CategoryPriceDto> getCategoryPriceDtoList(List<Tuple> categoryDetails) {
			List<CategoryPriceDto> categoryList = categoryDetails.stream().map(tuple -> {
				CategoryPriceDto categoryPriceDto = new CategoryPriceDto();
				categoryPriceDto.setCategoryNm(String.valueOf(tuple.get(product.category).getName()));
				categoryPriceDto.setPrice(tuple.get(product.price));
				return categoryPriceDto;
			}).collect(Collectors.toList());
			return categoryList;
		}

		@Data
		public static class CategoryPriceDto {
			private String categoryNm;
			private BigDecimal price;
		}
	}
}
