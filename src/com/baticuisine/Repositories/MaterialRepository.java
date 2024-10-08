package com.baticuisine.Repositories;

import com.baticuisine.Interfaces.MaterialInterface;
import com.baticuisine.Models.Material;
import com.baticuisine.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterialRepository implements MaterialInterface {
    private final Connection connection;

    public MaterialRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void addMaterial(Material material) {
        String query = "INSERT INTO materials (name, quantite, coutUnitaire, coutTransport, coefficientQualite, tauxTVA, typeComposant, project_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, material.getName());
            stmt.setDouble(2, material.getQuantity());
            stmt.setDouble(3, material.getUnitCost());
            stmt.setDouble(4, material.getTransportCost());
            stmt.setDouble(5, material.getQualityCoefficient());
            stmt.setDouble(6, material.getTauxTVA());
            stmt.setString(7, material.getTypeComposant());
            stmt.setInt(8, material.getProjectId());
            stmt.executeUpdate();
            System.out.println("Material added to database: " + material.getName());
        } catch (SQLException e) {
            System.err.println("Error adding material: " + e.getMessage());
        }
    }

    @Override
    public List<Material> getAllMaterials() {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM materials";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Material material = new Material(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("typeComposant"),
                        rs.getDouble("tauxTVA"),
                        rs.getDouble("quantite"),
                        rs.getDouble("coutUnitaire"),
                        rs.getDouble("coutTransport"),
                        rs.getDouble("coefficientQualite"),
                        rs.getInt("project_id")
                );
                materials.add(material);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving materials: " + e.getMessage());
        }

        return materials;
    }

    @Override
    public Optional<Material> getMaterialByName(String name) {
        String query = "SELECT * FROM materials WHERE name = ?";
        Material material = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    material = new Material(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("typeComposant"),
                            rs.getDouble("tauxTVA"),
                            rs.getDouble("quantite"),
                            rs.getDouble("coutUnitaire"),
                            rs.getDouble("coutTransport"),
                            rs.getDouble("coefficientQualite"),
                            rs.getInt("project_id")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving material by name: " + e.getMessage());
        }

        return Optional.ofNullable(material);
    }

    public void updateMaterial(Material material) {
        String query = "UPDATE materials SET name = ?, quantite = ?, coutUnitaire = ?, coutTransport = ?, coefficientQualite = ?, tauxTVA = ?, typeComposant = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, material.getName());
            stmt.setDouble(2, material.getQuantity());
            stmt.setDouble(3, material.getUnitCost());
            stmt.setDouble(4, material.getTransportCost());
            stmt.setDouble(5, material.getQualityCoefficient());
            stmt.setDouble(6, material.getTauxTVA());
            stmt.setString(7, material.getTypeComposant());
            stmt.setInt(8, material.getId());

            stmt.executeUpdate();
            System.out.println("Material updated in database: " + material.getName());
        } catch (SQLException e) {
            System.err.println("Error updating material: " + e.getMessage());
        }
    }


    public void deleteMaterial(int materialId) {
        String query = "DELETE FROM materials WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, materialId);
            stmt.executeUpdate();
            System.out.println("Material deleted from database with ID: " + materialId);
        } catch (SQLException e) {
            System.err.println("Error deleting material: " + e.getMessage());
        }
    }

    public List<Material> findMaterialProject(int projectId) {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM materials WHERE project_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, projectId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Material material = new Material(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("typeComposant"),
                            rs.getDouble("tauxTVA"),
                            rs.getDouble("quantite"),
                            rs.getDouble("coutUnitaire"),
                            rs.getDouble("coutTransport"),
                            rs.getDouble("coefficientQualite"),
                            rs.getInt("project_id")
                    );
                    materials.add(material);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving materials for project ID " + projectId + ": " + e.getMessage());
        }

        return materials;
    }
}
