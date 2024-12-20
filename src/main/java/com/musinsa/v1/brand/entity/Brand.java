package com.musinsa.v1.brand.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.musinsa.v1.product.entity.Product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	//
	// @OneToMany(mappedBy = "category")
	// private List<Product> products = new ArrayList<>();

	@OneToMany(mappedBy = "brand")
	private List<Product> products = new ArrayList<>();

	public Brand(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Brand brand = (Brand)o;
		return Objects.equals(id, brand.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
