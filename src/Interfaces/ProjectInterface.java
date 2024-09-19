package Interfaces;

import Models.MainDoeuvre;
import Models.Material;
import Models.Project;

import java.util.List;

public interface ProjectInterface {
    void createProject(Project project);
    void addMaterialToProject(int projectId, Material material);
    void addLaborToProject(int id, MainDoeuvre mainDoeuvre);
    double calculateProjectCost(int id);
    List<Project> getAllProjects();
}
