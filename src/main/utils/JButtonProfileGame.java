package main.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonProfileGame extends JLabel {

    public JButtonProfileGame(String text, int location, Color foregroundColor, Boolean cursor) {
        super(text);
        this.setFont(new Font("Arial", Font.BOLD, 40));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.setSize((int) (Utils.frameWidth * 0.5), (int) (this.getPreferredSize().height * 1.2));
        this.setLocation(location, Utils.frameHeight - (this.getPreferredSize().height - 8) * 2);
        this.setForeground(foregroundColor);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);

        if (cursor) {
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
}
