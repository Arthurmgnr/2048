package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static void openConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:postgresql://localhost:5432/2048";
                String user = "postgres";
                String password = "postgres";
//                String user = "polytech";
//                String password = "polytech";
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connexion établie avec la base de données.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
