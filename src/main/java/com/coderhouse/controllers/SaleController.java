package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@Operation(summary="Obtener la lista completa de ventas")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se obtuvo lista de ventas completa", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Sale.class))
			})
	})
	@GetMapping
	public ResponseEntity<List<Sale>> getAllSales() {

		try {
			List<Sale> sales = saleService.getAllSales();
			return ResponseEntity.ok(sales);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary="Obtener una venta determinada mediante ID")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se obtuvo venta con ID específico", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Sale.class))
			})
	})
	@GetMapping("/{id}")
	public ResponseEntity<Sale> findById(@PathVariable Long id) {
		try {
			Sale sale = saleService.findSaleById(id);
			return ResponseEntity.ok(sale);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary="Crear nueva venta")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se creó la venta correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Sale.class))
			})
	})
	@PostMapping
	public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
		try {
			Sale createdSale = saleService.saveSale(sale);
			return ResponseEntity.ok(createdSale);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary="Modifica venta mediante ID")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se modificó la venta correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Sale.class))
			})
	})
	@PutMapping
	public ResponseEntity<Sale> updateSale(@PathVariable Long id, @RequestBody Sale saleDetails) {
		try {
			Sale updatedSale = saleService.updateSale(id, saleDetails);
			return ResponseEntity.ok(updatedSale);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@Operation(summary="Se elimina venta con ID específico")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se eliminó el producto correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Sale.class))
			})
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
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
