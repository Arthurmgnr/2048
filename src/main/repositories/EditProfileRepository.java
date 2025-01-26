package main.repositories;

import main.DBConnection;
import main.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditProfileRepository {
    // Permet de recuperer la connexion vers la DB
    private final Connection connection = DBConnection.getConnection();

    // Recupere les informations du joueur dans la table Users
    public User getUserDetails(String username) {
        String query = """
                        SELECT username, avatar, creationdate, lang\s
                        FROM users\s
                        WHERE username = ?
                        """;
        User user = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString(1));
                user.setAvatar(rs.getString(2));
                user.setDateTime(rs.getTimestamp(3));
                user.setLang(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Permet de mettre a jour les informations du joueur
    public boolean updateUser(User user) {
        String query = """
                        UPDATE users\s
                        SET lang = ?, avatar = ?\s
                        WHERE username = ?
                        """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getLang());
            stmt.setString(2, user.getAvatar());
            stmt.setString(3, user.getUsername());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
