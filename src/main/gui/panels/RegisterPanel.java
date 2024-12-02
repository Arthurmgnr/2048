package main.gui.panels;

import main.gui.Register;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    // Réference sur la fenêtre principale
    private final Register register;
    public RegisterPanel(Register register) {
        this.register = register;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        register.dessiner(g);
    }
}