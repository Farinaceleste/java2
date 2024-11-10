package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    private String lastname;
    private boolean status;
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(unique = true, nullable = false)
    private String docNumber;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sale> sales = new ArrayList<>();


    public Client() {
		super();

	}
     
	public Client(String name, String lastname, boolean status, String docNumber,
			String email) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.status = status;
		this.docNumber = docNumber;
		this.email = email;
	}

	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public String getDocNumber() { return docNumber; }
    public void setDocNumber(String docNumber) { this.docNumber = docNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Sale> getSales() { return sales; }
    public void setSales(List<Sale> sales) { this.sales = sales; }
    
    
    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", email=" + email + ", lastname=" + lastname 
                + ", docNumber=" + docNumber + ", status=" + status + ", sales=" + sales + "]";
    }


}
