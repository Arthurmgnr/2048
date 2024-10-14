package gui;

import utils.Utils;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
class MonPanel extends JPanel {
    // Réference sur la fenêtre principale
    private Game game;
    public MonPanel(Game game) {
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.dessiner(g);
    }
}