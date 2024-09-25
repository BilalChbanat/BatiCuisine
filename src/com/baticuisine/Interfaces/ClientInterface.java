package com.baticuisine.Interfaces;

import com.baticuisine.Models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientInterface {
    void addClient(Client client);
    Optional<Client> getClientByName(String name);
    List<Client> getAllClients();
    void updateClient(Client client);
}
