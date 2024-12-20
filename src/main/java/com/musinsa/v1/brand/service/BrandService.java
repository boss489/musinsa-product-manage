package com.musinsa.v1.brand.service;

import java.util.List;

import com.musinsa.v1.brand.model.dto.BrandCrtReqDto;
import com.musinsa.v1.brand.model.dto.BrandCrtResDto;
import com.musinsa.v1.brand.model.dto.BrandResponse;
import com.musinsa.v1.brand.model.dto.BrandUptReqDto;

public interface BrandService {
	BrandCrtResDto addBrand(BrandCrtReqDto brandCrtReqDto);
	void updateBrand(BrandUptReqDto brandUptReqDto);
	void deleteBrand(Long id);
	List<BrandResponse> getAllBrands();
	BrandResponse getBrandById(Long id);
}