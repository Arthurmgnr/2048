package main.entities;

public class Games {
    private String username;
    private final int score;
    private final int moves;
    private final int bestTile;
    private final boolean win;

    public Games(String username, int score, int moves, int bestTile, boolean win) {
        this.username = username;
        this.score = score;
        this.moves = moves;
        this.bestTile = bestTile;
        this.win = win;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public int getMoves() {
        return moves;
    }

    public int getBestTile() {
        return bestTile;
    }

    public boolean getWin() {
        return win;
    }
}
