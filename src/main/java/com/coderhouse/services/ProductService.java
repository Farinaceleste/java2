package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.apis.ProductRestApi;
import com.coderhouse.interfaces.ProductRestInterface;
import com.coderhouse.models.Product	;

import jakarta.transaction.Transactional;

@Service
public class ProductService implements ProductRestInterface{
	
	@Autowired
	private ProductRestApi productRestApi;

	public List<Product> getAllProducts() {
		return productRestApi.getAllProducts();
	}
	
	public Product getProductById(Long id) {
		return productRestApi.getProductById(id);
	}
	
	@Transactional
	public Product createProduct(Product product) {
		return productRestApi.createProduct(product);
	}
	
	@Transactional
	public Product updateProduct(Product product) {
		return productRestApi.updateProduct(product);
	}

	public void deleteProduct(Long id) {
		productRestApi.deleteProduct(id);
	}

	@Override
	public Product addProduct(Product product) {
		return null;
	}

}
