package main.utils;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;

public class JTextFieldPersonalized extends JTextField {
    public JTextFieldPersonalized(JLabelPersonalized lLabel) {
        setFont(new Font("Arial", Font.PLAIN, 18));
        Dimension dimensionUsername = new Dimension(this.getFontMetrics(this.getFont()).charWidth('W') * 20, lLabel.getPreferredSize().height);
        setMaximumSize(dimensionUsername);
        setPreferredSize(dimensionUsername);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
