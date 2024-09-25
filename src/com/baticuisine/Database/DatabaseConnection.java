package com.baticuisine.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;
    private static final String URL = "jdbc:postgresql://localhost:5432/baticuisine";

    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    private DatabaseConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DatabaseConnection.class) {
                if (connection == null) {
                    try {
                        // Load the PostgreSQL JDBC driver
                        Class.forName("org.postgresql.Driver");
                        // Establish the connection
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        System.out.println("Database connection established.");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
