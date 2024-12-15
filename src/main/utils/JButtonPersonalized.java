package main.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonPersonalized extends JButton {

    public JButtonPersonalized(String text, String toolTipText) {
        super(text);

        setFont(new Font("Arial", Font.BOLD, 35));
        setBackground(Utils.blue);
        setForeground(Utils.white);
        setBorder(BorderFactory.createLineBorder(Utils.blue, 3));
        setOpaque(true);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText(toolTipText);

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

    public void setBothSize(Dimension dimension) {
        setPreferredSize(dimension);
        setMaximumSize(dimension);
    }
}
