package Services;

import Models.MainDoeuvre;
import Repositories.MainDoeuvreRepository;

import java.util.List;
import java.util.Scanner;

public class MainDoeuvreService {
    private MainDoeuvreRepository mainDoeuvreRepository;
    private Scanner scanner;

    public MainDoeuvreService(MainDoeuvreRepository mainDoeuvreRepository) {
        this.mainDoeuvreRepository = mainDoeuvreRepository;
        this.scanner = new Scanner(System.in);
    }

    // Add new labor
    public void addLabor(int projectId) {
        System.out.println("--- Ajout de la main-d'œuvre ---");

        System.out.print("Entrez le nom de la main-d'œuvre : ");
        String name = scanner.nextLine();

        System.out.print("Entrez le type de composant : ");
        String typeComposant = scanner.nextLine();

        System.out.print("Entrez le taux de TVA (%) : ");
        double tauxTVA = scanner.nextDouble();

        System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
        double tauxHoraire = scanner.nextDouble();

        System.out.print("Entrez le nombre d'heures travaillées : ");
        double heuresTravail = scanner.nextDouble();

        System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
        double productiviteOuvrier = scanner.nextDouble();

        // Create a new MainDoeuvre instance and set the fields
        MainDoeuvre mainDoeuvre = new MainDoeuvre(0, name, typeComposant, tauxTVA);
        mainDoeuvre.setTauxHoraire(tauxHoraire);
        mainDoeuvre.setHeuresTravail(heuresTravail);
        mainDoeuvre.setProductiviteOuvrier(productiviteOuvrier);

        // Add labor to the repository
        mainDoeuvreRepository.add(mainDoeuvre, projectId);
        System.out.println("Main-d'œuvre ajoutée avec succès !");
    }

    // Get labor by ID
    public void getLaborById() {
        System.out.print("Entrez l'ID de la main-d'œuvre : ");
        int laborId = scanner.nextInt();
        MainDoeuvre labor = mainDoeuvreRepository.findById(laborId);

        if (labor != null) {
            System.out.println("Détails de la main-d'œuvre : " + labor);
        } else {
            System.out.println("Main-d'œuvre non trouvée.");
        }
    }

    // Get all labor for a project
    public void getAllLaborForProject(int projectId) {
        List<MainDoeuvre> laborList = mainDoeuvreRepository.findByProjectId(projectId);

        if (!laborList.isEmpty()) {
            System.out.println("--- Liste des main-d'œuvre pour le projet ID: " + projectId + " ---");
            laborList.forEach(System.out::println);
        } else {
            System.out.println("Aucune main-d'œuvre trouvée pour ce projet.");
        }
    }

    public static void main(String[] args) {
        // Initialize the repository (this would normally involve database connection logic)
        MainDoeuvreRepository mainDoeuvreRepository = new MainDoeuvreRepository();

        // Initialize the service
        MainDoeuvreService mainDoeuvreService = new MainDoeuvreService(mainDoeuvreRepository);

        // Scanner for user interaction in the console
        Scanner scanner = new Scanner(System.in);

        // Simulate adding labor for a project
        System.out.println("Entrez l'ID du projet : ");
        int projectId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character after nextInt()

        // Add labor to the project
        mainDoeuvreService.addLabor(projectId);

        // Fetch and display all labor for the project
        System.out.println("Afficher toutes les main-d'œuvre pour le projet ID: " + projectId);
        mainDoeuvreService.getAllLaborForProject(projectId);

        // Get labor by ID (example)
        mainDoeuvreService.getLaborById();
    }
}
