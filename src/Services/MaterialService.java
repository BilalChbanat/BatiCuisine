package Services;

import Interfaces.MaterialInterface;
import Models.Material;
import Repositories.MaterialRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MaterialService {
    private final MaterialInterface materialInterface;
    private final Scanner scanner;

    public MaterialService() {
        this.materialInterface = new MaterialRepository();
        this.scanner = new Scanner(System.in);
    }

    public void addMaterials() {
        boolean addingMaterials = true;

        while (addingMaterials) {
            try {
                System.out.print("Entrez le nom du matériau : ");
                String name = scanner.nextLine();
                System.out.println("Material name entered: " + name); // Debug line


                System.out.print("Entrez la quantité de ce matériau (en m²) : ");
                double quantity = getValidDoubleInput();

                System.out.print("Entrez le coût unitaire de ce matériau (€/m²) : ");
                double unitCost = getValidDoubleInput();

                System.out.print("Entrez le coût de transport de ce matériau (€) : ");
                double transportCost = getValidDoubleInput();

                System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
                double qualityCoefficient = getValidDoubleInput();

                System.out.print("Entrez le type de composant (e.g., Material, Labor) : ");
                String typeComposant = scanner.nextLine();

                System.out.print("Entrez le taux de TVA (ex: 20 pour 20%) : ");
                double tauxTVA = getValidDoubleInput();

                Material material = new Material(0, name, typeComposant, tauxTVA, quantity, unitCost, transportCost, qualityCoefficient);
                materialInterface.addMaterial(material);
                System.out.println("Matériau ajouté avec succès !");

                System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
                String response = scanner.nextLine();
                addingMaterials = response.equalsIgnoreCase("y");

            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer une valeur valide.");
                scanner.nextLine();
            }
        }
    }

    private double getValidDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
    }

    public static void main(String[] args) {
        MaterialService materialService = new MaterialService();
        materialService.addMaterials();
        materialService.scanner.close();
    }
}
