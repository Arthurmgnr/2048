package main.gui.panels;

import main.gui.ProfileGame;

import javax.swing.*;
import java.awt.*;

public class ProfileGamePanel extends JPanel {
    private final ProfileGame profileGame;
    public ProfileGamePanel(ProfileGame profileGame) {
        this.profileGame = profileGame;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        profileGame.dessiner(g);
    }
}