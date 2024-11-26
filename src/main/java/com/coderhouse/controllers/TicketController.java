package com.coderhouse.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.DTO.TicketDTO;
import com.coderhouse.models.Ticket;
import com.coderhouse.services.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketDTO>> findAll() {
        List<TicketDTO> tickets = this.ticketService.findAll();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> one(@PathVariable Integer id) {
        Optional<TicketDTO> ticketDTOOptional = this.ticketService.findById(id);

        if (ticketDTOOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ticketDTOOptional.get());
    }

    @PostMapping
    public ResponseEntity<TicketDTO> newEntity(@RequestBody Ticket ticket) {
        TicketDTO ticketDTO = this.ticketService.save(ticket);
        if (ticketDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketDTO);
    }
}