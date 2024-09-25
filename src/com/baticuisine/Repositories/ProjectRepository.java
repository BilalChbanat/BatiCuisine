package com.baticuisine.Repositories;

import com.baticuisine.Database.DatabaseConnection;
import com.baticuisine.Enums.ProjectStatus;
import com.baticuisine.Interfaces.ProjectInterface;
import com.baticuisine.Models.Client;
import com.baticuisine.Models.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepository implements ProjectInterface {
    private final Connection connection;

    public ProjectRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void createProject(Project project) {
        String query = "INSERT INTO projects (name, profit_margin, total_cost, project_status, client_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, project.getName());
            stmt.setDouble(2, project.getProfitMargin());
            stmt.setDouble(3, project.getTotalCost());
            stmt.setString(4, project.getProjectStatus().name());
            stmt.setInt(5, project.getClientId());

            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                project.setId(generatedKeys.getInt(1));
                System.out.println("Project created: " + project.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProjectStatus(int projectId, ProjectStatus status) {
        String sql = "UPDATE projects SET project_status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status.name());
            stmt.setInt(2, projectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating project status: " + e.getMessage());
        }
    }


    private int fetchClientId(Client client) {
        String query = "SELECT id FROM clients WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getName());
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                throw new IllegalStateException("Client not found: " + client.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    @Override
    public void updateProject(Project project) {
        String query = "UPDATE projects SET name = ?, profit_margin = ?, total_cost = ?, project_status = ?, client_id = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, project.getName());
            stmt.setDouble(2, project.getProfitMargin());
            stmt.setDouble(3, project.getTotalCost());
            stmt.setString(4, project.getProjectStatus().name());
            stmt.setInt(5, project.getClientId()); // Directly using clientId from Project
            stmt.setInt(6, project.getId());

            stmt.executeUpdate();
            System.out.println("Project updated: " + project.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Project project = new Project(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("profit_margin"),
                        rs.getDouble("total_cost"),
                        ProjectStatus.valueOf(rs.getString("project_status")),
                        rs.getInt("client_id")
                );
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects; // Return the populated list of projects
    }

    @Override
    public Optional<Project> getProjectById(int id) {
        return Optional.empty();
    }

    @Override
    public double calculateProjectCost(int id) {
        Optional<Project> projectOptional = getProjectById(id);
        return projectOptional.map(Project::getTotalCost).orElse(0.0);
    }


}
