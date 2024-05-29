package com.musinsa.v1.product.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.musinsa.v1.product.model.dto.LowestPriceResponse;
import com.musinsa.v1.product.service.ProductService;

@Controller
@RequiredArgsConstructor
public class ProductViewController {

	private final ProductService productService;

	@GetMapping("/products")
	public String getProducts(Model model) {
		List<LowestPriceResponse> lowestPrices = productService.calculateLowestPrices();
		model.addAttribute("lowestPrices", lowestPrices);
		return "products";
	}
}
