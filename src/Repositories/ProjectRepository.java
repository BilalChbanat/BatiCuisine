package Repositories;

import Database.DatabaseConnection;
import Enums.ProjectStatus;
import Interfaces.ProjectInterface;
import Models.MainDoeuvre;
import Models.Material;
import Models.Project;

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
        String query = "INSERT INTO projects (name, profit_margin, total_cost, project_status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, project.getName());
            stmt.setDouble(2, project.getProfitMargin());
            stmt.setDouble(3, project.getTotalCost());
            stmt.setString(4, project.getProjectStatus().name());

            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                project.setId(generatedKeys.getInt(1)); // Set the generated ID
                System.out.println("Project created: " + project.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProject(Project project) {
        String query = "UPDATE projects SET name = ?, profit_margin = ?, total_cost = ?, project_status = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, project.getName());
            stmt.setDouble(2, project.getProfitMargin());
            stmt.setDouble(3, project.getTotalCost());
            stmt.setString(4, project.getProjectStatus().name());
            stmt.setInt(5, project.getId());

            stmt.executeUpdate();
            System.out.println("Project updated: " + project.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calculateProjectCost(int id) {
        Optional<Project> projectOptional = getProjectById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            return project.getTotalCost(); // Adjust this based on your logic
        }
        System.out.println("Project not found.");
        return 0.0;
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
                        ProjectStatus.valueOf(rs.getString("project_status"))
                );
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public Optional<Project> getProjectById(int id) {
        String query = "SELECT * FROM projects WHERE id = ?";
        Project project = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                project = new Project(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("profit_margin"),
                        rs.getDouble("total_cost"),
                        ProjectStatus.valueOf(rs.getString("project_status"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(project);
    }
}
