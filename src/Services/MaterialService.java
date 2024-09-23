package Services;

import Interfaces.MaterialInterface;
import Models.Material;
import Repositories.MaterialRepository;

import java.util.Scanner;

public class MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialService() {
        this.materialRepository = new MaterialRepository();
    }

    public void addMaterials() {
        Scanner scanner = new Scanner(System.in);
        boolean addingMaterials = true;

        while (addingMaterials) {
            System.out.print("Entrez le nom du matériau : ");
            String name = scanner.nextLine();

            System.out.print("Entrez la quantité de ce matériau (en m²) : ");
            double quantity = Double.parseDouble(scanner.nextLine());

            System.out.print("Entrez le coût unitaire de ce matériau (€/m²) : ");
            double unitCost = Double.parseDouble(scanner.nextLine());

            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            double transportCost = Double.parseDouble(scanner.nextLine());

            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            double qualityCoefficient = Double.parseDouble(scanner.nextLine());

            Material material = new Material(name, quantity, unitCost, transportCost, qualityCoefficient);
            materialRepository.addMaterial(material); // Save to DB
            System.out.println("Matériau ajouté avec succès !");

            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("y")) {
                addingMaterials = false;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        MaterialInterface materialInterface = new MaterialRepository();
        MaterialService materialService = new MaterialService();  // Corrected here
        materialService.addMaterials();  // Call the addMaterials method to begin the process
    }
}
