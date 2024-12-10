package main;

import main.entities.User;
import main.gui.*;
import main.services.ProfileService;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws URISyntaxException {

        System.out.println(DBConnection.getConnection());
        DBConnection.openConnection();
        System.out.println();


//        String query = "SELECT * FROM Users";
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
//
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                System.out.println("Résultat : " + rs.getString("Username") + " " + rs.getString("Langue"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


//        String url = "jdbc:postgresql://localhost:5432/2048";
//        String user = "postgres";
//        String password = "postgres";
//        try (Connection conn = DriverManager.getConnection(url, user, password)) {
//            if (conn != null) {
//                System.out.println("Connexion établie !");
//
//                // Exemple de récupération de données
//                String selectSQL = "SELECT * FROM Users";
//                try (PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
//                     ResultSet resultSet = preparedStatement.executeQuery()) {
//                    System.out.printf("%-15s%-15s%n", "Username", "Langue");
//                    System.out.println("-".repeat(30));
//                    while (resultSet.next()) {
//                        System.out.printf("%-15s%-15s%n", resultSet.getString("Username"), resultSet.getString("Langue"));
//                    }
//                }
//            } else {
//                System.out.println("Échec de la connexion.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Erreur de connexion : " + e.getMessage());
//            e.printStackTrace();
//        }

        SwingUtilities.invokeLater(() -> {
//            Home frame = new Home();
//            Register frame = new Register();
//            Login frame = new Login();
//            ProfileGame frame = new ProfileGame("TestUser", false, true);
            Profile frame = new Profile("TestUser");
//            EditProfile frame = new EditProfile("TestUser");
//            Game frame = new Game("TestUser");
            frame.setVisible(true);
        });

        Runtime.getRuntime().addShutdownHook(new Thread(DBConnection::closeConnection));
    }
}