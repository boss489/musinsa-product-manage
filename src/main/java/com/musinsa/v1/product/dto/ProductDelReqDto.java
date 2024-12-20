package com.musinsa.v1.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class ProductDelReqDto {

	@NotNull(message = "상품번호는 필수값입니다.")
	private Long productId;

	public ProductDelReqDto(Long id) {
		this.productId = id;
	}
}
