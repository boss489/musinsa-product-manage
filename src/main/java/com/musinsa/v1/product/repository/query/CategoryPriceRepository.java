package com.musinsa.v1.product.repository.query;

import com.musinsa.v1.product.dto.CategoryPriceResponseDto;
import com.musinsa.v1.product.dto.LowestPriceByCategoryResponseDto;

public interface CategoryPriceRepository {
	LowestPriceByCategoryResponseDto searchLowestPricesCategory();
	CategoryPriceResponseDto getLowestAndHighestPriceByCategory(String categoryName);
}
