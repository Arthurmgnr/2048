package main.gui.panels;

import main.gui.Home;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    // Réference sur la fenêtre principale
    private final Home home;
    public HomePanel(Home home) {
        this.home = home;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        home.dessiner(g);
    }
}