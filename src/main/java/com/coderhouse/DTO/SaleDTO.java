package com.coderhouse.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class SaleDTO {

	private String client_name;
	private String client_email;
	private List<ProductDTO> productDetails;
	private Double totalAmount;
	private LocalDateTime purchaseDate;
	
	
	public SaleDTO(String client_name, String client_email, List<ProductDTO> productDetails, Double totalAmount,
			LocalDateTime purchaseDate) {
		super();
		this.client_name = client_name;
		this.client_email = client_email;
		this.productDetails = productDetails;
		this.totalAmount = totalAmount;
		this.purchaseDate = purchaseDate;
	}


	public String getClient_name() {
		return client_name;
	}


	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}


	public String getClient_email() {
		return client_email;
	}


	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}


	public List<ProductDTO> getProductDetails() {
		return productDetails;
	}


	public void setProductDetails(List<ProductDTO> productDetails) {
		this.productDetails = productDetails;
	}


	public Double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}


	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}


	@Override
	public String toString() {
		return "SaleDTO [client_name=" + client_name + ", client_email=" + client_email + ", productDetails="
				+ productDetails + ", totalAmount=" + totalAmount + ", purchaseDate=" + purchaseDate + "]";
	}
	
	
	
	
	
	
}
