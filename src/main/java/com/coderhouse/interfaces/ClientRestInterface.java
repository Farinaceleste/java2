package com.coderhouse.interfaces;

import java.util.List;

import com.coderhouse.models.Client;

public interface ClientRestInterface {

	public List<Client> getAllClients();
	
	public Client getClientById(String id);
	
	public Client addClient(Client client);
	
	public Client updateClient(Client client);
	
	public void deleteClient(String id);
}
