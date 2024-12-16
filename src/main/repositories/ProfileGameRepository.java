package main.repositories;

import main.DBConnection;
import main.entities.User;
import main.entities.UserGamesDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileGameRepository {
    private final Connection connection = DBConnection.getConnection();

    public UserGamesDetails getBestScore(String username) {
        String query = "SELECT MAX(score) \n" +
                        "FROM games \n" +
                        "WHERE username = ?";
        UserGamesDetails user = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new UserGamesDetails();
                user.setBestScore(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserDetails(String username) {
        String query = "SELECT avatar, lang \n" +
                        "FROM users \n" +
                        "WHERE username = ?";
        User user = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setAvatar(rs.getString(1));
                user.setLang(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
