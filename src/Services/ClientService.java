package Services;

import Interfaces.ClientInterface;
import Models.Client;
import Repositories.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientService {

    private final ClientInterface clientRepository;
    private final Scanner scanner;

    public ClientService(ClientInterface clientRepository) {
        this.clientRepository = clientRepository;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n═════════════════════ Customer Management ═════════════════════");
            System.out.println("1: Add a new customer");
            System.out.println("2: View all customers");
            System.out.println("3: Update a customer");
            System.out.println("4: Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addClient();
                    break;
                case 2:
                    viewAllCustomers();
                    break;
                case 3:
                    updateCustomer();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addClient() {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Is the client a professional? (true/false): ");
        boolean isProfessional = scanner.nextBoolean();

        Client client = new Client(0, name, address, phone, isProfessional);
        clientRepository.addClient(client);
        System.out.println("Client added successfully.");
    }

    private void viewAllCustomers() {
        List<Client> clients = clientRepository.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("List of customers:");
            for (Client client : clients) {
                System.out.println(client.toString());
            }
        }
    }

    private void updateCustomer() {
        System.out.print("Enter the name of the customer to update: ");
        String name = scanner.nextLine();

        Optional<Client> optionalClient = getClientByName(name);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            System.out.print("Enter new name (leave blank to keep current): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                client.setName(newName);
            }
            System.out.print("Enter new address (leave blank to keep current): ");
            String newAddress = scanner.nextLine();
            if (!newAddress.isEmpty()) {
                client.setAddress(newAddress);
            }
            System.out.print("Enter new phone (leave blank to keep current): ");
            String newPhone = scanner.nextLine();
            if (!newPhone.isEmpty()) {
                client.setPhone(newPhone);
            }
            System.out.print("Is the client a professional? (true/false, leave blank to keep current): ");
            String isProfessionalInput = scanner.nextLine();
            if (!isProfessionalInput.isEmpty()) {
                client.setProfessional(Boolean.parseBoolean(isProfessionalInput));
            }

            clientRepository.updateClient(client);
            System.out.println("Client updated successfully.");
        } else {
            System.out.println("Client not found.");
        }
    }



    private Optional<Client> getClientByName(String name) {
        return clientRepository.getClientByName(name);
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public static void main(String[] args) {
        ClientInterface clientRepository = new ClientRepository();
        ClientService customerService = new ClientService(clientRepository);

        customerService.displayMenu();
    }

}
