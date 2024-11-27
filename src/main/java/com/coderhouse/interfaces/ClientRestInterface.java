package com.coderhouse.interfaces;

import java.util.List;

import com.coderhouse.models.Client;

public interface ClientRestInterface {

	public List<Client> getAllClients();
	
	public Client getClientById(Integer id);
	
	public Client addClient(Client client);
	
	public Client updateClient(Client client);
	
	public void deleteClient(Integer id);
}
