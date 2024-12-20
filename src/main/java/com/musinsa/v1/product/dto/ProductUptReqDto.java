package com.musinsa.v1.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUptReqDto {

	@NotNull(message = "상품 아이디는 필수값입니다.")
	private Long productId;

	@NotBlank(message = "카테고리명은 필수값입니다")
	private String categoryNm;

	@NotBlank(message = "브랜드명은 필수값입니다.")
	private String brandNm;

	@Min(value = 1, message = "가격은 최소 1보다 커야 합니다")
	private BigDecimal price;
}
