package utils;

import java.awt.*;
import java.util.HashMap;

public class Utils {
    // Recupere les dimensions de l'ecran
    private static double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
//    private static double screenWidth = 1280;
//    private  static double screenHeight = 720;
//    private static double screenWidth = 1440;
//    private  static double screenHeight = 900;
//    private static double screenWidth = 1680;
//    private  static double screenHeight = 1050;
//    private static double screenWidth = 1920;
//    private  static double screenHeight = 1080;
//    private static double screenWidth = 1200;
//    private  static double screenHeight = 800;
//    private static double screenWidth = 1024;
//    private  static double screenHeight = 768;

    // Dimensions de la fenetre
    public static int frameWidth = (int) (screenWidth * 0.5);
    public static int frameHeight = (int) (screenHeight * 0.8);

    // Coordonnees de la fenetre pour qu'elle s'affiche au centre a l'ecran
    public static int frameX = (int) ((screenWidth - frameWidth) / 2);
    public static int frameY = (int) ((screenHeight - frameHeight) / 2);

    // Permet de centrer un element horizontalement
    /*
    CHANGER CETTE FONCTION POUR PASSER EN PARAMETRE LES BONNES CHOSES
     */
    public static int getFrameX(int size, int type) {
        switch (type) {
            // Au centre de la page
            case 0:
                return (frameWidth - size) / 2;
            // Au centre dans la partie gauche de la page
            case 1:
                return (frameWidth / 2 - size) / 2;
            // Au centre dans la partie droite de la page
            case 2:
                return frameWidth / 2 + (frameWidth / 2 - size) / 2;
            default:
                return 0;
        }
    }

    // Dimension des cotes du plateau de jeu
    private static int largeurPlateau = (int) (Utils.frameWidth - 0.05 * Utils.frameWidth);
    private static int hauteurPlateau = (int) (Utils.frameHeight - 0.2 * Utils.frameHeight - 0.05 * Utils.frameHeight);
    public static int cotePlateau = Math.min(largeurPlateau, hauteurPlateau);
    public static int arrondiPlateau = 10 + (int) (0.02 * Utils.cotePlateau);

    public static int margeCases = (int) (0.02 * cotePlateau);
    public static int coteCase = (cotePlateau - 5 * margeCases) / 4;
    public static int arrondiCase = 10;


    // Cases
    public static Color getColorText(int valeur) {
        if (valeur == 2 || valeur == 4) {
            return new Color(0x776e65);
        } else {
            return new Color(0xf9f6f2);
        }
    }
    public static Color getColorCase(int valeur) {
        if (valeur == 2) return new Color(0xeee4da);
        else if (valeur == 4) return new Color(0xede0c8);
        else if (valeur == 8) return new Color(0xf2b179);
        else if (valeur == 16) return new Color(0xf59563);
        else if (valeur == 32) return new Color(0xf67c5f);
        else if (valeur == 64) return new Color(0xf65e3b);
        else if (valeur == 128) return new Color(0xedcf72);
        else if (valeur == 256) return new Color(0xedcc61);
        else if (valeur == 512) return new Color(0xedc850);
        else if (valeur == 1024) return new Color(0xedc53f);
        else if (valeur == 2048) return new Color(0xedc22e);
        else return new Color(0, 0, 0);
    }

    public static int getSizeText(int valeur) {
        if (valeur == 2 || valeur == 4) return 55;
        else return 2;
    }
}