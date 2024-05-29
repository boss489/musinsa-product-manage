package com.musinsa.v1.brand.service;

import java.util.List;

import com.musinsa.v1.brand.model.dto.BrandRequest;
import com.musinsa.v1.brand.model.dto.BrandResponse;

public interface BrandService {
	void addBrand(BrandRequest brandRequest);
	void updateBrand(Long id, BrandRequest brandRequest);
	void deleteBrand(Long id);
	List<BrandResponse> getAllBrands();
	BrandResponse getBrandById(Long id);
}