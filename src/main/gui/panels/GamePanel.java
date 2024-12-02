package main.gui.panels;

import main.gui.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    // Réference sur la fenêtre principale
    private final Game game;
    public GamePanel(Game game) {
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.dessiner(g);
    }
}