package com.baticuisine.Database;


import java.sql.Connection;
import java.sql.SQLException;

public class Config {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Get the database connection
            connection = DatabaseConnection.getConnection();

            // Test if the connection is valid
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connection to the database is successful!");
            } else {
                System.out.println("Failed to establish a connection.");
            }
        } catch (SQLException e) {
            System.out.println("Error while testing the connection: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the connection if it was established
            if (connection != null) {
                try {
                    DatabaseConnection.closeConnection();
                } catch (Exception e) {
                    System.out.println("Error closing the connection: " + e.getMessage());
                }
            }
        }
    }
}
