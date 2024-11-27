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

	public Client findClientById(Integer id) {
		return clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
	}

	@Transactional
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

	@Transactional
	public Client updateClient(Integer id, Client clientDetails) {
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Client not found"));

		client.setFirstName(clientDetails.getFirstName());
		client.setLastName(clientDetails.getLastName());
		client.setEmail(clientDetails.getEmail());

		if (clientDetails.getDocNumber() != null || !clientDetails.getDocNumber().isEmpty()) {
			client.setDocNumber(clientDetails.getDocNumber());
		}

		return clientRepository.save(client);
	}

	public void deleteClient(Integer id) {
		if (!clientRepository.existsById(id)) {
			throw new IllegalArgumentException("Client not found");
		}

		clientRepository.deleteById(id);
	}
}
