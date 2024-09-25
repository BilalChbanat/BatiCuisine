package Services;

import Enums.ProjectStatus;
import Interfaces.ClientInterface;
import Interfaces.MaterialInterface;
import Interfaces.ProjectInterface;
import Models.Client;
import Models.Project;
import Repositories.ClientRepository;
import Repositories.MaterialRepository;
import Repositories.ProjectRepository;
import Views.ClientMenu;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProjectService {
    private final ProjectInterface projectInterface;
    private final ClientInterface clientRepository;
    private final Scanner scanner;
    private final DevisService devisService;

    ClientMenu clientmenu = new ClientMenu();
    MaterialService materialService = new MaterialService();
    MainDoeuvreService mainDoeuvreService = new MainDoeuvreService();



    public ProjectService(ProjectInterface projectRepository, ClientInterface clientRepository,
                          MaterialService materialService, MainDoeuvreService mainDoeuvreService,
                          DevisService devisService) {
        this.projectInterface = projectRepository;
        this.clientRepository = clientRepository;
        this.materialService = materialService;
        this.mainDoeuvreService = mainDoeuvreService;
        this.devisService = devisService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createProject();
                    break;
                case 2:
                    viewAllProjects();
                    break;
                case 3:
//                    calculateProjectCost();
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void createProject() {
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");

        int clientChoice = scanner.nextInt();
        scanner.nextLine();

        Client client = null;

        if (clientChoice == 1) {
            client = searchExistingClient();
        } else if (clientChoice == 2) {
            client = addNewClient();
        }

        if (client != null) {
            System.out.println("--- Création d'un Nouveau Projet ---");
            System.out.print("Entrez le nom du projet : ");
            String projectName = scanner.nextLine();

            System.out.print("Entrez la surface de la cuisine (en m²) : ");
            double surfaceArea = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Entrez l'état du projet (IN_PROGRESS, COMPLETED, CANCELLED) : ");
            String statusInput = scanner.nextLine();
            ProjectStatus projectStatus = ProjectStatus.valueOf(statusInput.toUpperCase());

            Project project = new Project(0, projectName, surfaceArea, 0.0, projectStatus, client.getId());
            projectInterface.createProject(project);

            materialService.addMaterials(project);

            mainDoeuvreService.addLabor(project.getId());



            System.out.println("Projet créé avec succès pour le client : " + client.getName());
        } else {
            System.out.println("Aucun client sélectionné. Projet non créé.");
        }
    }

    private Client searchExistingClient() {

        System.out.print("Entrez le nom du client : ");
        String clientName = scanner.nextLine();
        Optional<Client> clientOptional = clientRepository.getClientByName(clientName);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            System.out.println("Client trouvé !");
            System.out.println("Nom : " + client.getName());
            System.out.println("Adresse : " + client.getAddress());
            System.out.println("Numéro de téléphone : " + client.getPhone());
            System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("y")) {
                return client;
            }
        } else {
            System.out.println("Client non trouvé.");
        }

        return null;
    }


    private Client addNewClient() {
        System.out.print("Entrez le nom du client : ");
        String name = scanner.nextLine();
        System.out.print("Entrez l'adresse : ");
        String address = scanner.nextLine();
        System.out.print("Entrez le numéro de téléphone : ");
        String phone = scanner.nextLine();
        System.out.print("Le client est-il un professionnel ? (true/false) : ");
        boolean isProfessional = scanner.nextBoolean();
        scanner.nextLine();

        Client newClient = new Client(0, name, address, phone, isProfessional);
        clientRepository.addClient(newClient);
        System.out.println("Client ajouté avec succès.");
        return newClient;
    }

    private void viewAllProjects() {
        List<Project> projects = projectInterface.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("Aucun projet trouvé.");
        } else {
            System.out.println("Projets existants :");
            for (Project project : projects) {
                System.out.println(project);
            }
        }
    }

//    public static void main(String[] args) {
//        ClientInterface clientRepository = new ClientRepository();
//        ProjectInterface projectRepository = new ProjectRepository();
//        ProjectService projectService = new ProjectService(projectRepository, clientRepository);
//        projectService.displayMenu();
//    }

}
