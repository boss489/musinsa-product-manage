package com.musinsa.v1.product.repository.query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.musinsa.v1.brand.entity.Brand;
import com.musinsa.v1.brand.entity.QBrand;
import com.musinsa.v1.product.dto.CategoryPriceResponseDto;
import com.musinsa.v1.product.dto.LowestPriceByCategoryResponseDto;
import com.musinsa.v1.product.entity.Category;
import com.musinsa.v1.product.entity.QCategory;
import com.musinsa.v1.product.entity.QProduct;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryPriceRepositoryImpl implements CategoryPriceRepository {

	private final JPAQueryFactory queryFactory;

	public LowestPriceByCategoryResponseDto searchLowestPricesCategory(){
		return getCategoryLowest();
	}

	private LowestPriceByCategoryResponseDto getCategoryLowest() {
		QProduct product = QProduct.product;
		QProduct subProduct = new QProduct("subProduct");
		QCategory category = QCategory.category;
		QBrand brand = QBrand.brand;

		List<Tuple> results = getMinCatePriceList(category, brand, product, subProduct);
		Map<String, Tuple> uniqueResultsMap = getUniqueResult(results, category, product);
		List<Tuple> uniqueList = new ArrayList<>(uniqueResultsMap.values());

		BigDecimal totalPrice = BigDecimal.ZERO;
		List<LowestPriceByCategoryResponseDto.CategoryLowestPriceDto> categoryList = new ArrayList<>();

		for (Tuple tuple : uniqueList) {
			Category cat = tuple.get(category);
			Brand brd = tuple.get(brand);
			BigDecimal price = tuple.get(product.price);

			categoryList.add(new LowestPriceByCategoryResponseDto.CategoryLowestPriceDto(cat, brd, price));
			totalPrice = totalPrice.add(price);
		}

		return new LowestPriceByCategoryResponseDto(categoryList, totalPrice);
	}

	private List<Tuple> getMinCatePriceList(QCategory category, QBrand brand, QProduct product, QProduct subProduct) {
		return queryFactory
			.select(
				category,
				brand,
				product.price
			)
			.from(product)
			.join(product.category, category)
			.join(product.brand, brand)
			.where(
				product.price.eq(
					JPAExpressions.select(subProduct.price.min())
						.from(subProduct)
						.where(subProduct.category.eq(category))
				)
			)
			.fetch();
	}

	private Map<String, Tuple> getUniqueResult(List<Tuple> results, QCategory category, QProduct product) {
		Map<String, Tuple> uniqueResults = results.stream()
			.collect(Collectors.toMap(
				tuple -> tuple.get(category) + ":" + tuple.get(product.price),
				tuple -> tuple,
				(existing, replacement) -> existing
			));
		return uniqueResults;
	}

	public CategoryPriceResponseDto getLowestAndHighestPriceByCategory(String categoryName) {
		CategoryPriceResponseDto.CategoryLowestHighestPriceDto lowestPriceList = getLowestPriceList(categoryName);
		CategoryPriceResponseDto.CategoryLowestHighestPriceDto highestPriceList = getHighestPriceList(categoryName);

		return new CategoryPriceResponseDto(categoryName, lowestPriceList, highestPriceList);
	}

	private CategoryPriceResponseDto.CategoryLowestHighestPriceDto getHighestPriceList(String categoryName) {
		QProduct product = QProduct.product;

		return queryFactory
			.select(product.brand, product.price)
			.from(product)
			.where(product.category.name.eq(categoryName))
			.orderBy(product.price.desc())
			.limit(1)
			.fetch()
			.stream()
			.map(tuple -> new CategoryPriceResponseDto.CategoryLowestHighestPriceDto(
				tuple.get(product.brand).getName(),
				tuple.get(product.price)
			)).findAny().orElseGet(() -> new CategoryPriceResponseDto.CategoryLowestHighestPriceDto(null, null));

	}

	private CategoryPriceResponseDto.CategoryLowestHighestPriceDto getLowestPriceList(String categoryName) {
		QProduct product = QProduct.product;

		return queryFactory
			.select(product.brand, product.price)
			.from(product)
			.where(product.category.name.eq(categoryName))
			.orderBy(product.price.asc())
			.limit(1)
			.fetch()
			.stream()
			.map(tuple -> new CategoryPriceResponseDto.CategoryLowestHighestPriceDto(
				tuple.get(product.brand).getName(),
				tuple.get(product.price)
			)).findAny().orElseGet(() -> new CategoryPriceResponseDto.CategoryLowestHighestPriceDto(null, null));
	}


}
