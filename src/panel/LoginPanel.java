package panel;

import gui.Login;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    // Réference sur la fenêtre principale
    private final Login login;
    public LoginPanel(Login login) {
        this.login = login;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        login.dessiner(g);
    }
}