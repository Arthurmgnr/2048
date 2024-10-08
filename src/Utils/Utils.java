package Utils;

import java.awt.*;

public class Utils {
    private static double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public static int frameWidth = 900;
    public static int frameHeight = 1100;

    public static int frameX = (int) (screenWidth - frameWidth) / 2;
    public static int frameY = (int) (screenHeight - frameHeight) / 2;

    public static int getFrameX(int size, int type) {
        switch (type) {
            // Au centre de la page
            case 0:
                return (int) (frameWidth - size) / 2;
            // Au centre dans la partie gauche de la page
            case 1:
                return (int) (frameWidth / 2 - size) / 2;
            // Au centre dans la partie droite de la page
            case 2:
                return (int) frameWidth / 2 + (frameWidth / 2 - size) / 2;
            default:
                return 0;
        }
    }
}