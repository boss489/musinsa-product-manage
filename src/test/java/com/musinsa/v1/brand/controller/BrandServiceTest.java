package com.musinsa.v1.brand.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.musinsa.common.exception.NotFoundException;
import com.musinsa.v1.brand.model.dto.BrandRequest;
import com.musinsa.v1.brand.model.entity.Brand;
import com.musinsa.v1.brand.repository.BrandRepository;
import com.musinsa.v1.brand.service.BrandServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

	@Mock
	private BrandRepository brandRepository;

	@InjectMocks
	private BrandServiceImpl brandService;

	@Test
	public void testAddBrand() {
		BrandRequest request = new BrandRequest();
		request.setName("Test Brand");

		Brand brand = new Brand();
		brand.setId(1L);
		brand.setName("Test Brand");

		when(brandRepository.save(any(Brand.class))).thenReturn(brand);

		brandService.addBrand(request);

		verify(brandRepository, times(1)).save(any(Brand.class));
	}

	@Test
	public void testUpdateBrand() {
		BrandRequest request = new BrandRequest();
		request.setName("Updated Brand");

		Brand brand = new Brand();
		brand.setId(1L);
		brand.setName("Old Brand");

		when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));

		brandService.updateBrand(1L, request);

		verify(brandRepository, times(1)).save(brand);
		assertEquals("Updated Brand", brand.getName());
	}

	@Test
	public void testUpdateBrand_NotFound() {
		BrandRequest request = new BrandRequest();
		request.setName("Updated Brand");

		when(brandRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> brandService.updateBrand(1L, request));
	}

	@Test
	public void testDeleteBrand() {
		Brand brand = new Brand();
		brand.setId(1L);
		brand.setName("Test Brand");

		when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));

		brandService.deleteBrand(1L);

		verify(brandRepository, times(1)).delete(brand);
	}

	@Test
	public void testDeleteBrand_NotFound() {
		when(brandRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> brandService.deleteBrand(1L));
	}
}
