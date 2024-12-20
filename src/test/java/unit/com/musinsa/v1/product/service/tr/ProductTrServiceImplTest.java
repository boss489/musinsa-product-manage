package com.musinsa.v1.product.service.tr;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.musinsa.v1.product.dto.ProductCrtReqDto;
import com.musinsa.v1.product.dto.ProductDelReqDto;
import com.musinsa.v1.product.dto.ProductUptReqDto;
import com.musinsa.v1.product.entity.Product;
import com.musinsa.v1.product.repository.ProductRepository;

@SpringBootTest
@Transactional
class ProductTrServiceImplTest {

	@Autowired
	private ProductTrServiceImpl productTrService;

	@Autowired
	private ProductRepository productRepository;

	private ProductCrtReqDto productCrtReqDto;
	private ProductUptReqDto productUptReqDto;
	private ProductDelReqDto productDelReqDto;

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

		productDelReqDto = new ProductDelReqDto(2L);
	}

	@Test
	void saveProduct() {
		Product product = productTrService.saveProduct(productCrtReqDto);
		Optional<Product> byId = productRepository.findById(product.getId());
		assertNotNull(byId);

		assertEquals(product.getPrice(), byId.get().getPrice());
	}

	@Test
	void updateProduct() {
		productTrService.updateProduct(productUptReqDto);
		Optional<Product> byId = productRepository.findById(productUptReqDto.getProductId());
		assertEquals(0, byId.get().getPrice().compareTo(BigDecimal.valueOf(2000)));
	}

	@Test
	void deleteProduct() {
		productTrService.deleteProduct(productDelReqDto.getProductId());
		Optional<Product> byId = productRepository.findById(productDelReqDto.getProductId());
		Assertions.assertThat(byId).isEmpty();
	}
}