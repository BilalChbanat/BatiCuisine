package com.baticuisine.Repositories;

import com.baticuisine.Interfaces.ComposantInterface;
import com.baticuisine.Models.Composant;
import com.baticuisine.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComposantRepository implements ComposantInterface {
    private final Connection connection;

    public ComposantRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void add(Composant composant) {
        String query = "INSERT INTO composants (name, typeComposant, tauxTVA) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, composant.getName());
            stmt.setString(2, composant.getTypeComposant());
            stmt.setDouble(3, composant.getTauxTVA());
            stmt.executeUpdate();
            System.out.println("Composant added to database: " + composant.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Composant findByName(String name) {
        String query = "SELECT * FROM composants WHERE name = ?";
        Composant composant = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                composant = new Composant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("typeComposant"),
                        rs.getDouble("tauxTVA")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return composant;
    }

    @Override
    public List<Composant> findAll() {
        List<Composant> composants = new ArrayList<>();
        String query = "SELECT * FROM composants";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Composant composant = new Composant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("typeComposant"),
                        rs.getDouble("tauxTVA")
                );
                composants.add(composant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return composants;
    }

    @Override
    public void update(Composant composant) {
        String query = "UPDATE composants SET name = ?, typeComposant = ?, tauxTVA = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, composant.getName());
            stmt.setString(2, composant.getTypeComposant());
            stmt.setDouble(3, composant.getTauxTVA());
            stmt.setInt(4, composant.getId());
            stmt.executeUpdate();
            System.out.println("Composant updated: " + composant.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM composants WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Composant deleted with id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
