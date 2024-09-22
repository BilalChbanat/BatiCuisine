package Services;

import Enums.ProjectStatus;
import Interfaces.ProjectInterface;
import Models.MainDoeuvre;
import Models.Material;
import Models.Project;
import Repositories.ProjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProjectService {
    private final ProjectInterface projectRepository;
    private final Scanner scanner;

    public ProjectService(ProjectInterface projectRepository) {
        this.projectRepository = projectRepository;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n═════════════════════ Project Management ═════════════════════");
            System.out.println("1: Create a new project");
            System.out.println("2: View all projects");
            System.out.println("3: Update a project");
            System.out.println("4: Calculate project cost");
            System.out.println("5: Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createProject();
                    break;
                case 2:
                    viewAllProjects();
                    break;
                case 3:
                    updateProject();
                    break;
                case 4:
                    calculateProjectCost();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createProject() {
        System.out.print("Enter project name: ");
        String name = scanner.nextLine();
        System.out.print("Enter profit margin: ");
        double profitMargin = scanner.nextDouble();
        System.out.print("Enter total cost: ");
        double totalCost = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter project status (e.g., IN_PROGRESS, COMPLETED): ");
        String statusInput = scanner.nextLine();

        // Validate input against enum constants
        ProjectStatus projectStatus;
        try {
            projectStatus = ProjectStatus.valueOf(statusInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid project status. Please enter a valid status.");
            return;
        }

        Project project = new Project(0, name, profitMargin, totalCost, projectStatus);
        projectRepository.createProject(project);
    }

    private void viewAllProjects() {
        List<Project> projects = projectRepository.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("No projects found.");
        } else {
            System.out.println("List of projects:");
            for (Project project : projects) {
                System.out.println(project);
            }
        }
    }

    private void updateProject() {
        System.out.print("Enter the ID of the project to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Optional<Project> optionalProject = projectRepository.getProjectById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            System.out.print("Enter new name (leave blank to keep current): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                project.setName(newName);
            }
            System.out.print("Enter new profit margin (leave blank to keep current): ");
            String newProfitMarginInput = scanner.nextLine();
            if (!newProfitMarginInput.isEmpty()) {
                project.setProfitMargin(Double.parseDouble(newProfitMarginInput));
            }
            System.out.print("Enter new total cost (leave blank to keep current): ");
            String newTotalCostInput = scanner.nextLine();
            if (!newTotalCostInput.isEmpty()) {
                project.setTotalCost(Double.parseDouble(newTotalCostInput));
            }
            System.out.print("Enter new project status (leave blank to keep current): ");
            String newStatusInput = scanner.nextLine();
            if (!newStatusInput.isEmpty()) {
                project.setProjectStatus(ProjectStatus.valueOf(newStatusInput));
            }

            projectRepository.updateProject(project);
        } else {
            System.out.println("Project not found.");
        }
    }

    private void calculateProjectCost() {
        System.out.print("Enter the ID of the project to calculate cost: ");
        int id = scanner.nextInt();
        double cost = projectRepository.calculateProjectCost(id);
        System.out.println("Total cost for project ID " + id + " is: " + cost);
    }

    public static void main(String[] args) {
        ProjectInterface projectRepository = new ProjectRepository();
        ProjectService projectService = new ProjectService(projectRepository);
        projectService.displayMenu();
    }
}
