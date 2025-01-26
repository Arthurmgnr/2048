package main.utils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;

public class JLabelPersonalized extends JLabel {
    /**
     * Permet de definir un label
     * @param text le texte du label
     * @param fontSize la taille de police du texte
     * @param centerAlignment permet de savoir si le label doit etre centree horizontalement
     */
    public JLabelPersonalized(String text, int fontSize, boolean centerAlignment) {
        super(text);

        this.setFont(new Font("Arial", Font.BOLD, fontSize));

        if (centerAlignment)  this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
