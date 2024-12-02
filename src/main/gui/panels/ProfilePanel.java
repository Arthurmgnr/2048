package main.gui.panels;

import main.gui.Profile;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private final Profile profile;
    public ProfilePanel(Profile profile) {
        this.profile = profile;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        profile.dessiner(g);
    }
}