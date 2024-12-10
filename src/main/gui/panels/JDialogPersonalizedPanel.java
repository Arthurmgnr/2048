package main.gui.panels;

import main.gui.JDialogPersonalized;

import javax.swing.*;
import java.awt.*;

public class JDialogPersonalizedPanel extends JPanel {
    // Réference sur la fenêtre principale
    private final JDialogPersonalized jDialogPersonalized;
    public JDialogPersonalizedPanel(JDialogPersonalized jDialogPersonalized) {
        this.jDialogPersonalized = jDialogPersonalized;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        jDialogPersonalized.dessiner(g);
    }
}