package main.repositories;

import main.DBConnection;
import main.model.User;
import main.model.UserGamesDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRepository {
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

    // Recupere les informations du joueur dans la table Games
    public UserGamesDetails getUserGamesDetails(String username) {
        String query = """
                        SELECT MAX(score) AS bestScore,\s
                            COUNT(*) AS nbParties,\s
                            AVG(score) AS scoreMoyen,\s
                            AVG(moves)AS nbCoupsMoyen,\s
                            COUNT(*) FILTER (WHERE win = TRUE) AS nbPartiesGagnees
                        FROM games\s
                        WHERE username = ?
                        """;
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
