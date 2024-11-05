package panel;

import gui.Home;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class HomePanel extends JPanel {
    // Réference sur la fenêtre principale
    private Home home;
    public HomePanel(Home home) {
        this.home = home;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        home.dessiner(g);
    }
}