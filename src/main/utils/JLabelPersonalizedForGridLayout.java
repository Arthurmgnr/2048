package main.utils;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class JLabelPersonalizedForGridLayout extends JLabel {
    /**
     * Permet de definir un label (uniquement dans un GridLayout)
     * @param text le texte du label
     * @param font la police que l'on souhaite appliquer au texte
     */
    public JLabelPersonalizedForGridLayout(String text, int font) {
        super(text);

        setFont(new Font("Arial", Font.BOLD, font));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }
}
