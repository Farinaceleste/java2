package com.coderhouse.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description="Product Model")
@Table(name="Products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable{


	private static final long serialVersionUID = 1L;
    @Schema(description="Product ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    @Schema(description="Product title", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
    @Schema(description="Product description", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(description="Product price", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer price;
    @Schema(description="Stock available", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantity;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	    @ManyToMany(mappedBy = "products")
    private List<Sale> sales = new ArrayList<>();
	
}