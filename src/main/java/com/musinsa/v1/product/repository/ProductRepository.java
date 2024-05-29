package com.musinsa.v1.product.repository;

import com.musinsa.v1.brand.model.entity.Brand;
import com.musinsa.v1.product.model.entity.Product;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@EntityGraph(attributePaths = {"category", "brand"})
	List<Product> findAll();
	List<Product> findByCategoryName(String name);
	List<Product> findByCategoryId(Long id);
	List<Product> findAllByBrand(Brand brand);

}
