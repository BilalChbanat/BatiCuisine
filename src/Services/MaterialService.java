package Services;

import Interfaces.MaterialInterface;
import Models.Material;
import Repositories.MaterialRepository;

import java.util.Scanner;

public class MaterialService {
    private final MaterialInterface materialRepository;
    private final Scanner scanner;

    public MaterialService(MaterialInterface materialRepository) {
        this.materialRepository = materialRepository;
        this.scanner = new Scanner(System.in);  // Initialize scanner once
    }

    public void addMaterials() {
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

            // Create Material with all required parameters
            Material material = new Material(0, name, "Material", 20.0, // Replace with actual type and TVA
                    quantity, unitCost, transportCost, qualityCoefficient);
            materialRepository.addMaterial(material);  // Save to DB
            System.out.println("Matériau ajouté avec succès !");

            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("y")) {
                addingMaterials = false;
            }
        }
    }

    public static void main(String[] args) {
        MaterialInterface materialInterface = new MaterialRepository();
        MaterialService materialService = new MaterialService(materialInterface);  // Use interface
        materialService.addMaterials();  // Call the addMaterials method to begin the process
    }
}
