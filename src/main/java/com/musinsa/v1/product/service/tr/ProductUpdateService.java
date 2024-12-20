package com.musinsa.v1.product.service.tr;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.musinsa.common.exception.NotFoundException;
import com.musinsa.v1.brand.entity.Brand;
import com.musinsa.v1.brand.repository.BrandRepository;
import com.musinsa.v1.product.entity.Category;
import com.musinsa.v1.product.entity.Product;
import com.musinsa.v1.product.dto.ProductUptReqDto;
import com.musinsa.v1.product.model.ProductUpdateModel;
import com.musinsa.v1.product.repository.CategoryRepository;
import com.musinsa.v1.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductUpdateService extends ProductCudTemplate<ProductUpdateModel, ProductUptReqDto> {

	private final ProductRepository productRepository;
	private final BrandRepository brandRepository;
	private final CategoryRepository categoryRepository;


	@Override
	public ProductUpdateModel preValidate(ProductUptReqDto uptReqDto) {
		Optional<Product> product = productRepository.findById(uptReqDto.getProductId());
		if (product.isEmpty()) {
			throw new NotFoundException(String.format("상품이 존재하지 않습니다 : 상품번호 %s", uptReqDto.getProductId()));
		}

		Optional<Brand> brand = brandRepository.findByName(uptReqDto.getBrandNm());

		if (brand.isEmpty()) {
			throw new NotFoundException(String.format("브랜드가 존재하지 않습니다 : 브랜드명 %s", uptReqDto.getBrandNm()));
		}

		Optional<Category> category = categoryRepository.findByName(uptReqDto.getCategoryNm());

		if (category.isEmpty()) {
			throw new NotFoundException(String.format("카테고리가 존재하지 않습니다 : 카테고리명 " + uptReqDto.getCategoryNm()));
		}

		return new ProductUpdateModel(product.get(), category.get(), brand.get(), uptReqDto.getPrice());
	}

	@Override
	public ProductUpdateModel process(ProductUpdateModel productUpdateModel) {
		Product product = productUpdateModel.getProduct();
		product.updateProductDetails(productUpdateModel.getCategory(), productUpdateModel.getBrand(),
				productUpdateModel.getPrice());
		return productUpdateModel;
	}

	@Override
	public void noReturnExecute(ProductUptReqDto uptReqDto) {
		 super.noReturnExecute(uptReqDto);
	}

}
