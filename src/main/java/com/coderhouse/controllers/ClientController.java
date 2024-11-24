package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	
	@Operation(summary = "Obtener lista completa de todos los clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de clientes obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Client>> getAllClients() {

		try {
			List<Client> clients = clientService.getAllClients();
			return ResponseEntity.ok(clients);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Obtener cliente por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)) }),
			@ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content) })
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
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

	@Operation(summary = "Agregar nuevo cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cliente agregado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)) }),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		try {
			Client createdClient = clientService.saveClient(client);
			return ResponseEntity.ok(createdClient);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary = "Modificar datos de un cliente existente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente modificado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)) }),
			@ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content) })
	
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
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
	
	@Operation(summary = "Eliminar un alumno existente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Cliente eliminado correctamente", content = @Content),
			@ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content) })
	
	@DeleteMapping(value = "/{id}")
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
