package com.musinsa.v1.product.service.tr;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.musinsa.common.exception.NotFoundException;
import com.musinsa.v1.product.entity.Product;
import com.musinsa.v1.product.dto.ProductDelReqDto;
import com.musinsa.v1.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductDeleteService extends ProductCudTemplate<Product, ProductDelReqDto> {

	private final ProductRepository productRepository;

	@Override
	public Product preValidate(ProductDelReqDto productDelReqDto) {
		Optional<Product> product = productRepository.findById(productDelReqDto.getProductId());

		if (product.isEmpty()) {
			throw new NotFoundException(String.format("상품이 존재하지 않습니다 : 상품번호 %s", productDelReqDto.getProductId()));
		}

		return product.get();
	}

	@Override
	public Product process(Product product) {
		productRepository.delete(product);
		return product;
	}

	@Override
	public void noReturnExecute(ProductDelReqDto delReqDto) {
		 super.noReturnExecute(delReqDto);
	}
}
