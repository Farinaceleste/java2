package com.coderhouse.DTO;

public class ProductDTO {

	
	private String productName;
	private int quantity;
	private Double price;
	
	
	public ProductDTO(String productName, int quantity, Double price) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "ProductDTO [productName=" + productName + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	
	
	
}
