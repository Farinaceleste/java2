package com.coderhouse.DTO;

public class ProductDTO {

	
	private String productName;
	private int quantity;
	private Double price;
	private Integer id;
	private String description;
	

	public ProductDTO(String productName, int quantity, Double price, Integer id, String description) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.id = id;
		this.description = description;
	}


	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
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


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "ProductDTO [productName=" + productName + ", quantity=" + quantity + ", price=" + price + ", id=" + id
				+ ", description=" + description + "]";
	}


	
	
	
	
}
