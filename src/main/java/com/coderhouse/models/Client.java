package com.coderhouse.models;

import java.time.LocalDateTime;
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
@Schema(description="Client Model")
@Table(name = "Clients")
public class Client {
	
	@Schema(description="Client ID", requiredMode = Schema.RequiredMode.REQUIRED)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(name = "name")
    @Schema(description="Client name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;
    @Schema(description="Client last name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;
    @Schema(description="Creation date", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(unique = true, nullable = false)
    private String docNumber;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sale> sales = new ArrayList<>();
    
}
