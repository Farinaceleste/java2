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

import com.coderhouse.models.Sale;
import com.coderhouse.services.SaleService;

@RestController
@RequestMapping("/api/sales")

public class SaleController {

	@Autowired
	private SaleService saleService;

	@GetMapping
	public ResponseEntity<List<Sale>> getAllSales() {

		try {
			List<Sale> sales = saleService.getAllSales();
			return ResponseEntity.ok(sales);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/{id")
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


	@PostMapping
	public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
		try {
			Sale createdSale = saleService.saveSale(sale);
			return ResponseEntity.ok(createdSale);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
