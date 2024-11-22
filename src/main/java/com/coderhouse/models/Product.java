package com.coderhouse.models;

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
@Getter
@Setter
@Entity
@Schema(description="Product Model")
@Table(name="Products")
public class Product {
	
    @Schema(description="Product ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    @Schema(description="Product title", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
    @Schema(description="Product description", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(description="Product price", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer price;
    @Schema(description="Stock available", requiredMode = Schema.RequiredMode.REQUIRED)
    
    private Integer stock;

    @ManyToMany(mappedBy = "products")
    private List<Sale> sales = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}