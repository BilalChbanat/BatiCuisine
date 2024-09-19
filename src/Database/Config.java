package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Config {

    public static void main(String[] args) {
        try {
            DatabaseConnection dbInstance = DatabaseConnection.getInstance();
            Connection connection = dbInstance.getConnection();

            if (connection != null) {
                System.out.println("Connected to the BatiCuisine database using Singleton!");

                Statement statement = connection.createStatement();
                String createTableSQL = "CREATE TABLE IF NOT EXISTS test_table (id SERIAL PRIMARY KEY, name VARCHAR(255))";
                statement.executeUpdate(createTableSQL);
                System.out.println("Test table created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
