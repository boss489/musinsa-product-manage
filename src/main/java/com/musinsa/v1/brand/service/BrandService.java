package com.musinsa.v1.brand.service;

import java.util.List;

import com.musinsa.v1.brand.model.dto.BrandRequest;
import com.musinsa.v1.brand.model.dto.BrandResponse;
import com.musinsa.v1.brand.model.entity.Brand;

public interface BrandService {
	Brand addBrand(BrandRequest brandRequest);
	void updateBrand(Long id, BrandRequest brandRequest);
	void deleteBrand(Long id);
	List<BrandResponse> getAllBrands();
	BrandResponse getBrandById(Long id);
}