package main.repositories;

import main.DBConnection;
import main.entities.User;
import main.entities.UserGamesDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRepository {
    private final Connection connection = DBConnection.getConnection();

    public User getUserDetails(String username) {
        String query = "SELECT username, avatar, creationdate, lang \n" +
                        "FROM users \n" +
                        "WHERE username = ?";
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

    public UserGamesDetails getUserGamesDetails(String username) {
        String query = "SELECT MAX(score) AS bestScore, \n" +
                                "COUNT(*) AS nbParties, \n" +
                                "AVG(score) AS scoreMoyen, \n" +
                                "AVG(moves)AS nbCoupsMoyen, \n" +
                                "COUNT(*) FILTER (WHERE win = TRUE) AS nbPartiesGagnees\n" +
                        "FROM games \n" +
                        "WHERE username = ?";
        UserGamesDetails userGamesDetails = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userGamesDetails = new UserGamesDetails();
                userGamesDetails.setBestScore(rs.getInt(1));
                userGamesDetails.setNbParties(rs.getInt(2));
                userGamesDetails.setScoreMoyen(rs.getInt(3));
                userGamesDetails.setNbCoupsMoyen(rs.getInt(4));
                userGamesDetails.setNbPartiesGagnees(rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userGamesDetails;
    }
}
