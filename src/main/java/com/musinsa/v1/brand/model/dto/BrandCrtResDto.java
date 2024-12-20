package com.musinsa.v1.brand.model.dto;

import com.musinsa.v1.brand.entity.Brand;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandCrtResDto {
	private Long brandId;
	private String brandName;

	public BrandCrtResDto(Brand brand) {
		this.brandId = brand.getId();
		this.brandName = brand.getName();
	}
}
