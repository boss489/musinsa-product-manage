package com.musinsa.v1.product.service.tr;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.musinsa.common.exception.NotFoundException;
import com.musinsa.v1.brand.entity.Brand;
import com.musinsa.v1.brand.repository.BrandRepository;
import com.musinsa.v1.product.entity.Category;
import com.musinsa.v1.product.entity.Product;
import com.musinsa.v1.product.dto.ProductCrtReqDto;
import com.musinsa.v1.product.repository.CategoryRepository;
import com.musinsa.v1.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCreateService extends ProductCudTemplate<Product, ProductCrtReqDto> {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final BrandRepository brandRepository;

	@Override
	public Product preValidate(ProductCrtReqDto crtReqDto) {
		Optional<Brand> brand = brandRepository.findByName(crtReqDto.getBrandNm());
		Optional<Category> category = categoryRepository.findByName(crtReqDto.getCategoryNm());

		if (brand.isEmpty()) {
			throw new NotFoundException(String.format("브랜드가 존재하지 않습니다 : 브랜드명 %s", crtReqDto.getBrandNm()));
		}

		if (category.isEmpty()) {
			throw new NotFoundException(String.format("카테고리가 존재하지 않습니다 : 카테고리명 " + crtReqDto.getCategoryNm()));
		}

		return new Product(crtReqDto, brand.get(), category.get());
	}

	@Override
	public Product process(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product execute(ProductCrtReqDto crtReqDto) {
		return super.execute(crtReqDto);
	}
}
