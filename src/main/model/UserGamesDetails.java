package main.model;

/**
 * Classe qui permet de calculer les informations relatives a l'ensemble des parties d'un joueur
 */
public class UserGamesDetails {
    private String username;
    private int bestScore;
    private int nbParties;
    private int scoreMoyen;
    private int nbCoupsMoyen;
    private int nbPartiesGagnees;

    public UserGamesDetails() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public int getNbParties() {
        return nbParties;
    }

    public void setNbParties(int nbParties) {
        this.nbParties = nbParties;
    }

    public int getScoreMoyen() {
        return scoreMoyen;
    }

    public void setScoreMoyen(int scoreMoyen) {
        this.scoreMoyen = scoreMoyen;
    }

    public int getNbCoupsMoyen() {
        return nbCoupsMoyen;
    }

    public void setNbCoupsMoyen(int nbCoupsMoyen) {
        this.nbCoupsMoyen = nbCoupsMoyen;
    }

    public int getNbPartiesGagnees() {
        return nbPartiesGagnees;
    }

    public void setNbPartiesGagnees(int nbPartiesGagnees) {
        this.nbPartiesGagnees = nbPartiesGagnees;
    }
}
