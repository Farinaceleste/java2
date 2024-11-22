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

	@Override
	public List<Product> getAllProducts() {
		return productRestApi.getAllProducts();
	}
	
	@Override
	public Product getProductById(String id) {
		return productRestApi.getProductById(id);
	}
	
	@Override
	public Product addProduct(Product product) {
		return productRestApi.addProduct(product);
	}
	
	@Override
	public Product updateProduct(Product product) {
		return productRestApi.updateProduct(product);
	}
	
	@Override
	public void deleteProduct(Long id) {
		return productRestApi.deleteProduct(id);
	}
	
	
	
}
