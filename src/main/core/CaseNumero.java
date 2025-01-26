package main.core;

import main.utils.Utils;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class CaseNumero extends Case {
    // Represente la valeur de la case
    private final int valeur;

    public CaseNumero(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Permet d'afficher la valeur de la case dans le panel associe ainsi que la couleur du texte et du fond de la case
     * @param panel le panel que l'on vient modifier
     */
    @Override
    public void afficher(JPanel panel) {
        JLabel lCase = (JLabel) panel.getComponent(0);

        panel.setBackground(Utils.getColorCase(valeur));

        lCase.setText(String.valueOf(valeur));
        lCase.setFont(new Font("Arial", Font.BOLD, Utils.getSizeText(valeur)));
        lCase.setForeground(Utils.getColorText(valeur));
    }

    public int getValeur() {
        return valeur;
    }
}
