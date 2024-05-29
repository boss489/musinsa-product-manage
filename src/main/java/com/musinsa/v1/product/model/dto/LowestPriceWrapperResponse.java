package com.musinsa.v1.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LowestPriceWrapperResponse {
	private LowestTotalResponse lowestPrice;
}
