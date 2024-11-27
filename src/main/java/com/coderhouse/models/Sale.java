package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description="Sale Model")
@Table(name = "Sales")
public class Sale {
	
    @Schema(description="Sale ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Schema(description="Client Name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;
    @Schema(description="Creation Date", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createdAt = LocalDateTime.now();
    

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	   @ManyToOne
	    @JoinColumn(name = "client_id", nullable = false)
	    private Client client;

	   @ManyToMany
	   @JoinTable(
	       name = "sale_products",
	       joinColumns = @JoinColumn(name = "sale_id"), 
	       inverseJoinColumns = @JoinColumn(name = "product_id") 
	   )
	   @JsonIgnore
	   private List<Product> products = new ArrayList<>();

}