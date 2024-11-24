package com.coderhouse.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class SaleDTO {

	private String client_firstName;
	private String client_lastName;
	private String client_email;
	private Long client_id;
	private Long productDetails;
	private Double totalAmount;
	private LocalDateTime purchaseDate;
	

	public SaleDTO(String client_firstName, String client_lastName, String client_email, Long client_id, Long productDetails, Double totalAmount,
			LocalDateTime purchaseDate) {
		super();
		this.client_firstName = client_firstName;
		this.client_lastName = client_lastName;
		this.client_email = client_email;
		this.client_id = client_id;
		this.productDetails = productDetails;
		this.totalAmount = totalAmount;
		this.purchaseDate = purchaseDate;
	}


	public String getClient_firstName() {
		return client_firstName;
	}


	public void setClient_firstName(String client_firstName) {
		this.client_firstName = client_firstName;
	}


	public String getClient_lastName() {
		return client_lastName;
	}


	public void setClient_lastName(String client_lastName) {
		this.client_lastName = client_lastName;
	}


	public String getClient_email() {
		return client_email;
	}


	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}


	public Long getClient_id() {
		return client_id;
	}


	public void setClient_id(Long client_id) {
		this.client_id = client_id;
	}


	public Long getProductDetails() {
		return productDetails;
	}


	public void setProductDetails(Long productDetails) {
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
		return "SaleDTO [client_firstName=" + client_firstName + ", client_lastName=" + client_lastName
				+ ", client_email=" + client_email + ", client_id=" + client_id + ", productDetails=" + productDetails
				+ ", totalAmount=" + totalAmount + ", purchaseDate=" + purchaseDate + "]";
	}


}
