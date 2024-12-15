package main.utils;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonWithIcon extends JButton {

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
