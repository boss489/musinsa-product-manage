package com.musinsa.v1.brand.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandUptReqDto {

	@NotNull(message = "브랜드 아이디는 필수값입니다.")
	private Long id;

	@NotNull(message = "브랜드명은 필수값입니다.")
	private String name;
}
