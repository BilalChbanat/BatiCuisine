package Services;

import Models.MainDoeuvre;
import Repositories.MainDoeuvreRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainDoeuvreService {
    private final MainDoeuvreRepository mainDoeuvreRepository;
    private final Scanner scanner;

    public MainDoeuvreService(MainDoeuvreRepository mainDoeuvreRepository) {
        this.mainDoeuvreRepository = mainDoeuvreRepository;
        this.scanner = new Scanner(System.in);
    }

    public void addLabor(int projectId) {
        System.out.println("--- Ajout de la main-d'œuvre ---");

        System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
        String name = scanner.nextLine();

        String typeComposant = "Main-d'œuvre";

        double tauxTVA = getDoubleInput("Entrez le taux de TVA (%) : ");
        double tauxHoraire = getDoubleInput("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
        double heuresTravail = getDoubleInput("Entrez le nombre d'heures travaillées : ");
        double productiviteOuvrier = getDoubleInput("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");

        // Create a new MainDoeuvre instance and set the fields
        MainDoeuvre mainDoeuvre = new MainDoeuvre(0, name, typeComposant, tauxTVA, tauxHoraire, heuresTravail, productiviteOuvrier);

        // Add labor to the repository
        mainDoeuvreRepository.add(mainDoeuvre, projectId);
        System.out.println("Main-d'œuvre ajoutée avec succès !");
    }

    private double getDoubleInput(String prompt) {
        double input = -1;
        while (input < 0) {
            try {
                System.out.print(prompt);
                input = scanner.nextDouble();
                if (input < 0) {
                    System.out.println("La valeur ne peut pas être négative. Veuillez réessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre valide.");
                scanner.next();  // Clear the invalid input
            }
        }
        return input;
    }

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
        MainDoeuvreRepository mainDoeuvreRepository = new MainDoeuvreRepository();
        MainDoeuvreService mainDoeuvreService = new MainDoeuvreService(mainDoeuvreRepository);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez l'ID du projet : ");
        int projectId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character after nextInt()

        mainDoeuvreService.addLabor(projectId);
        mainDoeuvreService.getAllLaborForProject(projectId);
        mainDoeuvreService.getLaborById();
    }
}
