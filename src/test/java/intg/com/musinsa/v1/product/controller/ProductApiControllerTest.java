package com.musinsa.v1.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.v1.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

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
		mockMvc.perform(get("/api/v1/products/category/" + categoryName)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
}