package com.coderhouse.models;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

	@Entity
	@Table(name="linea")
	@NamedQuery(name="Linea.findAll", query="SELECT l FROM Linea l")
public class Line implements Serializable{
	

		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer lineId;

		private Integer quantity;

		private String description;

		private BigDecimal price;

		//bi-directional many-to-one association to Comprobante
		@ManyToOne
		@JoinColumn(name="ticketId")
		private Ticket ticket;

		@ManyToOne
		@JoinColumn(name="productId")
		private Product product;

		public Line() {
		}


		public Integer getLineId() {
			return lineId;
		}


		public void setLineId(Integer lineId) {
			this.lineId = lineId;
		}


		public Integer getQuantity() {
			return quantity;
		}


		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public BigDecimal getPrice() {
			return price;
		}


		public void setProcec(BigDecimal price) {
			this.price = price;
		}


		public Ticket getTicket() {
			return ticket;
		}


		public void setTicket(Ticket ticket) {
			this.ticket = ticket;
		}


		public Product getProduct() {
			return product;
		}


		public void setProduct(Product product) {
			this.product = product;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}


		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Linea [lineId=").append(lineId).append(", quantity=").append(quantity).append(", ");
			if (description != null)
				builder.append("description=").append(description).append(", ");
			if (price != null)
				builder.append("price=").append(price);
			builder.append("]");
			return builder.toString();
		}
		
	
}
