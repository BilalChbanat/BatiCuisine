package Services;

import Interfaces.MaterialInterface;
import Models.Material;
import Models.Project;
import Repositories.MaterialRepository;

import java.util.*;

public class MaterialService {
    private final MaterialInterface materialInterface;
    private final Scanner scanner;

    public MaterialService() {
        this.materialInterface = new MaterialRepository();
        this.scanner = new Scanner(System.in);
    }

    public void addMaterials(Project project) {
        boolean addingMaterials = true;
        List<Material> materialsAdded = new ArrayList<>();

        while (addingMaterials) {
            try {
                System.out.print("Entrez le nom du matériau : ");
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("Le nom du matériau ne peut pas être vide. Veuillez réessayer.");
                    continue;
                }

                System.out.print("Entrez la quantité de ce matériau (en m²) : ");
                double quantity = getValidDoubleInput();

                System.out.print("Entrez le coût unitaire de ce matériau (€/m²) : ");
                double unitCost = getValidDoubleInput();

                System.out.print("Entrez le coût de transport de ce matériau (€) : ");
                double transportCost = getValidDoubleInput();

                System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
                double qualityCoefficient = getValidDoubleInput();

                System.out.print("Entrez le taux de TVA (ex: 20 pour 20%) : ");
                double tauxTVA = getValidDoubleInput();

                String typeComposant = "Material";
                Material material = new Material(0, name, typeComposant, tauxTVA, quantity, unitCost, transportCost, qualityCoefficient, project.getId());
                materialInterface.addMaterial(material);
                materialsAdded.add(material);
                System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
                String response = scanner.nextLine().trim();
                addingMaterials = response.equalsIgnoreCase("y");

            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer une valeur valide.");
                scanner.nextLine();
            }
        }

        System.out.println("Voulez-vous appliquer la taxe pour ce projet ? (1 pour oui | 2 pour non) : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            List<Material> materialsWithTax = applyTaxToMaterials(materialsAdded);
            System.out.println("Matériaux avec taxe appliquée :");
            for (Material materialWithTax : materialsWithTax) {
                System.out.printf("Nom : %s, Coût total avec taxe : %.2f€%n", materialWithTax.getName(), materialWithTax.getUnitCost());
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

    public List<Material> applyTaxToMaterials(List<Material> materials) {
        List<Material> materialsWithTax = new ArrayList<>();

        for (Material material : materials) {
            double taxAmount = material.getUnitCost() * material.getQuantity() * material.getQualityCoefficient();
            double totalCostWithTax = ((taxAmount * material.getTauxTVA()) / 100);

            Material materialWithTax = new Material(
                    material.getId(),
                    material.getName(),
                    material.getTypeComposant(),
                    material.getTauxTVA(),
                    material.getQuantity(),
                    totalCostWithTax,
                    material.getTransportCost(),
                    material.getQualityCoefficient(),
                    material.getProjectId()
            );

            materialsWithTax.add(materialWithTax);
        }

        return materialsWithTax;
    }
}
