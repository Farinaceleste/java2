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

	public Sale findSaleById(Long id) {
		return saleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sale not found"));
	}

	public Sale saveSale(Sale sale) {
		return saleRepository.save(sale);
	}

	@Transactional
	public Sale updateSale(Long id, Sale saleDetails) {
		Sale sale = saleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Sale not found"));

		sale.setName(saleDetails.getName());
		sale.setClient(saleDetails.getClient());
		
		return saleRepository.save(sale);
	}

	public void deleteSale(Long id) {
		if (!saleRepository.existsById(id)) {
			throw new IllegalArgumentException("Client not found");
		}

		saleRepository.deleteById(id);
	}
}
