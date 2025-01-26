package main.repositories;

import main.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    // Permet de recuperer la connexion vers la DB
    private final Connection connection = DBConnection.getConnection();

    // Verifie si l'identifiant existe deja
    public boolean usernameDoesntExists(String username) {
        String query = "SELECT COUNT(*) \n" +
                        "FROM users \n" +
                        "WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
