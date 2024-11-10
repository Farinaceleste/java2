package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="Products")
public class Product {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
    private String description;
    private Integer price;
    private Integer stock;

    @ManyToMany(mappedBy = "products")
    private List<Sale> sales = new ArrayList<>();

    public Product() {
		super();
	}
    
    
	public Product(String title, String description, Integer price, Integer stock) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}


	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public List<Sale> getSales() { return sales; }
    public void setSales(List<Sale> sales) { this.sales = sales; }

    @Override
    public String toString() {
        return "Product [id=" + id + ", title=" + title + ", description=" + description + ", stock=" + stock + ", price=" + price + "]";
    }
}