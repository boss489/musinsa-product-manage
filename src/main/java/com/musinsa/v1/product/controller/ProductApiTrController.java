package com.musinsa.v1.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.v1.common.CommonResponseModel;
import com.musinsa.v1.product.dto.ProductCrtReqDto;
import com.musinsa.v1.product.dto.ProductUptReqDto;
import com.musinsa.v1.product.service.tr.ProductTrService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product API", description = "상품 등록 수정 api")
public class ProductApiTrController {

	private final ProductTrService productTrService;


	@Operation(summary = "상품 추가")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponseModel addProduct(@Valid @RequestBody ProductCrtReqDto productRequest) {
		productTrService.saveProduct(productRequest);
		return CommonResponseModel.builder()
			.returnCode(HttpStatus.CREATED.value())
			.message("상품이 추가 되었습니다")
			.build();
	}

	@Operation(summary = "상품 수정")
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public CommonResponseModel updateProduct(@Valid @RequestBody ProductUptReqDto productRequest) {
		productTrService.updateProduct(productRequest);
		return CommonResponseModel.builder()
			.returnCode(HttpStatus.CREATED.value())
			.message("상품이 수정 되었습니다")
			.build();
	}

	@Operation(summary = "상품 삭제")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public CommonResponseModel deleteProduct(@PathVariable Long id) {
		productTrService.deleteProduct(id);
		return  CommonResponseModel.builder()
			.returnCode(HttpStatus.OK.value())
			.message("상품이 수정 되었습니다")
			.build();
	}
}
