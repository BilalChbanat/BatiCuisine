package com.baticuisine.Services;

import com.baticuisine.Interfaces.MainDoeuvreInterface;
import com.baticuisine.Models.MainDoeuvre;
import com.baticuisine.Repositories.MainDoeuvreRepository;

import java.util.List;
import java.util.Scanner;

public class MainDoeuvreService {
    private MainDoeuvreInterface mainDoeuvreInterface;
    private Scanner scanner;

    public MainDoeuvreService() {
        this.mainDoeuvreInterface = new MainDoeuvreRepository();
        this.scanner = new Scanner(System.in);
    }

    public void addLabor(int projectId) {
        String addMore = "y";

        while (addMore.equalsIgnoreCase("y")) {
            System.out.println("--- Ajout de la main-d'œuvre ---");

            System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
            String name = scanner.nextLine();

            String typeComposant = "Main-d'œuvre";

            System.out.print("Entrez le taux de TVA (%) : ");
            double tauxTVA = scanner.nextDouble();

            System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            double tauxHoraire = scanner.nextDouble();

            System.out.print("Entrez le nombre d'heures travaillées : ");
            double heuresTravail = scanner.nextDouble();

            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            double productiviteOuvrier = scanner.nextDouble();
            scanner.nextLine(); // To consume the newline character

            MainDoeuvre mainDoeuvre = new MainDoeuvre(0, name, typeComposant, tauxTVA, tauxHoraire, heuresTravail, productiviteOuvrier);

            mainDoeuvreInterface.add(mainDoeuvre, projectId);
            System.out.println("Main-d'œuvre ajoutée avec succès !");

            // Ask if they want to add more labor
            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            addMore = scanner.nextLine();
        }

        System.out.println("Ajout de main-d'œuvre terminé.");
    }

    public void getAllLaborForProject(int projectId) {
        List<MainDoeuvre> laborList = mainDoeuvreInterface.findByProjectId(projectId);

        if (!laborList.isEmpty()) {
            System.out.println("--- Liste des main-d'œuvre pour le projet ID: " + projectId + " ---");
            laborList.forEach(System.out::println);
        } else {
            System.out.println("Aucune main-d'œuvre trouvée pour ce projet.");
        }
    }
}
