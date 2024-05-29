package com.musinsa.v1.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musinsa.v1.product.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
