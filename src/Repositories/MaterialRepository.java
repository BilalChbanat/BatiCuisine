package Repositories;

import Interfaces.MaterialInterface;
import Models.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Database.DatabaseConnection; // Assuming you have a DatabaseConnection class

public class MaterialRepository implements MaterialInterface {
    private final Connection connection;

    public MaterialRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void addMaterial(Material material) {
        String query = "INSERT INTO materials (name, quantite, coutUnitaire, coutTransport, coefficientQualite) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, material.getName());
            stmt.setDouble(2, material.getQuantity());
            stmt.setDouble(3, material.getUnitCost());
            stmt.setDouble(4, material.getTransportCost());
            stmt.setDouble(5, material.getQualityCoefficient());
            stmt.executeUpdate();
            System.out.println("Material added to database: " + material.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Material> getAllMaterials() {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM materials";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Material material = new Material(
                        rs.getString("name"),
                        rs.getDouble("quantite"),
                        rs.getDouble("coutUnitaire"),
                        rs.getDouble("coutTransport"),
                        rs.getDouble("coefficientQualite")
                );
                materials.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }

    @Override
    public Optional<Material> getMaterialByName(String name) {
        String query = "SELECT * FROM materials WHERE name = ?";
        Material material = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                material = new Material(
                        rs.getString("name"),
                        rs.getDouble("quantite"),
                        rs.getDouble("coutUnitaire"),
                        rs.getDouble("coutTransport"),
                        rs.getDouble("coefficientQualite")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(material);
    }


}
