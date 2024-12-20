package com.musinsa.v1.brand.repository;

import java.util.Optional;

import com.musinsa.v1.brand.entity.Brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  BrandRepository extends JpaRepository<Brand, Long> {
	Optional<Brand> findByName(String brandNm);
}
