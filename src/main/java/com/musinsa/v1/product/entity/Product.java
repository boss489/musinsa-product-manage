package com.musinsa.v1.product.entity;

import java.math.BigDecimal;

import com.musinsa.v1.brand.entity.Brand;
import com.musinsa.v1.product.dto.ProductCrtReqDto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;

	@Column(nullable = false)
	private BigDecimal price;

	public Product(BigDecimal price, Category category, Brand brand) {
		this.price = price;
		this.category = category;
		this.brand = brand;
	}

	public Product(ProductCrtReqDto crtReqDto, Brand brand, Category category) {
		this.price = crtReqDto.getPrice();
		this.brand = brand;
		this.category = category;
	}

	public void updateProductDetails(Category category, Brand brand, BigDecimal price) {
		this.category = category;
		this.brand = brand;
		this.price = price;
		this.changeCategory(category);
		this.changeBrand(brand);
	}


	public void changeCategory(Category category) {
		this.category = category;
		this.category.getProducts().add(this);
	}

	public void changeBrand(Brand brand) {
		this.brand = brand;
		this.brand.getProducts().add(this);
	}

}