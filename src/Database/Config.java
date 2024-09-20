package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Config {

    public static void main(String[] args) {
        DatabaseConnection connectionManager = DatabaseConnection.getInstance();

        Connection connection = connectionManager.getConnection();

        if (connection != null) {
            System.out.println("Successfully connected to the database!");

            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT CURRENT_TIMESTAMP");

                if (resultSet.next()) {
                    System.out.println("Current Timestamp: " + resultSet.getString(1));
                }
            } catch (SQLException e) {
                System.out.println("Error executing test query: " + e.getMessage());
            } finally {
                connectionManager.closeConnection();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}



