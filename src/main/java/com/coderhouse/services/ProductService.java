package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Product	;
import com.coderhouse.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product findProductById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
	}

	@Transactional
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Transactional
	public Product updateProduct(Long id, Product productDetails) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Product not found"));

		product.setTitle(productDetails.getTitle());
		product.setDescription(productDetails.getDescription());
		product.setStock(productDetails.getStock());
		product.setPrice(productDetails.getPrice());

		return productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		if (!productRepository.existsById(id)) {
			throw new IllegalArgumentException("Product not found");
		}

		productRepository.deleteById(id);
	}
	
	
}
