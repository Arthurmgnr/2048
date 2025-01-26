package main.utils;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;

public class JTextFieldPersonalized extends JTextField {
    /**
     * Permet de definir une zone de texte pour recuperer une saisie de l'utilisateur
     * @param lLabel permet de recuperer la taille du label qui indique ce que l'utilisateur doit saisir ; cela permet
     *               d'avoir une hauteur de zone de texte identique a celle du label correspondant
     */
    public JTextFieldPersonalized(JLabelPersonalized lLabel) {
        setFont(new Font("Arial", Font.PLAIN, 18));
        Dimension dimensionUsername = new Dimension(this.getFontMetrics(this.getFont()).charWidth('W') * 20, lLabel.getPreferredSize().height);
        setMaximumSize(dimensionUsername);
        setPreferredSize(dimensionUsername);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
