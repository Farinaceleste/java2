package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.coderhouse.models.Product;
import com.coderhouse.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/products")
@Tag(name="ProductController", description="Controlador para manejar productos")


public class ProductController {

	@Autowired
	private ProductService productService;

	@Operation(summary="Obtener la lista completa de productos")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se obtuvo lista de productos de forma correcta", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
			})
	})
	
	@GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Product>> getAllProducts() {

		try {
			List<Product> products = productService.getAllProducts();
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Operation(summary="Obtener un producto determinado mediante ID")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se obtuvo producto con ID específico", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
			})
	})
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		try {
			Product product = productService.findProductById(id);
			return ResponseEntity.ok(product);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary="Crear nuevo producto")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se creó el producto", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
			})
	})
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		try {
			Product createdProduct = productService.saveProduct(product);
			return ResponseEntity.ok(createdProduct);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary="Actualizar producto")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se actualizó producto de forma correcta", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
			})
	})
	@PutMapping
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
		try {
			Product updatedProduct = productService.updateProduct(id, productDetails);
			return ResponseEntity.ok(updatedProduct);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary="Eliminar producto mediante ID")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se eliminó producto con ID indicado.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
			})
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		try {
			productService.deleteProduct(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}