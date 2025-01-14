package main.repositories;

import main.model.User;
import main.DBConnection;

import java.sql.*;

public class RegisterRepository {
    private final Connection connection = DBConnection.getConnection();

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
