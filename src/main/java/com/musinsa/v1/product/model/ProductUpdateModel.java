package com.musinsa.v1.product.model;

import java.math.BigDecimal;

import com.musinsa.v1.brand.entity.Brand;
import com.musinsa.v1.product.dto.ProductUptReqDto;
import com.musinsa.v1.product.entity.Category;
import com.musinsa.v1.product.entity.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductUpdateModel {

	private Product product;
	private Category category;
	private Brand brand;
	private BigDecimal price;

	public ProductUpdateModel(Product product, Category category, Brand brand, BigDecimal price) {
		this.product = product;
		this.category = category;
		this.brand = brand;
		this.price = price;
	}
}
