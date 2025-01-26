package main.utils;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonWithIcon extends JButton {

    /**
     * Permet de definir un bouton qui contient une image
     * @param path le chemin d'acces a l'image
     * @param toolTipText l'info-bulle qui indique a l'utilisateur ce que fait le bouton
     * @param centerAlignement permet de savoir si le bouton doit etre centree horizontalement
     */
    public JButtonWithIcon(String path, String toolTipText, boolean centerAlignement) { //ImageIcon icon, Dimension size, String toolTipText) {
        int cote = Utils.coteButton;

        ImageIcon iconPreviousAvatar = new ImageIcon(Utils.getAbsolutePath(path));
        setIcon(Utils.resizeImage(iconPreviousAvatar, cote, cote));

        setSize(new Dimension(cote, cote));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setToolTipText(toolTipText);

        if (centerAlignement) setAlignmentX(Component.CENTER_ALIGNMENT);

        // Permet de creer une animation pour le curseur
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
}
