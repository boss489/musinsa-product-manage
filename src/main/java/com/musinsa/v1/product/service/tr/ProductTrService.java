package com.musinsa.v1.product.service.tr;

import com.musinsa.v1.product.dto.ProductCrtReqDto;
import com.musinsa.v1.product.dto.ProductUptReqDto;
import com.musinsa.v1.product.entity.Product;

public interface ProductTrService {
	Product saveProduct(ProductCrtReqDto productRequest);
	void updateProduct(ProductUptReqDto productRequest);
	void deleteProduct(Long id);
}