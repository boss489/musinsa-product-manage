package com.musinsa.v1.product.service;

import com.musinsa.v1.product.dto.LowestPriceByCategoryResponseDto;
import com.musinsa.v1.product.dto.LowestPriceResponseDto;
import com.musinsa.v1.product.dto.CategoryPriceResponseDto;

public interface ProductService {
	LowestPriceByCategoryResponseDto calculateLowestPricesForCategory();
	LowestPriceResponseDto calculateLowestTotalForBrand();
	CategoryPriceResponseDto getLowestAndHighestPriceByCategory(String categoryName);
}