package gui;

import core.Plateau;
import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

//    private JPanel myContentPane;
    private MonPanel myContentPane;
    private JLabel l2048;
    private JLabel lScore;
    private JLabel lBestScore;
    private Plateau plateau = new Plateau();

    public Game() {
        // Parametres de la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(Utils.frameX, Utils.frameY, Utils.frameWidth, Utils.frameHeight);
        setTitle("2048");
        setResizable(false);

        // Gestionnaire de placement
        myContentPane = new MonPanel(this);
        myContentPane.setLayout(null);
        setContentPane(myContentPane);

        // Label 2048
        l2048 = new JLabel("2048");
        l2048.setFont(new Font("Arial", Font.BOLD, 30));
        l2048.setSize(l2048.getPreferredSize());
//        l2048.setLocation(Utils.getFrameX(l2048.getSize().width, 0), 100);
        l2048.setLocation(Utils.getFrameX(l2048.getSize().width, 0), (int) (0.02 * Utils.frameHeight));
        myContentPane.add(l2048);

        // Label Score
        lScore = new JLabel("Score");
        lScore.setFont(new Font("Arial", Font.BOLD, 24));
        lScore.setSize(lScore.getPreferredSize());
//        lScore.setLocation(Utils.getFrameX(lScore.getSize().width, 1), 200);
        lScore.setLocation(Utils.getFrameX(lScore.getSize().width, 1), (int) (0.1 * Utils.frameHeight));
        myContentPane.add(lScore);

        // Label BestScore
        lBestScore = new JLabel("Best Score");
        lBestScore.setFont(new Font("Arial", Font.BOLD, 24));
        lBestScore.setSize(lBestScore.getPreferredSize());
//        lBestScore.setLocation(Utils.getFrameX(lBestScore.getSize().width, 2), 200);
        lBestScore.setLocation(Utils.getFrameX(lBestScore.getSize().width, 2), (int) (0.1 * Utils.frameHeight));
        myContentPane.add(lBestScore);


    }

    public void dessiner(Graphics g) {
        Graphics bufferGraphics;
        Image offscreen;

        // On crée une image en mémoire de la taille du ContentPane
        offscreen = createImage(getWidth(), getHeight());

        // On récupère l'objet de type Graphics permettant de dessiner dans cette image
        bufferGraphics = offscreen.getGraphics();

        // On colore le fond de l'image en blanc
        bufferGraphics.setColor(Color.WHITE);
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

        plateau.afficher(bufferGraphics);

        // On afficher l'image mémoire à l'écran
        g.drawImage(offscreen, 0, 0, null);
    }
}
