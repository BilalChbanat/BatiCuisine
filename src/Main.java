import Interfaces.ClientInterface;
import Interfaces.DevisInterface;
import Interfaces.MaterialInterface;
import Interfaces.ProjectInterface;
import Repositories.ClientRepository;
import Repositories.DevisRepository;
import Repositories.MaterialRepository;
import Repositories.ProjectRepository;
import Services.DevisService;
import Services.MaterialService;
import Services.MainDoeuvreService;
import Services.ProjectService;
import Database.DatabaseConnection;

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
