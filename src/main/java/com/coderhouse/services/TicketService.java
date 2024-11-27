package com.coderhouse.services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.DTO.LineDTO;
import com.coderhouse.DTO.TicketDTO;
import com.coderhouse.models.Client;
import com.coderhouse.models.Line;
import com.coderhouse.models.Product;
import com.coderhouse.models.Ticket;
import com.coderhouse.models.WorldClock;
import com.coderhouse.repositories.ClientRepository;
import com.coderhouse.repositories.ProductRepository;
import com.coderhouse.repositories.TicketRepository;

@Service
public class TicketService {
	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RestTemplate restTemplate;

	public List<TicketDTO> findAll() {
		return createTicketDTO(this.ticketRepository.findAll());
	}

	public TicketDTO save(Ticket ticket) {

		Boolean existsClient = existsClient(ticket.getClient());

		Boolean existsProducts = existsProducts(ticket.getLines());

		Boolean existsStock = existsStock(ticket.getLines());

		if (existsClient && existsProducts && existsStock) {

			var ticketToSave = createTicket(ticket);

			actualizarStock(ticketToSave.getLines());

			return createTicketDTO(this.ticketRepository.save(ticketToSave));
		} else {
			return new TicketDTO();
		}
	}

	private void actualizarStock(Set<Line> lines) {
		for (Line line : lines) {
			var quantitySaled = line.getQuantity();
			var product = line.getProduct();

			Product productDB = this.productRepository.findById(product.getId())
					.orElseThrow(() -> new RuntimeException("Product not found"));

			var stock = productDB.getQuantity();
			var newStock = stock - quantitySaled;

			productDB.setQuantity(newStock);

			this.productRepository.save(productDB);
		}
	}

	public Optional<TicketDTO> findById(Integer id) {
		return ticketRepository.findById(id).map(this::createTicketDTO);
	}

	private List<TicketDTO> createTicketDTO(List<Ticket> tickets) {
		List<TicketDTO> ticketsDTOs = new ArrayList<TicketDTO>();
		for (Ticket comprobante : tickets) {
			ticketsDTOs.add(this.createTicketDTO(comprobante));
		}

		return ticketsDTOs;
	}

	private TicketDTO createTicketDTO(Ticket ticket) {
		TicketDTO dto = new TicketDTO();

		dto.setTicketId(ticket.getTicketId());

		dto.setQuantity(ticket.getQuantity());

		dto.setDate(ticket.getDate());

		dto.setTotal(ticket.getTotal());

		dto.setClient(ticket.getClient());

		dto.setLines(createLinesDTO(ticket.getLines()));

		return dto;
	}

	private Set<LineDTO> createLinesDTO(Set<Line> lines) {
		Set<LineDTO> dtos = new HashSet<LineDTO>();

		for (Line line : lines) {

			LineDTO dto = new LineDTO();

			dto.setLineId(line.getLineId());

			dto.setQuantity(line.getQuantity());

			dto.setDescription(line.getDescription());

			dto.setPrice(line.getPrice());

			dtos.add(dto);

		}

		return dtos;
	}

	private Ticket createTicket(Ticket ticket) {
	    var ticketToSave = new Ticket();

	    ticketToSave.setClient(this.clientRepository.findById(ticket.getClient().getId()).orElseThrow(() -> new RuntimeException("Client not found")));

	    WorldClock worldClock = this.restTemplate.getForObject("http://worldclockapi.com/api/json/utc/now", WorldClock.class);
	    String currentDateTime = worldClock.getCurrentDateTime();
	    
	    try {
	        Date date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(currentDateTime);
	        ticketToSave.setDate(date1);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        ticketToSave.setDate(new Date()); 
	    }  

	    ticketToSave.setLines(new HashSet<Line>());
	    
	    for (Line line : ticket.getLines()) {
	        ticketToSave.addLine(createLine(line));  
	    }

	    ticketToSave.setTotal(calcularTotal(ticketToSave.getLines()));

	    ticketToSave.setQuantity(ticket.getLines().size());
	    
	    return ticketToSave;
	}

	private BigDecimal calcularTotal(Set<Line> lines) {
		BigDecimal total = new BigDecimal("0");

		for (Line line : lines) {
			total = total.add(new BigDecimal(line.getPrice().toString()));
		}

		return total;
	}

	private Line createLine(Line line) {
		Line lineToSave = new Line();

		Product productDB = this.productRepository.findById(line.getProduct().getId()).get();
		lineToSave.setQuantity(line.getQuantity());
		lineToSave.setDescription(productDB.getDescription());
		lineToSave.setPrice(productDB.getPrice());
		lineToSave.setProduct(productDB);

		return lineToSave;
	}

	private Boolean existsStock(Set<Line> lines) {
		for (Line line : lines) {
			var productid = line.getProduct().getId();
			var opProduct = this.productRepository.findById(productid);
			if (opProduct.isEmpty()) {
				return false;
			}
			if (line.getQuantity() > opProduct.get().getQuantity()) {
				return false;
			}
		}
		return true;
	}

	private Boolean existsProducts(Set<Line> lines) {
		for (Line line : lines) {
			var productId = line.getProduct().getId();
			var opProduct = this.productRepository.findById(productId);
			if (opProduct.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	private Boolean existsClient(Client client) {
		var opClient = this.clientRepository.findById(client.getId());
		return opClient.isPresent();
	}
}
