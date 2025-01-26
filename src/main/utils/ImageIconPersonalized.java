package main.utils;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Component;

public class ImageIconPersonalized extends JLabel {
    /**
     * Permet de definir une image que l'on peut ensuite afficher dans la fenetre
     * @param imagePath le chemin d'acces a l'image
     * @param dimension la dimension de l'image dans la fenetre (dimension*dimension) l'image sera carree
     * @param centerAlignment permet de savoir si l'image doit etre centree horizontalement
     */
    public ImageIconPersonalized(String imagePath, int dimension, boolean centerAlignment) {
        ImageIcon avatar = new ImageIcon(imagePath);
        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, dimension, dimension);
        setIcon(resizedImageIcon);

        if (centerAlignment) setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
