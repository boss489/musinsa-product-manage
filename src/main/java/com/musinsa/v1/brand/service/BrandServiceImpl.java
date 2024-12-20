package com.musinsa.v1.brand.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.musinsa.common.exception.NotFoundException;
import com.musinsa.v1.brand.model.dto.BrandCrtReqDto;
import com.musinsa.v1.brand.model.dto.BrandCrtResDto;
import com.musinsa.v1.brand.model.dto.BrandResponse;
import com.musinsa.v1.brand.entity.Brand;
import com.musinsa.v1.brand.model.dto.BrandUptReqDto;
import com.musinsa.v1.brand.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

	private final BrandRepository brandRepository;

	@Override
	public BrandCrtResDto addBrand(BrandCrtReqDto brandCrtReqDto) {
		Brand brand = new Brand();
		brand.setName(brandCrtReqDto.getName());
		return new BrandCrtResDto(brandRepository.save(brand));
	}

	@Override
	public void updateBrand(BrandUptReqDto brandRequest) {
		Brand brand = brandRepository.findById(brandRequest.getId())
			.orElseThrow(() -> new NotFoundException("브랜드가 존재하지 않습니다"));

		brand.setName(brandRequest.getName());
	}

	@Override
	public void deleteBrand(Long id) {
		Brand brand = brandRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("브랜드가 존재하지 않습니다"));
		brandRepository.delete(brand);
	}

	@Override
	public List<BrandResponse> getAllBrands() {
		return brandRepository.findAll().stream()
			.map(brand -> new BrandResponse(brand.getId(), brand.getName()))
			.collect(Collectors.toList());
	}

	@Override
	public BrandResponse getBrandById(Long id) {
		Brand brand = brandRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("브랜드가 존재하지 않습니다"));

		return new BrandResponse(brand.getId(), brand.getName());
	}
}
