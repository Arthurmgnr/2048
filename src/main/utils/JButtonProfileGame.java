package main.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonProfileGame extends JLabel {

    public JButtonProfileGame(String text, Boolean cursor, String toolTipText) {
        super(text);

        setFont(new Font("Arial", Font.BOLD, 40));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);

        setForeground(cursor ? Color.BLACK : Utils.blue);

        if (cursor) {
            setToolTipText(toolTipText);

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

    public void setBothSize(Dimension dimension) {
        setPreferredSize(dimension);
        setMaximumSize(dimension);
    }
}
