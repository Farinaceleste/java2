package com.coderhouse.interfaces;

import java.util.List;

import com.coderhouse.models.Product;

public interface ProductRestInterface {
	
	public List<Product> getAllProducts();
	
	public Product getProductById(Integer id);
	
	public Product addProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public void deleteProduct(Integer id);

	Product createProduct(Product product);
}
