package com.musinsa.v1.brand.repository;

import com.musinsa.v1.brand.model.entity.Brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  BrandRepository extends JpaRepository<Brand, Long> {
}
