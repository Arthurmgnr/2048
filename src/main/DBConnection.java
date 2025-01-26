package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Cette classe permet d'ouvrir, de fermer et de transmettre la connexion a la DB
public class DBConnection {
    // Cette variable permet d'interagir avec la DB
    private static Connection connection;

    // Cette methode permet d'etablir la connexion
    public static void openConnection() {
        if (connection == null) {
            try {
                // Il faut modifier '2048' par le nom de votre DB sur PostgreSQL
                String url = "jdbc:postgresql://localhost:5432/2048";
                // Il faut modifier la variable user par l'identifiant de connexion a votre DB
                String user = "postgres";
                // Il faut modifier la variable password par votre mot de passe de connexion a votre DB
                String password = "postgres";
                connection = DriverManager.getConnection(url, user, password);
                // Affichage dans la console pour informer le joueur
                System.out.println("Connexion établie avec la base de données.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Permet de transmettre la connexion aux Repository pour effectuer les requetes
    public static Connection getConnection() {
        return connection;
    }

    // Permet de fermer la connexion a la DB
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
