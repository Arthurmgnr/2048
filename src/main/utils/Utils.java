package main.utils;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    // Dimensions de l'ecran
    private static final int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    // Calcul des dimensions de la fenetre de jeu
    private static final int frameDimension = Math.min(screenWidth, screenHeight);
    public static int frameHeight = Math.min((int) (frameDimension * 0.8), 1000);
    public static int frameWidth = Math.min((int) (frameHeight * 1.5), 800);

    // Coordonnees de la fenetre pour qu'elle s'affiche au centre a l'ecran
    public static int frameX = (screenWidth - frameWidth) / 2;
    public static int frameY = (screenHeight - frameHeight) / 2;

    /**
     * Permet de définir les proprietes de chaque fenetre
     * @param frame la fenetre sur laquelle on applique les proprietes
     */
    public static void setFrameParameters(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(frameX, frameY, frameWidth, frameHeight);
        frame.setTitle("2048");
        frame.setResizable(false);
    }

    // Calcul de la longueur du cote du plateau de jeu (plateau carre)
    private static final int largeurPlateau = Math.min((int) (Utils.frameWidth - 0.05 * Utils.frameWidth), 500);
    private static final int hauteurPlateau = (int) (Utils.frameHeight - 0.2 * Utils.frameHeight - 0.05 * Utils.frameHeight);
    public static int cotePlateau = Math.min(largeurPlateau, hauteurPlateau);
    // Variable pour avoir des angles arrondis a l'ecran
    public static int arrondiPlateau = 10 + (int) (0.02 * Utils.cotePlateau);

    // Calcul pour les cases : ecart entre les cases, longueur des cases (cases carrees) et l'arrondi pour le visuel
    public static int margeCases = (int) (0.02 * cotePlateau);
    public static int coteCase = (cotePlateau - 5 * margeCases) / 4;
    public static int arrondiCase = 10;

    // Couleurs blanche et bleue
    public static Color white = new Color(252, 254, 254);
    public static Color blue = new Color(30, 144, 255);

    // Permet de recuperer la couleur du texte pour une case selon sa valeur
    public static Color getColorText(int valeur) {
        if (valeur == 2 || valeur == 4) {
            return new Color(0x776e65);
        } else {
            return new Color(0xf9f6f2);
        }
    }

    // Permet de definir la couleur de fond d'une case selon sa valeur
    private static final Map<Integer, Color> dicoColorCase = new HashMap<>() {{
        put(2, new Color(0xeee4da));
        put(4, new Color(0xede0c8));
        put(8, new Color(0xf2b179));
        put(16, new Color(0xf59563));
        put(32, new Color(0xf67c5f));
        put(64, new Color(0xf65e3b));
        put(128, new Color(0xedcf72));
        put(256, new Color(0xedcc61));
        put(512, new Color(0xedc850));
        put(1024, new Color(0xedc53f));
        put(2048, new Color(0xedc22e));
    }};

    // Permet de recuperer la couleur de fond d'une case selon le dictionnaire juste au-dessus
    public static Color getColorCase(int valeur) {
        return dicoColorCase.getOrDefault(valeur, new Color(0, 0, 0));
    }

    // Permet de recuperer la taille de police du texte d'une case selon sa valeur
    public static int getSizeText(int valeur) {
        if (valeur == 2 || valeur == 4 || valeur == 8 || valeur == 16 || valeur == 32 || valeur == 64) return 55;
        else if (valeur == 128 || valeur == 256 || valeur == 512) return 45;
        else if (valeur == 1024 || valeur == 2048) return 35;
        else return 30;
    }

    /**
     * Permet de recuperer le message a afficher au joueur a la fin de la partie
     * @param perdu pour savoir si le plateau est complet et plus aucun deplacement possible
     * @param atteint2048 pour savoir si la joueur a deja atteint 2048 au cours de la partie
     * @param tuile valeur de la plus haute tuile atteinte
     * @return le message a afficher au joueur dans la fenetre de dialogue
     */
    public static String getMessageFin(boolean perdu, boolean atteint2048, int tuile) {
        // Le joueur a atteint 2048
        if (!perdu && atteint2048) {
            return TranslationManager.get("messageWinLost.reached2048");
        }
        // Le joueur n'a pas atteint 2048
        else if (perdu && !atteint2048) {
            return TranslationManager.get("messageWinLost.notReached2048") + TranslationManager.get("messageWinLost." + tuile);
        }
        // Le joueur a depasse 2048
        else {
            return TranslationManager.get("messageWinLost.outdated2048") + TranslationManager.get("messageWinLost." + tuile);
        }
    }

    /**
     * Permet de redimensionner une image
     * @param icon l'image a redimensionner
     * @param width la largeur de l'image
     * @param height la hauteur de l'image
     * @return l'image redimensionnee
     */
    public static ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * Permet de recuperer le chemin d'acces aux images d'avatar dans le projet
     * @return la liste des chemins d'acces aux avatar
     */
    public static List<String> listOfAvatars() {
        File folder = new File(getAbsolutePath("avatar"));
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        return Arrays.stream(listOfFiles)
                .map(File::getPath)
                .map(chemin -> chemin.replace("\\", "/"))
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Permet de modifier l'indice dans la liste contenant les chemins d'acces aux avatar et de modifier l'image de l'avatar
     * @param direction indique si l'on doit ajouter ou soustraire, selon le sens de parcourt de la liste
     * @param actualAvatar indice actuel dans la liste
     * @param listOfAvatar liste contenant tous les chemins
     * @param lAvatarImage l'avatar actuel
     * @param widthAvatar largeur de l'image
     * @param heightAvatar hauteur de l'image
     * @return le nouvel indice dans la liste
     */
    public static int changeAvatar(int direction, int actualAvatar, List<String> listOfAvatar, JLabel lAvatarImage, int widthAvatar, int heightAvatar) {
        actualAvatar = (actualAvatar + direction + listOfAvatar.size()) % listOfAvatar.size();

        ImageIcon avatar = new ImageIcon(listOfAvatar.get(actualAvatar));
        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, widthAvatar, heightAvatar);

        lAvatarImage.setIcon(resizedImageIcon);
        return actualAvatar;
    }

    // Definit une largeur et une hauteur de 50 pour les boutons avec des images
    public static int coteButton = 50;

    // Stocke le chemin absolu jusqu'au dossier contenant les ressources du projet
    private final static String absolutePath = "src/main/ressources/";

    /**
     * Permet d'obtenir le chemin complet pour acceder a une image
     * @param path le chemin relatif de l'image
     * @return le chemin complet de l'image
     */
    public static String getAbsolutePath(String path) { return absolutePath + path; }

    // Permet de comparer et de renvoyer la plus grande dimension, selon la largeur
    public static Dimension getMaxDimension(Dimension d1, Dimension d2) {
        if (d1.width > d2.width) return d1;
        else return d2;
    }
}