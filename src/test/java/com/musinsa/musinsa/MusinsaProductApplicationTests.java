package com.musinsa.musinsa;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.v1.product.model.entity.Product;
import com.musinsa.v1.product.repository.ProductRepository;

import jakarta.persistence.EntityManager;

@SpringBootTest
class MusinsaProductApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Test
	@Transactional
	@Rollback(false)
	void contextLoads() {
		productRepository.findByCategoryName("상의");
		productRepository.findByCategoryId(3l);
		productRepository.findAll();

	}


}
