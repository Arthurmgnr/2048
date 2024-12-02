package main.repositories;

import main.DBConnection;
import main.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRepository {
    private final Connection connection = DBConnection.getConnection();

    public String getUserAvatar(String username) {
        String query = "SELECT avatar \n" +
                        "FROM users \n" +
                        "WHERE username = ?";
        String userAvatar = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {;
                userAvatar = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAvatar;
    }

    public int getUserBestScore(String username) {
        String query = "SELECT MAX(score) AS bestScore \n" +
                        "FROM games \n" +
                        "WHERE username = ?";
        int userBestScore = 0;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {;
                userBestScore = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBestScore;
    }
}
