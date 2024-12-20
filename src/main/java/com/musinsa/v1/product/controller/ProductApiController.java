package com.musinsa.v1.product.controller;

import com.musinsa.v1.product.dto.CategoryPriceResponseDto;
import com.musinsa.v1.product.dto.LowestPriceByCategoryResponseDto;
import com.musinsa.v1.product.dto.LowestPriceResponseDto;
import com.musinsa.v1.product.service.ProductService;
import com.musinsa.v1.product.model.ProductResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product API", description = "상품 관리 api")
public class ProductApiController {

	private final ProductService productService;

	@Operation(summary = "구현 1) 카테고리 별 최저가")
	@GetMapping("/lowest-prices")
	public ResponseEntity<LowestPriceByCategoryResponseDto> getLowestPrices() {
		LowestPriceByCategoryResponseDto response = productService.calculateLowestPricesForCategory();
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "구현 2) 최저가 판매 브랜드 정보")
	@GetMapping("/lowest-total")
	public ResponseEntity<LowestPriceResponseDto> getLowestTotal() {
		LowestPriceResponseDto response = productService.calculateLowestTotalForBrand();
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "구현 3) 카테고리 명 별 상품 가격 정보 조회")
	@GetMapping("/category/{categoryNm}")
	public ResponseEntity<CategoryPriceResponseDto> getCategoryPrices(@PathVariable(name = "categoryNm") String categoryNm) {
		CategoryPriceResponseDto response = productService.getLowestAndHighestPriceByCategory(categoryNm);
		return ResponseEntity.ok(response);
	}
}
