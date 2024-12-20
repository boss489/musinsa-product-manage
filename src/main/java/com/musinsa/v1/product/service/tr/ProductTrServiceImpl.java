package com.musinsa.v1.product.service.tr;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.v1.product.dto.ProductCrtReqDto;
import com.musinsa.v1.product.dto.ProductDelReqDto;
import com.musinsa.v1.product.dto.ProductUptReqDto;
import com.musinsa.v1.product.entity.Product;
import com.musinsa.v1.product.model.ProductUpdateModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductTrServiceImpl implements ProductTrService {

	private final ProductCreateService productCreateService;
	private final ProductUpdateService productUpdateService;
	private final ProductDeleteService productDeleteService;

	@Override
	@Transactional
	public Product saveProduct(ProductCrtReqDto productRequest) {
		return productCreateService.execute(productRequest);
	}

	@Override
	@Transactional
	public void updateProduct(ProductUptReqDto productRequest) {
		productUpdateService.execute(productRequest);
	}

	@Override
	@Transactional
	public void deleteProduct(Long id) {
		ProductDelReqDto productRequest = new ProductDelReqDto(id);
		productDeleteService.execute(productRequest);
	}
}
