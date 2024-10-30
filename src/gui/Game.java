package gui;

import core.Plateau;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame {

//    private JPanel myContentPane;
    private MonPanel myContentPane;
    private JLabel l2048;
    private JLabel lScore;
    private JLabel lScoreJoueur;
    private JLabel lBestScore;
    private JLabel lBestScoreJoueur;
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
        myContentPane.setFocusable(true);
        setContentPane(myContentPane);

        // Label 2048
        l2048 = new JLabel("2048");
        l2048.setFont(new Font("Arial", Font.BOLD, 30));
        l2048.setSize(l2048.getPreferredSize());
        l2048.setLocation(Utils.getFrameX(l2048.getSize().width, Utils.frameWidth), (int) (0.02 * Utils.frameHeight));
        myContentPane.add(l2048);

        // Label Score
        lScore = new JLabel("Score");
        lScore.setFont(new Font("Arial", Font.BOLD, 24));
        lScore.setSize(lScore.getPreferredSize());
        lScore.setLocation(Utils.getFrameX(lScore.getSize().width, Utils.frameWidth / 2), (int) (0.1 * Utils.frameHeight));
        myContentPane.add(lScore);

        // Label Score Joueur
        lScoreJoueur = new JLabel(String.valueOf(plateau.getScore()));
        lScoreJoueur.setFont(new Font("Arial", Font.BOLD, 24));
        lScoreJoueur.setSize(lScoreJoueur.getPreferredSize());
        lScoreJoueur.setLocation(Utils.getFrameX(lScoreJoueur.getSize().width, Utils.frameWidth / 2), (int) (0.13 * Utils.frameHeight));
        myContentPane.add(lScoreJoueur);

        // Label BestScore
        lBestScore = new JLabel("Best Score");
        lBestScore.setFont(new Font("Arial", Font.BOLD, 24));
        lBestScore.setSize(lBestScore.getPreferredSize());
        lBestScore.setLocation(Utils.getFrameX(lBestScore.getSize().width, (int) (Utils.frameWidth * 1.5)), (int) (0.1 * Utils.frameHeight));
        myContentPane.add(lBestScore);

        myContentPane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                formKeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void dessiner(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        plateau.afficher(g);

        lScoreJoueur.setText(String.valueOf(plateau.getScore()));
        lScoreJoueur.setSize(lScoreJoueur.getPreferredSize());
        lScoreJoueur.setLocation(Utils.getFrameX(lScoreJoueur.getSize().width, Utils.frameWidth / 2), (int) (0.13 * Utils.frameHeight));
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                deroulerPartie("Down");
                break;
            case KeyEvent.VK_UP:
                deroulerPartie("Up");
                break;
            case KeyEvent.VK_LEFT:
                deroulerPartie("Left");
                break;
            case KeyEvent.VK_RIGHT:
                deroulerPartie("Right");
                break;
        }
    }

    public void deroulerPartie(String fleche) {
        if (plateau.deplacementAFaire(fleche)) {
            // On effectue le deplacement
            plateau.deplacement(fleche);
            // On ajoute une nouvelle case
            plateau.ajoutCase();
            // On verifie si le joueur a atteint 2048
            if (plateau.joueurAtteint2048() && !plateau.getatteint2048()) {
                gagne();
                plateau.setatteint2048(true);
            }
            // On verifie si le joueur ne peut plus effectuer de deplacement
            if (plateau.gameOver()) {
                perdre();
//                plateau.setPerdu(true);
            }
        }

        myContentPane.repaint();
    }

    public void gagne() {
//        myContentPane.setFocusable(false);
        System.out.println(Utils.getMessageFin(false, true, plateau.getBestTuile()));
    }

    public void perdre() {
        System.out.println(Utils.getMessageFin(true, plateau.getatteint2048(), plateau.getBestTuile()));
        myContentPane.setFocusable(false);
    }
}
