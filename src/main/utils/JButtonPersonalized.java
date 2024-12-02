package main.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonPersonalized extends JButton {

    public JButtonPersonalized(String text, Font font, int widthFrame, double location) {
        super(text);
        this.setFont(font);
        this.setSize((int) (this.getPreferredSize().width * 1.1), (int) (this.getPreferredSize().height * 1.1));
        this.setLocation(Utils.getFrameX(this.getSize().width, widthFrame), (int) (location * Utils.frameHeight));
        this.setBackground(Utils.blue);
        this.setForeground(Utils.white);
        this.setBorder(BorderFactory.createLineBorder(Utils.blue, 3));
        this.setOpaque(true);

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
}
