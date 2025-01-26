package main.utils;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonPersonalized extends JButton {

    /**
     * Permet de definir un bouton
     * @param text le texte du bouton
     * @param toolTipText l'info-bulle qui indique a l'utilisateur ce que fait le bouton
     */
    public JButtonPersonalized(String text, String toolTipText) {
        super(text);

        setFont(new Font("Arial", Font.BOLD, 35));
        setBackground(Utils.blue);
        setForeground(Utils.white);
        setBorder(BorderFactory.createLineBorder(Utils.blue, 3));
        setOpaque(true);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText(toolTipText);

        // Permet de creer une animation pour le curseur et la couleur du bouton
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Utils.blue);
                setBackground(Utils.white);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(Utils.white);
                setBackground(Utils.blue);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
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
