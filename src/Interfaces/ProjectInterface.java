package Interfaces;

import Models.MainDoeuvre;
import Models.Material;
import Models.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectInterface {
    void createProject(Project project);
//    void addLaborToProject(int id, MainDoeuvre mainDoeuvre);
    double calculateProjectCost(int id);
    void updateProject(Project project);
    List<Project> getAllProjects();

    Optional<Project> getProjectById(int id);
}
