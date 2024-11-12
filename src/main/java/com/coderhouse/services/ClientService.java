package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Client;
import com.coderhouse.repositories.ClientRepository;

import jakarta.transaction.Transactional;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	public Client findClientById(Long id) {
		return clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
	}

	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

	@Transactional
	public Client updateClient(Long id, Client clientDetails) {
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Client not found"));

		client.setName(clientDetails.getName());
		client.setLastname(clientDetails.getLastname());
		client.setEmail(clientDetails.getEmail());

		if (clientDetails.getDocNumber() != null || !clientDetails.getDocNumber().isEmpty()) {
			client.setDocNumber(clientDetails.getDocNumber());
		}

		return clientRepository.save(client);
	}

	public void deleteClient(Long id) {
		if (!clientRepository.existsById(id)) {
			throw new IllegalArgumentException("Client not found");
		}

		clientRepository.deleteById(id);
	}
}
