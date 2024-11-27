package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Sale;
import com.coderhouse.repositories.SaleRepository;

import jakarta.transaction.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;

	public List<Sale> getAllSales() {
		return saleRepository.findAll();
	}

	public Sale findSaleById(Integer id) {
		return saleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sale not found"));
	}

	@Transactional
	public Sale saveSale(Sale sale) {
		return saleRepository.save(sale);
	}

	@Transactional
	public Sale updateSale(Integer id, Sale saleDetails) {
		Sale sale = saleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Sale not found"));

		sale.setfirstName(saleDetails.getfirstName());
		sale.setClient(saleDetails.getClient());
		
		return saleRepository.save(sale);
	}

	public void deleteSale(Integer id) {
		if (!saleRepository.existsById(id)) {
			throw new IllegalArgumentException("Sale not found");
		}

		saleRepository.deleteById(id);
	}	
}
