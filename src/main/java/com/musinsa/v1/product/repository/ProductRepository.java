package com.musinsa.v1.product.repository;

import com.musinsa.v1.brand.entity.Brand;
import com.musinsa.v1.product.entity.Product;
import com.musinsa.v1.product.repository.query.ProductPriceRepository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductPriceRepository {

	@EntityGraph(attributePaths = {"category", "brand"})
	List<Product> findAll();

	List<Product> findByCategoryName(String name);

	List<Product> findAllByBrand(Brand brand);
}
