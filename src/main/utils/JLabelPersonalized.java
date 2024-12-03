package main.utils;

import javax.swing.*;
import java.awt.*;

public class JLabelPersonalized extends JLabel {
    public JLabelPersonalized(String text, Font font) { //, int locationX, int locationY) {
        super(text);

        int firstChar = text.length() != 0 ? text.charAt(0) : 'A';

        this.setFont(font);
        this.setSize(new Dimension(this.getFontMetrics(this.getFont()).charWidth(firstChar) * text.length(), this.getFontMetrics(this.getFont()).getHeight()));
        this.setHorizontalAlignment(JLabel.CENTER);
    }

    public void setLocation2(int locationX, int locationY) {
        this.setLocation(locationX, locationY);
    }

    public void setText2(String text) {
        int firstChar = text.length() != 0 ? text.charAt(0) : 'A';

        this.setText(text);
        this.setSize(new Dimension(this.getFontMetrics(this.getFont()).charWidth(firstChar) * text.length(), this.getFontMetrics(this.getFont()).getHeight()));
    }
}
