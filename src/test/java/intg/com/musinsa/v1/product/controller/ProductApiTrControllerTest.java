package com.musinsa.v1.product.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.v1.product.dto.ProductCrtReqDto;
import com.musinsa.v1.product.dto.ProductUptReqDto;
import com.musinsa.v1.product.service.tr.ProductTrService;

@SpringBootTest
@AutoConfigureMockMvc
class ProductApiTrControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductTrService productTrService;

	@Autowired
	private ObjectMapper objectMapper;

	private ProductCrtReqDto productCrtReqDto;
	private ProductUptReqDto productUptReqDto;

	@BeforeEach
	void setUp() {
		productCrtReqDto = new ProductCrtReqDto();
		productCrtReqDto.setCategoryNm("상의");
		productCrtReqDto.setBrandNm("B");
		productCrtReqDto.setPrice(BigDecimal.valueOf(1000));

		productUptReqDto = new ProductUptReqDto();
		productUptReqDto.setProductId(1L);
		productUptReqDto.setCategoryNm("아우터");
		productUptReqDto.setBrandNm("C");
		productUptReqDto.setPrice(BigDecimal.valueOf(2000));
	}

	@Test
	public void testAddProduct() throws Exception {
		mockMvc.perform(post("/api/v1/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productCrtReqDto)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.message").value("상품이 추가 되었습니다"));
	}

	@Test
	public void testUpdateProduct() throws Exception {
		mockMvc.perform(put("/api/v1/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productUptReqDto)))
			.andExpect(status().isNoContent());
	}

	@Test
	public void testDeleteProduct() throws Exception {
		Long productId = 1L;
		mockMvc.perform(delete("/api/v1/products/" + productId))
			.andExpect(status().isNoContent());
	}
}