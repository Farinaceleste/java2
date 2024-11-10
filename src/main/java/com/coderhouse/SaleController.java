package com.coderhouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Client;
import com.coderhouse.models.Product;
import com.coderhouse.models.Sale;
import com.coderhouse.repositories.SaleRepository;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
	
	@Autowired
	private SaleRepository saleRepository;
	
	@GetMapping
	public List<Sale> getAllSales() {
		return saleRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Sale> getSaleById(@PathVariable Long id){
		if(saleRepository.existsById(id)) {
			Sale sale = saleRepository.findById(id).get();
			return ResponseEntity.ok(sale);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public Sale createSale(@RequestBody Sale sale) {
		return saleRepository.save(sale);
		
	}
	
	
	
	
	
}
