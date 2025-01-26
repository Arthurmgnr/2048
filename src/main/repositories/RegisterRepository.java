package main.repositories;

import main.model.User;
import main.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class RegisterRepository {
    // Permet de recuperer la connexion vers la DB
    private final Connection connection = DBConnection.getConnection();

    // Verifie si l'identifiant existe deja
    public boolean usernameExists(String username) {
        String query = """
                        SELECT COUNT(*)\s
                        FROM users\s
                        WHERE username = ?
                        """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Creation du compte du joueur
    public boolean saveUser(User user) {
        String query = """
                        INSERT INTO users (username, creationdate, avatar, lang)
                        VALUES (?, ?, ?, ?)
                        """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setTimestamp(2, Timestamp.from(user.getDateTime().toInstant()));
            stmt.setString(3, user.getAvatar());
            stmt.setString(4, user.getLang());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
