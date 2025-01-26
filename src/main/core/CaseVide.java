package main.core;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;

public class CaseVide extends Case {
    /**
     * Permet d'afficher une case vide dans le panel associe avec simplement un fond
     * @param panel le panel a modifier
     */
    @Override
    public void afficher(JPanel panel) {
        JLabel lCase = (JLabel) panel.getComponent(0);

        panel.setBackground(new Color(205, 192, 180));
        lCase.setText("");
    }
}
