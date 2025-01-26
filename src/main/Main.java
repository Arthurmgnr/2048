package main;

import main.gui.Home;

import javax.swing.SwingUtilities;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException {
        // Ouverture de la connexion a la DB
        DBConnection.openConnection();

        // Lancement de l'application avec la fenetre Home
        SwingUtilities.invokeLater(() -> {
            Home frame = new Home();
            frame.setVisible(true);
        });

        // Fermeture de la connexion a la DB
        Runtime.getRuntime().addShutdownHook(new Thread(DBConnection::closeConnection));
    }
}