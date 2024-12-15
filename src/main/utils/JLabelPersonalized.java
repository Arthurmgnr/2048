package main.utils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;

public class JLabelPersonalized extends JLabel {
    public JLabelPersonalized(String text, int fontSize, boolean centerAlignment) {
        super(text);

        this.setFont(new Font("Arial", Font.BOLD, fontSize));

        if (centerAlignment)  this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
