package com.musinsa.v1.product.repository.query;

import com.musinsa.v1.product.dto.LowestPriceResponseDto;

public interface ProductPriceRepository {
	LowestPriceResponseDto searchLowestPricesBrand();
}
