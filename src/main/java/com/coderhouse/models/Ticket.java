package com.coderhouse.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="ticket")
@ NamedQuery(name="Ticket.findAll()", query="SELECT c from Ticket c")
public class Ticket implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer TicketId;
	private Integer quantity;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	private BigDecimal total;
	
    @ManyToOne
    @JoinColumn(name = "id")  
    private Client client;
	
	@OneToMany(mappedBy="ticket", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Line> lines = new HashSet<>();
	
	public Ticket() {
		
	}

	public Integer getTicketId() {
		return TicketId;
	}

	public void setTicketId(Integer ticketId) {
		TicketId = ticketId;
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

	public void setDate(java.util.Date date1) {
		this.date = (Date) date1;
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

	public Set<Line> getLines() {
		return lines;
	}

	public void setLines(Set<Line> lines) {
		this.lines = lines;
	}
	
	public Line removeLine(Line line) {
		getLines().remove(line);
		line.setTicket(null);
		
		return line;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticket [");
		if (TicketId != null)
			builder.append("ticketId=").append(TicketId).append(", ");
		if (quantity != null)
			builder.append("quantity=").append(quantity).append(", ");
		if (date != null)
			builder.append("date=").append(date).append(", ");
		if (total != null)
			builder.append("total=").append(total).append(", ");
		if (client != null)
			builder.append("client=").append(client).append(", ");
		if (lines != null)
			builder.append("lines=").append(lines);
		builder.append("]");
		return builder.toString();
	}

	public void addLine(Line line) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
