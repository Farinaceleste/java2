package com.coderhouse.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Client;
import com.coderhouse.models.Product;
import com.coderhouse.services.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/clients")
@Tag(name="ClientController", description="Controlador para manejar clientes")

public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Operation(summary="Devuelve lista completa de clientes")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se obtuvo lista completa de clientes correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
			})
	})
	@GetMapping
	public ResponseEntity<List<Client>> getAllClients() {

		try {
			List<Client> clients = clientService.getAllClients();
			return ResponseEntity.ok(clients);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary="Busca cliente mediante ID")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se encontró el cliente correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
			})
	})
	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		try {
			Client client = clientService.findClientById(id);
			return ResponseEntity.ok(client);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary="Crear nuevo cliente")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se creó nuevo cliente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
			})
	})
	@PostMapping
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		try {
			Client createdClient = clientService.saveClient(client);
			return ResponseEntity.ok(createdClient);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Modifica cliente mediante ID")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se modificó el cliente correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
			})
	})
	@PutMapping
	public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
		try {
			Client updatedClient = clientService.updateClient(id, clientDetails);
			return ResponseEntity.ok(updatedClient);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@Operation(summary="Elimina cliente mediante ID")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Se eliminó el cliente correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
			})
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
		try {
			clientService.deleteClient(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
