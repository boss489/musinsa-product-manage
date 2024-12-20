package com.musinsa.v1.product.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.v1.product.dto.CategoryPriceResponseDto;
import com.musinsa.v1.product.dto.LowestPriceByCategoryResponseDto;
import com.musinsa.v1.product.dto.LowestPriceResponseDto;

@SpringBootTest
@Transactional
class ProductServiceImplTest {

	@Autowired
	private ProductServiceImpl productService;

	@Test
	void calculateLowestPricesForCategory() {
		//given
		//when
		LowestPriceByCategoryResponseDto response = productService.calculateLowestPricesForCategory();
		//then
		Assertions.assertEquals(0, response.getTotPrice().compareTo(BigDecimal.valueOf(34100d)));
		assertNotNull(response);
	}

	@Test
	void calculateLowestTotalForBrand() {
		// given
		// when
		LowestPriceResponseDto response = productService.calculateLowestTotalForBrand();
		// then
		assertEquals(0, response.getLowestPriceDto().getTotPrice().compareTo(BigDecimal.valueOf(36100d)));
		Assertions.assertEquals(response.getLowestPriceDto().getBrandNm() , "D");

	}

	@Test
	void getLowestAndHighestPriceByCategory() {
		//given
		String categoryName = "상의";
		//when
		CategoryPriceResponseDto response = productService.getLowestAndHighestPriceByCategory(categoryName);
		//then
		Assertions.assertEquals(response.getHighestPrice().getBrandNm(), "I");
		Assertions.assertEquals(response.getLowestPrice().getBrandNm(), "C");
	}
}