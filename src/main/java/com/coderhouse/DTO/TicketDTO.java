package com.coderhouse.DTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.coderhouse.models.Client;


public class TicketDTO {
	
	private Integer ticketId;
	private Integer quantity;
	private Date date;
	private BigDecimal total;
	private Client client;
	private Set<LineDTO> lines;
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Set<LineDTO> getLines() {
		return lines;
	}
	public void setLines(Set<LineDTO> lines) {
		this.lines = lines;
	}
	
	
	
	

}
