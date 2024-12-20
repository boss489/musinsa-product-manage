package com.musinsa.v1.product.service;

import com.musinsa.v1.product.dto.CategoryPriceResponseDto;
import com.musinsa.v1.product.dto.LowestPriceByCategoryResponseDto;
import com.musinsa.v1.product.dto.LowestPriceResponseDto;
import com.musinsa.v1.product.repository.CategoryRepository;
import com.musinsa.v1.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	@Override
	public LowestPriceByCategoryResponseDto calculateLowestPricesForCategory() {
		return categoryRepository.searchLowestPricesCategory();
	}

	@Override
	public LowestPriceResponseDto calculateLowestTotalForBrand() {
		return productRepository.searchLowestPricesBrand();
	}

	@Override
	public CategoryPriceResponseDto getLowestAndHighestPriceByCategory(String categoryName) {
		return categoryRepository.getLowestAndHighestPriceByCategory(categoryName);
	}
}
