package Services;

import Enums.ProjectStatus;
import Interfaces.DevisInterface;
import Models.Devis;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Models.Material;
import Repositories.DevisRepository;
import Repositories.MaterialRepository;
import Repositories.ProjectRepository;

public class DevisService {
    private final DevisInterface devisRepository;
    private final Scanner scanner;
    private MaterialRepository materialRepository =  new MaterialRepository();

    public DevisService(DevisInterface devisRepository) {
        this.devisRepository = devisRepository;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("=== Devis Menu ===");
            System.out.println("1. Add Devis");
            System.out.println("2. View All Devis");
            System.out.println("3. Find Devis by ID");
            System.out.println("4. Update Devis");
            System.out.println("5. Delete Devis");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addDevis();
                    break;
                case 2:
                    viewAllDevis();
                    break;
                case 3:
                    findDevisById();
                    break;
                case 4:
                    updateDevis();
                    break;
                case 5:
                    deleteDevis();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addDevis() {
        try {
            System.out.print("Enter project ID: ");
            int projectId = scanner.nextInt();
            scanner.nextLine();

            List<Material> materials = materialRepository.findMaterialProject(projectId);

            if (materials.isEmpty()) {
                System.out.println("No materials found for this project.");
                return;
            }

            MaterialService materialService = new MaterialService();
            List<Material> materialsWithTax = materialService.applyTaxToMaterials(materials);

            double totalMaterialCostWithTax = materialsWithTax.stream()
                    .mapToDouble(material -> material.getUnitCost() * material.getQuantity())
                    .sum();

            System.out.println("Total Material Cost with Tax: " + totalMaterialCostWithTax);

            System.out.print("Enter emission date (yyyy-mm-dd): ");
            LocalDate dateEmission = LocalDate.parse(scanner.nextLine());

            System.out.print("Is it accepted? (true/false): ");
            boolean accepte = scanner.nextBoolean();
            scanner.nextLine();

            System.out.print("Enter validity date (yyyy-mm-dd): ");
            LocalDate dateValide = LocalDate.parse(scanner.nextLine());

            Devis devis = new Devis(0, totalMaterialCostWithTax, dateEmission, accepte, dateValide, projectId);
            devisRepository.add(devis);
            System.out.println("Devis added successfully. ID: " + devis.getId());

            if (accepte) {
                ProjectRepository projectRepository = new ProjectRepository();
                projectRepository.updateProjectStatus(projectId, ProjectStatus.COMPLETED);
                System.out.println("Project status updated to COMPLETED.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }



    private void viewAllDevis() {
        List<Devis> devisList = devisRepository.findAll();
        if (devisList.isEmpty()) {
            System.out.println("No Devis found.");
        } else {
            System.out.println("=== List of Devis ===");
            for (Devis devis : devisList) {
                System.out.println("ID: " + devis.getId() +
                        ", Estimated Amount: " + devis.getMontantEstime() +
                        ", Emission Date: " + devis.getDateEmission() +
                        ", Accepted: " + devis.isAccepte() +
                        ", Validity Date: " + devis.getDateValide() +
                        ", Project ID: " + devis.getProjectId());

            }

        }
    }

    private void findDevisById() {
        System.out.print("Enter Devis ID to find: ");
        int id = scanner.nextInt();
        Devis devis = devisRepository.findById(id);
        if (devis != null) {
            System.out.printf("ID: %d, Estimated Amount: %.2f, Emission Date: %s, Accepted: %b, Validity Date: %s, Project ID: %d%n",
                    devis.getId(), devis.getMontantEstime(), devis.getDateEmission(), devis.isAccepte(), devis.getDateValide(), devis.getProjectId());
        } else {
            System.out.println("Devis not found.");
        }
    }

    private void updateDevis() {
        try {
            System.out.print("Enter Devis ID to update: ");
            int id = scanner.nextInt();
            Devis devis = devisRepository.findById(id);
            if (devis != null) {
                System.out.print("Enter new estimated amount (current: " + devis.getMontantEstime() + "): ");
                devis.setMontantEstime(scanner.nextDouble());
                scanner.nextLine();

                System.out.print("Enter new emission date (current: " + devis.getDateEmission() + ", format yyyy-mm-dd): ");
                devis.setDateEmission(LocalDate.parse(scanner.nextLine()));

                System.out.print("Is it accepted? (current: " + devis.isAccepte() + "): ");
                devis.setAccepte(scanner.nextBoolean());
                scanner.nextLine();

                System.out.print("Enter new validity date (current: " + devis.getDateValide() + ", format yyyy-mm-dd): ");
                devis.setDateValide(LocalDate.parse(scanner.nextLine()));

                System.out.print("Enter new project ID (current: " + devis.getProjectId() + "): ");
                devis.setProjectId(scanner.nextInt());

                devisRepository.update(devis);
                System.out.println("Devis updated successfully.");
            } else {
                System.out.println("Devis not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private void deleteDevis() {
        System.out.print("Enter Devis ID to delete: ");
        int id = scanner.nextInt();
        devisRepository.delete(id);
        System.out.println("Devis deleted successfully.");
    }

//    public static void main(String[] args) {
//        try (Connection connection = Database.DatabaseConnection.getConnection()) {
//            DevisInterface devisRepository = new Repositories.DevisRepository(connection);
//            DevisService devisService = new DevisService(devisRepository);
//            devisService.displayMenu();
//        } catch (SQLException e) {
//            System.out.println("Error connecting to the database: " + e.getMessage());
//        }
//    }
}
