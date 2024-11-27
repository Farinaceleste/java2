package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Client;
import com.coderhouse.models.Product;
import com.coderhouse.models.Sale;
import com.coderhouse.services.SaleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/sales")
@Tag(name="SaleController", description="Controlador para manejar ventas")


public class SaleController {

	@Autowired
	private SaleService saleService;

	@Operation(summary = "Obtener lista completa de ventas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de ventas obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Sale.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Sale>> getAllSales() {

		try {
			List<Sale> sales = saleService.getAllSales();
			return ResponseEntity.ok(sales);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Obtener venta por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Venta encontrada correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Sale.class)) }),
			@ApiResponse(responseCode = "404", description = "Venta no encontrada", content = @Content) })
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Sale> findById(@PathVariable Integer id) {
		try {
			Sale sale = saleService.findSaleById(id);
			return ResponseEntity.ok(sale);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Crear nueva venta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Venta creada correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Sale.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
		try {
			Sale createdSale = saleService.saveSale(sale);
			return ResponseEntity.ok(createdSale);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Modificar venta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Venta modificada correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Sale.class)) }),
			@ApiResponse(responseCode = "404", description = "Venta no encontrada", content = @Content) })
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Sale> updateSale(@PathVariable Integer id, @RequestBody Sale saleDetails) {
		try {
			Sale updatedSale = saleService.updateSale(id, saleDetails);
			return ResponseEntity.ok(updatedSale);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	

	@Operation(summary = "Eliminar una venta mediante ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Venta eliminada correctamente", content = @Content),
			@ApiResponse(responseCode = "404", description = "Venta no encontrada", content = @Content) })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteSale(@PathVariable Integer id) {
		try {
			saleService.deleteSale(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
