package Repositories;

import Database.DatabaseConnection;
import Interfaces.ClientInterface;
import Models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements ClientInterface {

    private Connection connection;

    public ClientRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void addClient(Client client) {
        String query = "INSERT INTO clients (name, address, phone, isProfessional) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getAddress());
            stmt.setString(3, client.getPhone());
            stmt.setBoolean(4, client.isProfessional());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Client> getClientByName(String name) {
        String query = "SELECT * FROM clients WHERE name = ?";
        Client client = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                client = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getBoolean("isProfessional")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(client);
    }

    @Override
    public List<Client> getAllClients() {
        // Corrected table name from "batiCuisine" to "clients"
        String query = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getBoolean("isProfessional")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public void updateClient(Client client) {
        String query = "UPDATE clients SET name = ?, address = ?, phone = ?, isProfessional = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getAddress());
            stmt.setString(3, client.getPhone());
            stmt.setBoolean(4, client.isProfessional());
            stmt.setInt(5, client.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
