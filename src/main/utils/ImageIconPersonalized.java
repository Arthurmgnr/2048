package main.utils;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Component;

public class ImageIconPersonalized extends JLabel {
    public ImageIconPersonalized(String imagePath, int dimension, boolean centerAlignment) {
        ImageIcon avatar = new ImageIcon(imagePath);
        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, dimension, dimension);
        setIcon(resizedImageIcon);

        if (centerAlignment) setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
