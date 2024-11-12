package com.coderhouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Product;
import com.coderhouse.services.ProductService;

@RestController
@RequestMapping("/api/products")

public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {

		try {
			List<Product>products = productService.getAllProducts();
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/{id")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		try {
			Product product =productService.findProductById(id);
			return ResponseEntity.ok(product);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		try {
			Product createdProduct = productService.saveProduct(product);
			return ResponseEntity.ok(createdProduct);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}