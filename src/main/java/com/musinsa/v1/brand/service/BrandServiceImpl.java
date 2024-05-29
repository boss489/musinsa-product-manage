package com.musinsa.v1.brand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.musinsa.common.exception.NotFoundException;
import com.musinsa.v1.brand.model.dto.BrandRequest;
import com.musinsa.v1.brand.model.dto.BrandResponse;
import com.musinsa.v1.brand.model.entity.Brand;
import com.musinsa.v1.brand.repository.BrandRepository;
import com.musinsa.v1.product.model.entity.Product;
import com.musinsa.v1.product.service.ProductService;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

	private final BrandRepository brandRepository;
	private final ProductService productService;
	private final EntityManager entityManager;

	@Override
	public void addBrand(BrandRequest brandRequest) {
		Brand brand = new Brand();
		brand.setName(brandRequest.getName());
		brandRepository.save(brand);
	}

	@Override
	public void updateBrand(Long id, BrandRequest brandRequest) {
		Brand brand = brandRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Brand not found"));

		brand.setName(brandRequest.getName());
		brandRepository.save(brand);
	}

	@Override
	public void deleteBrand(Long id) {

		Brand brand = brandRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Brand not found"));

		List<Product> products = productService.findAllByBrand(brand);
		productService.deleteInBatch(products);
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
			.orElseThrow(() -> new NotFoundException("Brand not found"));

		return new BrandResponse(brand.getId(), brand.getName());
	}
}
