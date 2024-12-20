package com.musinsa.v1.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.musinsa.v1.product.dto.LowestPriceByCategoryResponseDto;
import com.musinsa.v1.product.service.ProductService;

@Controller
@RequiredArgsConstructor
public class ProductViewController {

	private final ProductService productService;

	@GetMapping("/products")
	public String getProducts(Model model) {
		LowestPriceByCategoryResponseDto lowestPriceByCategoryResponseDto = productService.calculateLowestPricesForCategory();
		model.addAttribute("lowestPriceByCategoryResponseDto", lowestPriceByCategoryResponseDto);
		return "products";
	}
}
