package com.musinsa.v1.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.v1.product.model.dto.ProductRequest;
import com.musinsa.v1.product.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductService productService;

	@Autowired
	private ObjectMapper objectMapper;

	private ProductRequest productRequest;

	@BeforeEach
	void setUp() {
		productRequest = new ProductRequest();
		productRequest.setCategoryId(1l);
		productRequest.setBrandId(1l);
		productRequest.setPrice(10000);
	}

	@Test
	public void testGetLowestPrices() throws Exception {
		mockMvc.perform(get("/api/v1/products/lowest-prices"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testGetLowestTotal() throws Exception {
		mockMvc.perform(get("/api/v1/products/lowest-total"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testGetCategoryPrices() throws Exception {
		String categoryName = "상의";
		mockMvc.perform(get("/api/v1/products/category/prices")
			.contentType(MediaType.APPLICATION_JSON)
			.param("categoryName", categoryName))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testAddProduct() throws Exception {
		mockMvc.perform(post("/api/v1/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productRequest)))
			.andExpect(status().isOk())
			.andExpect(content().string("Product added successfully"));
	}

	@Test
	public void testUpdateProduct() throws Exception {
		Long productId = productService.saveProduct(productRequest).getId();

		mockMvc.perform(put("/api/v1/products/" + productId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productRequest)))
			.andExpect(status().isOk())
			.andExpect(content().string("Product updated successfully"));
	}

	@Test
	public void testDeleteProduct() throws Exception {
		Long productId = productService.saveProduct(productRequest).getId();

		mockMvc.perform(delete("/api/v1/products/" + productId))
			.andExpect(status().isOk())
			.andExpect(content().string("Product deleted successfully"));
	}
}
