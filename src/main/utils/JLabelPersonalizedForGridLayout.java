package main.utils;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class JLabelPersonalizedForGridLayout extends JLabel {
    public JLabelPersonalizedForGridLayout(String text, int font) {
        super(text);

        setFont(new Font("Arial", Font.BOLD, font));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }
}
