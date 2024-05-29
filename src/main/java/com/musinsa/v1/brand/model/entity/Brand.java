package com.musinsa.v1.brand.model.entity;

import java.util.List;
import java.util.Objects;

import com.musinsa.v1.product.model.entity.Product;

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
