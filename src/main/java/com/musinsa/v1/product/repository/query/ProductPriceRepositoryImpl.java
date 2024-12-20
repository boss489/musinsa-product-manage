package com.musinsa.v1.product.repository.query;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import com.musinsa.v1.brand.entity.Brand;
import com.musinsa.v1.product.dto.LowestPriceResponseDto;
import com.musinsa.v1.product.entity.QProduct;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductPriceRepositoryImpl implements ProductPriceRepository {

	private final JPAQueryFactory queryFactory;

	public LowestPriceResponseDto searchLowestPricesBrand(){

		QProduct product = QProduct.product;
		Tuple cheapBrandSrchResult = getLowestBrand(product);

		Brand cheapestBrand = cheapBrandSrchResult.get(product.brand);
		BigDecimal brandTotPrice = cheapBrandSrchResult.get(product.price.sum());

		List<Tuple> categoryDetails = getBrandCategoryProductList(product, cheapestBrand);

		return reponseDto(cheapestBrand, brandTotPrice, categoryDetails);
	}

	private LowestPriceResponseDto reponseDto(Brand cheapestBrand, BigDecimal brandTotPrice, List<Tuple> categoryDetails) {
		return new LowestPriceResponseDto(cheapestBrand, brandTotPrice, categoryDetails);
	}

	private List<Tuple> getBrandCategoryProductList(QProduct product, Brand cheapestBrand) {
		return queryFactory.select(product.category, product.price)
			.from(product)
			.where(product.brand.eq(cheapestBrand))
			.fetch();
	}

	private Tuple getLowestBrand(QProduct product) {

		List<Tuple> results = queryFactory.select(product.brand, product.price.sum())
			.from(product)
			.groupBy(product.brand)
			.fetch();

		Tuple cheapestBrand = results.stream()
			.min(Comparator.comparing(tuple -> tuple.get(product.price.sum())))
			.orElseThrow(() -> new RuntimeException("No data found"));

		return cheapestBrand;
	}
}
