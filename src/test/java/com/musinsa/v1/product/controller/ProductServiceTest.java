package com.musinsa.v1.product.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.musinsa.common.exception.NotFoundException;
import com.musinsa.v1.product.model.dto.ProductRequest;
import com.musinsa.v1.brand.model.entity.Brand;
import com.musinsa.v1.product.model.entity.Category;
import com.musinsa.v1.product.model.entity.Product;
import com.musinsa.v1.brand.repository.BrandRepository;
import com.musinsa.v1.product.repository.CategoryRepository;
import com.musinsa.v1.product.repository.ProductRepository;
import com.musinsa.v1.product.service.ProductServiceImpl;

class ProductServiceTest {

	@InjectMocks
	private ProductServiceImpl productService;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private BrandRepository brandRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveProduct() {
		ProductRequest request = new ProductRequest();
		request.setBrandId(1L);
		request.setCategoryId(1L);
		request.setPrice(10000);

		Brand brand = new Brand(1l, "Test Brand");

		Category category = new Category(1l, "상의");

		when(brandRepository.findById(anyLong())).thenReturn(Optional.of(brand));
		when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
		when(productRepository.save(any(Product.class))).thenReturn(new Product());

		productService.saveProduct(request);

		verify(brandRepository, times(1)).findById(1L);
		verify(categoryRepository, times(1)).findById(1L);
		verify(productRepository, times(1)).save(any(Product.class));
	}

	@Test
	void testSaveProduct_BrandNotFound() {
		ProductRequest request = new ProductRequest();
		request.setBrandId(1L);
		request.setCategoryId(1L);
		request.setPrice(10000);

		when(brandRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> {
			productService.saveProduct(request);
		});
	}

	@Test
	void testSaveProduct_CategoryNotFound() {
		ProductRequest request = new ProductRequest();
		request.setBrandId(1L);
		request.setCategoryId(1L);
		request.setPrice(10000);

		Brand brand = new Brand(1l, "Test Brand");

		when(brandRepository.findById(anyLong())).thenReturn(Optional.of(brand));
		when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> {
			productService.saveProduct(request);
		});
	}
}