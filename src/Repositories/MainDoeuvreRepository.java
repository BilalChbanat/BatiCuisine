package Repositories;

import Database.DatabaseConnection;
import Interfaces.MainDoeuvreInterface;
import Models.MainDoeuvre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainDoeuvreRepository implements MainDoeuvreInterface {

    private Connection connection;

    public MainDoeuvreRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void add(MainDoeuvre mainDoeuvre, int projectId) {
        String query = "INSERT INTO maindoeuvre (name, typeComposant, tauxTva, tauxHoraire, heuresTravail, productiviteOuvrier, project_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the values from MainDoeuvre and inherited fields
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
                MainDoeuvre mainDoeuvre = new MainDoeuvre(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("typeComposant"),
                        resultSet.getDouble("tauxTva")
                );
                mainDoeuvre.setTauxHoraire(resultSet.getDouble("tauxHoraire"));
                mainDoeuvre.setHeuresTravail(resultSet.getDouble("heuresTravail"));
                mainDoeuvre.setProductiviteOuvrier(resultSet.getDouble("productiviteOuvrier"));
                return mainDoeuvre;
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving main-d'œuvre by ID: " + e.getMessage());
        }
        return null;  // Return null if not found
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
                        resultSet.getString("typeComposant"),
                        resultSet.getDouble("tauxTva")
                );
                mainDoeuvre.setTauxHoraire(resultSet.getDouble("tauxHoraire"));
                mainDoeuvre.setHeuresTravail(resultSet.getDouble("heuresTravail"));
                mainDoeuvre.setProductiviteOuvrier(resultSet.getDouble("productiviteOuvrier"));
                laborList.add(mainDoeuvre);
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving labor list by project ID: " + e.getMessage());
        }
        return laborList;  // Return list of labor associated with the project
    }
}
