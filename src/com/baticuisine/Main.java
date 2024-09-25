package com.baticuisine;

import com.baticuisine.Interfaces.ClientInterface;
import com.baticuisine.Interfaces.DevisInterface;
import com.baticuisine.Interfaces.MaterialInterface;
import com.baticuisine.Interfaces.ProjectInterface;
import com.baticuisine.Repositories.ClientRepository;
import com.baticuisine.Repositories.DevisRepository;
import com.baticuisine.Repositories.MaterialRepository;
import com.baticuisine.Repositories.ProjectRepository;
import com.baticuisine.Services.DevisService;
import com.baticuisine.Services.MaterialService;
import com.baticuisine.Services.MainDoeuvreService;
import com.baticuisine.Services.ProjectService;
import com.baticuisine.Database.DatabaseConnection;

import java.sql.Connection;

public class Main {
    private static Connection connection = DatabaseConnection.getConnection();

    private static ClientInterface clientRepository = new ClientRepository();
    private static ProjectInterface projectRepository = new ProjectRepository();
    private static MaterialInterface materialRepository = new MaterialRepository();
    private static DevisInterface devisRepository = new DevisRepository(connection);

    private static DevisService devisService = new DevisService(devisRepository);
    private static MaterialService materialService = new MaterialService();
    private static MainDoeuvreService mainDoeuvreService = new MainDoeuvreService();

    private static ProjectService projectService = new ProjectService(
            projectRepository,
            clientRepository,
            materialService,
            mainDoeuvreService,
            devisService
    );

    public static void main(String[] args) {
        projectService.displayMenu();

        Runtime.getRuntime().addShutdownHook(new Thread(DatabaseConnection::closeConnection));
    }
}
