package Repositories;

import Interfaces.ProjectInterface;
import Models.MainDoeuvre;
import Models.Material;
import Models.Project;

import java.util.List;

public class ProjectRepository implements ProjectInterface {
    @Override
    public void createProject(Project project) {

    }

    @Override
    public void addMaterialToProject(int projectId, Material material) {

    }

    @Override
    public void addLaborToProject(int id, MainDoeuvre mainDoeuvre) {

    }

    @Override
    public double calculateProjectCost(int id) {
        return 0;
    }

    @Override
    public List<Project> getAllProjects() {
        return List.of();
    }
}
