package com.musinsa.v1.brand.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.v1.brand.entity.Brand;
import com.musinsa.v1.brand.model.dto.BrandCrtReqDto;
import com.musinsa.v1.brand.model.dto.BrandCrtResDto;
import com.musinsa.v1.brand.model.dto.BrandRequest;
import com.musinsa.v1.brand.model.dto.BrandResponse;
import com.musinsa.v1.brand.model.dto.BrandUptReqDto;
import com.musinsa.v1.brand.service.BrandService;
import com.musinsa.v1.common.CommonResponseModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
@Tag(name = "Brands API", description = "브랜드 관리 api")
public class BrandApiController {

	private final BrandService brandService;

	@Operation(summary = "브랜드 추가")
	@PostMapping
	public CommonResponseModel addBrand(@Valid @RequestBody BrandCrtReqDto brandRequest) {
		BrandCrtResDto brandCrtResDto = brandService.addBrand(brandRequest);
		return CommonResponseModel.builder()
			.returnCode(HttpStatus.CREATED.value())
			.message("브랜드가 추가 되었습니다")
			.data(brandCrtResDto)
			.build();
	}

	@Operation(summary = "브랜드 수정")
	@PutMapping
	public CommonResponseModel updateBrand(@Valid  @RequestBody BrandUptReqDto brandRequest) {
		brandService.updateBrand(brandRequest);

		return CommonResponseModel.builder()
			.returnCode(HttpStatus.NO_CONTENT.value())
			.message("브랜드가 업데이트 되었습니다")
			.build();
	}

	@Operation(summary = "브랜드 삭제")
	@DeleteMapping("/{id}")
	public CommonResponseModel deleteBrand(@PathVariable Long id) {
		brandService.deleteBrand(id);
		return CommonResponseModel.builder()
			.returnCode(HttpStatus.NO_CONTENT.value())
			.message("브랜드가 삭제 되었습니다")
			.build();
	}
}
