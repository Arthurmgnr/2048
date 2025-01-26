package main.utils;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonProfileGame extends JLabel {

    /**
     * Permet de definir un bouton (uniquement disponible dans les pages Profile et ProfileGame)
     * @param text le texte du bouton
     * @param cursor permet de savoir si on doit ajouter l'info-bulle et l'animation au bouton
     * @param toolTipText l'info-bulle qui indique a l'utilisateur ce que fait le bouton
     */
    public JButtonProfileGame(String text, Boolean cursor, String toolTipText) {
        super(text);

        setFont(new Font("Arial", Font.BOLD, 40));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);

        setForeground(cursor ? Color.BLACK : Utils.blue);

        if (cursor) {
            setToolTipText(toolTipText);

            // Permet de creer une animation pour le curseur et la couleur du bouton
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(Color.LIGHT_GRAY);
                    setOpaque(true);
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(Color.WHITE);
                    setOpaque(false);
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }
    }

    /**
     * Permet de definir les dimensions (largeur et hauteur) du bouton
     * @param dimension un objet qui contient la hauteur et la largeur que l'on souhaite appliquer au bouton
     */
    public void setBothSize(Dimension dimension) {
        setPreferredSize(dimension);
        setMaximumSize(dimension);
    }
}
