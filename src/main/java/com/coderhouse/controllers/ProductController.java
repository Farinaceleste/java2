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

import com.coderhouse.apis.ProductRestApi;
import com.coderhouse.models.Client;
import com.coderhouse.models.Product;
import com.coderhouse.models.Sale;
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
	private ProductRestApi productRestApi;

	@Operation(summary = "Obtener lista completa de productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Product>> getAllProducts() {

		try {
			List<Product> products = productRestApi.getAllProducts();
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Operation(summary = "Obtener producto mediante ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto obtenido correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content) })
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		try {
			Product product = productRestApi.getProductById(id);
			return ResponseEntity.ok(product);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Crear nuevo producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Producto creado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		try {
			Product createdProduct = productRestApi.createProduct(product);
			return ResponseEntity.ok(createdProduct);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Modificar producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto modificado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content) })
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
		try {
			productDetails.setId(id);
			Product updatedProduct = productRestApi.updateProduct(productDetails);
			return ResponseEntity.ok(updatedProduct);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Eliminar un producto mediante ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Producto eliminado correctamente", content = @Content),
			@ApiResponse(responseCode = "404", description = "Producto no encontrada", content = @Content) })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		try {
			productRestApi.deleteProduct(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}