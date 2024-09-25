package com.baticuisine.Views;

import com.baticuisine.Services.ClientService;
import com.baticuisine.Interfaces.ClientInterface;
import com.baticuisine.Repositories.ClientRepository;
import com.baticuisine.Services.ProjectService;

import java.util.Scanner;

public class ClientMenu {
    private final ClientService clientService;
    private final Scanner scanner;
    ProjectService projectService;

    public ClientMenu() {
        ClientInterface clientRepository = new ClientRepository(); // Adjust if needed
        this.clientService = new ClientService(clientRepository);
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("========= Customer Management ==========");
            System.out.println("1: Add a new customer");
            System.out.println("2: View all customers");
            System.out.println("3: Update a customer");
            System.out.println("4: Return to main menu"); // Added option
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    clientService.addClient(); // Call method in ClientService
                    break;
                case 2:
                    clientService.viewAllCustomers(); // Call method in ClientService
                    break;
                case 3:
                    clientService.updateCustomer(); // Call method in ClientService
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    projectService.displayMenu();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ClientMenu clientMenu = new ClientMenu();
        clientMenu.showMenu();
    }
}
