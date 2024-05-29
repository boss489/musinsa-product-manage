package com.musinsa.v1.product.service;

import com.musinsa.common.exception.NotFoundException;
import com.musinsa.v1.product.model.dto.CategoryPriceResponse;
import com.musinsa.v1.product.model.dto.LowestPriceResponse;
import com.musinsa.v1.product.model.dto.LowestPriceWrapperResponse;
import com.musinsa.v1.product.model.dto.LowestTotalResponse;
import com.musinsa.v1.product.model.dto.ProductResponse;
import com.musinsa.v1.brand.model.entity.Brand;
import com.musinsa.v1.product.model.entity.Category;
import com.musinsa.v1.product.model.entity.Product;
import com.musinsa.v1.product.model.dto.ProductRequest;
import com.musinsa.v1.brand.repository.BrandRepository;
import com.musinsa.v1.product.repository.CategoryRepository;
import com.musinsa.v1.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final BrandRepository brandRepository;
	private final CategoryRepository categoryRepository;

	@Override
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> findAllByBrand(Brand brand) {
		return productRepository.findAllByBrand(brand);
	}

	@Override
	public void deleteInBatch(List<Product> products) {
		productRepository.deleteInBatch(products);
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		return this.findAllProducts().stream()
			.map(ProductResponse::convertToProductResponse)
			.collect(Collectors.toList());
	}
	@Override
	public Product saveProduct(ProductRequest productRequest) {
		Brand brand = brandRepository.findById(productRequest.getBrandId())
			.orElseThrow(() -> new NotFoundException("Brand not found"));
		Category category = categoryRepository.findById(productRequest.getCategoryId())
			.orElseThrow(() -> new NotFoundException("Category not found"));
		Product product = new Product(productRequest.getPrice(), category, brand);
		return productRepository.save(product);
	}

	@Override
	public void updateProduct(Long id, ProductRequest productRequest) {

		Product product = productRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Product not found"));
		Brand brand = brandRepository.findById(productRequest.getBrandId())
			.orElseThrow(() -> new NotFoundException("Brand not found"));
		Category category = categoryRepository.findById(productRequest.getCategoryId())
			.orElseThrow(() -> new NotFoundException("Category not found"));

		product.setCategory(category);
		product.setBrand(brand);
		product.setPrice(productRequest.getPrice());

		productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long productId) {
		if (!productRepository.existsById(productId)) {
			throw new NotFoundException("Product not found");
		}
		productRepository.deleteById(productId);
	}

	@Override
	public List<LowestPriceResponse> calculateLowestPrices() {
		List<Product> products = findAllProducts();
		Map<Long, Product> lowestPrices = new HashMap<>();
		for (Product product : products) {
			lowestPrices.merge(product.getCategory().getId(), product, (oldValue, newValue) ->
				oldValue.getPrice() > newValue.getPrice() ? newValue : oldValue);
		}
		return lowestPrices.values().stream()
			.map(product -> new LowestPriceResponse(product.getCategory().getName(), product.getBrand().getName(), product.getPrice()))
			.collect(Collectors.toList());
	}

	@Override
	public LowestPriceWrapperResponse calculateLowestTotal() {
		List<Product> products = findAllProducts();
		Map<Brand, Double> brandTotals = new HashMap<>();
		Map<Brand, List<Product>> brandProducts = new HashMap<>();

		for (Product product : products) {
			brandTotals.merge(product.getBrand(), product.getPrice(), Double::sum);
			brandProducts.computeIfAbsent(product.getBrand(), k -> new ArrayList<>()).add(product);
		}

		Map.Entry<Brand, Double> lowestTotalBrand = brandTotals.entrySet().stream()
			.min(Map.Entry.comparingByValue())
			.orElseThrow();

		List<LowestTotalResponse.CategoryPrice> categories = brandProducts.get(lowestTotalBrand.getKey()).stream()
			.map(product -> new LowestTotalResponse.CategoryPrice(product.getCategory().getName(), product.getPrice()))
			.collect(Collectors.toList());

		LowestTotalResponse lowestTotalResponse = new LowestTotalResponse(
			lowestTotalBrand.getKey().getName(),
			lowestTotalBrand.getValue(),
			categories
		);

		return new LowestPriceWrapperResponse(lowestTotalResponse);
	}

	@Override
	public CategoryPriceResponse getCategoryPrices(String categoryName) {
		List<Product> products = productRepository.findByCategoryName(categoryName);

		if (products.isEmpty()) {
			throw new NotFoundException("No products found in category categoryName : " + categoryName);
		}

		Product lowestPriceProduct = products.stream()
			.min(Comparator.comparingDouble(Product::getPrice))
			.orElseThrow();
		Product highestPriceProduct = products.stream()
			.max(Comparator.comparingDouble(Product::getPrice))
			.orElseThrow();

		return new CategoryPriceResponse(
			products.get(0).getCategory().getName(),
			new CategoryPriceResponse.PriceDetail(lowestPriceProduct.getBrand().getName(), lowestPriceProduct.getPrice()),
			new CategoryPriceResponse.PriceDetail(highestPriceProduct.getBrand().getName(), highestPriceProduct.getPrice())
		);
	}
}
