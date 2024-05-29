package com.musinsa.v1.product.service;

import com.musinsa.v1.brand.model.entity.Brand;
import com.musinsa.v1.product.model.dto.LowestPriceResponse;
import com.musinsa.v1.product.model.dto.LowestPriceWrapperResponse;
import com.musinsa.v1.product.model.dto.ProductResponse;
import com.musinsa.v1.product.model.entity.Product;
import com.musinsa.v1.product.model.dto.CategoryPriceResponse;
import com.musinsa.v1.product.model.dto.ProductRequest;

import java.util.List;

public interface ProductService {
	List<Product> findAllProducts();
	List<Product> findAllByBrand(Brand brand);
	void deleteInBatch(List<Product> products);
	Product saveProduct(ProductRequest productRequest);
	void updateProduct(Long id, ProductRequest productRequest);
	void deleteProduct(Long productId);
	List<LowestPriceResponse> calculateLowestPrices();
	LowestPriceWrapperResponse calculateLowestTotal();
	CategoryPriceResponse getCategoryPrices(String categoryName);
	List<ProductResponse> getAllProducts(); // 추가된 메서드
}