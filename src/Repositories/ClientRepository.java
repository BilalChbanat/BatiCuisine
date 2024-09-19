package Repositories;

import Interfaces.ClientInterface;
import Models.Client;

import java.util.List;
import java.util.Optional;

public class ClientRepository implements ClientInterface {
    @Override
    public void addClient(Client client) {

    }

    @Override
    public Optional<Client> getClientByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Client> getAllClients() {
        return List.of();
    }
}
