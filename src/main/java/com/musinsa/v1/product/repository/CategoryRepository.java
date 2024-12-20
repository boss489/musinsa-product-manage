package com.musinsa.v1.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musinsa.v1.product.entity.Category;
import com.musinsa.v1.product.repository.query.CategoryPriceRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryPriceRepository {
	Optional<Category> findByName(String categoryNm);
}
