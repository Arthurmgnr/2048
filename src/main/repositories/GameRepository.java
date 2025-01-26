package main.repositories;

import main.DBConnection;
import main.model.Games;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRepository {
    // Permet de recuperer la connexion vers la DB
    private final Connection connection = DBConnection.getConnection();

    // Recupere le chemin d'acces a l'avatar du joueur
    public String getUserAvatar(String username) {
        String query = """
                        SELECT avatar\s
                        FROM users\s
                        WHERE username = ?
                        """;
        String userAvatar = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userAvatar = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAvatar;
    }

    // Recupere le meilleur score du joueur
    public int getUserBestScore(String username) {
        String query = """
                        SELECT MAX(score) AS bestScore\s
                        FROM games\s
                        WHERE username = ?
                        """;
        int userBestScore = 0;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userBestScore = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBestScore;
    }

    // Enregistre la partie dans la DB
    public boolean registerGames(Games games) {
        String query = """
                        INSERT INTO games (username, score, moves, bestTile, win)
                        VALUES (?, ?, ?, ?, ?)
                        """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, games.getUsername());
            stmt.setInt(2, games.getScore());
            stmt.setInt(3, games.getMoves());
            stmt.setInt(4, games.getBestTile());
            stmt.setBoolean(5, games.getWin());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
