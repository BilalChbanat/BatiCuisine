package com.baticuisine.Repositories;

import com.baticuisine.Database.DatabaseConnection;
import com.baticuisine.Interfaces.MainDoeuvreInterface;
import com.baticuisine.Models.MainDoeuvre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainDoeuvreRepository implements MainDoeuvreInterface {

    private final Connection connection;

    public MainDoeuvreRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void add(MainDoeuvre mainDoeuvre, int projectId) {
        String query = "INSERT INTO maindoeuvre (name, typeComposant, tauxTva, tauxHoraire, heuresTravail, productuviteouvrier, project_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, mainDoeuvre.getName());
            statement.setString(2, mainDoeuvre.getTypeComposant());
            statement.setDouble(3, mainDoeuvre.getTauxTVA());
            statement.setDouble(4, mainDoeuvre.getTauxHoraire());
            statement.setDouble(5, mainDoeuvre.getHeuresTravail());
            statement.setDouble(6, mainDoeuvre.getProductiviteOuvrier());
            statement.setInt(7, projectId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while adding main-d'œuvre: " + e.getMessage());
        }
    }

    @Override
    public MainDoeuvre findById(int id) {
        String query = "SELECT * FROM maindoeuvre WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new MainDoeuvre(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("typecomposant"),
                        resultSet.getDouble("tauxtva"),
                        resultSet.getDouble("tauxhoraire"),
                        resultSet.getDouble("heurestravail"),
                        resultSet.getDouble("productuviteouvrier")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving main-d'œuvre by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<MainDoeuvre> findByProjectId(int projectId) {
        String query = "SELECT * FROM maindoeuvre WHERE project_id = ?";
        List<MainDoeuvre> laborList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, projectId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                MainDoeuvre mainDoeuvre = new MainDoeuvre(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("typecomposant"),
                        resultSet.getDouble("tauxtva"),
                        resultSet.getDouble("tauxhoraire"),
                        resultSet.getDouble("heurestravail"),
                        resultSet.getDouble("productuviteouvrier")
                );
                laborList.add(mainDoeuvre);
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving labor list by project ID: " + e.getMessage());
        }
        return laborList;
    }
}
