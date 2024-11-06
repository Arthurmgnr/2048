package gui;

import core.Plateau;
import utils.Utils;
import panel.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JFrame {
    private final GamePanel panelGame;
    private final JLabel lScoreJoueur;
    private final JLabel lBestScoreJoueur;
    private final JLabel lMovesNb;
    private final Plateau plateau = new Plateau();

    public Game() {
        // Parametres de la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(Utils.frameX, Utils.frameY, Utils.frameWidth, Utils.frameHeight);
        setTitle("2048");
        setResizable(false);

        // Gestionnaire de placement
        panelGame = new GamePanel(this);
        panelGame.setLayout(null);
        panelGame.setFocusable(true);
        setContentPane(panelGame);

        // Label 2048
        JLabel l2048 = new JLabel("2048");
        l2048.setFont(new Font("Arial", Font.BOLD, 30));
        l2048.setSize(l2048.getPreferredSize());
        l2048.setLocation(Utils.getFrameX(l2048.getSize().width, Utils.frameWidth), (int) (0.02 * Utils.frameHeight));
        panelGame.add(l2048);

        // Label Score
        JLabel lScore = new JLabel("Score");
        lScore.setFont(new Font("Arial", Font.BOLD, 24));
        lScore.setSize(lScore.getPreferredSize());
        lScore.setLocation(Utils.getFrameX(lScore.getSize().width, (int) (Utils.frameWidth * 0.5)), (int) (0.1 * Utils.frameHeight));
        panelGame.add(lScore);

        // Label Score Joueur
        lScoreJoueur = new JLabel(String.valueOf(plateau.getScore()));
        lScoreJoueur.setFont(new Font("Arial", Font.BOLD, 24));
        lScoreJoueur.setSize(lScoreJoueur.getPreferredSize());
        lScoreJoueur.setLocation(Utils.getFrameX(lScoreJoueur.getSize().width, (int) (Utils.frameWidth * 0.5)), (int) (0.13 * Utils.frameHeight));
        panelGame.add(lScoreJoueur);

        // Label BestScore
        JLabel lBestScore = new JLabel("Best Score");
        lBestScore.setFont(new Font("Arial", Font.BOLD, 24));
        lBestScore.setSize(lBestScore.getPreferredSize());
        lBestScore.setLocation(Utils.getFrameX(lBestScore.getSize().width, (int) (Utils.frameWidth * 1.5)), (int) (0.1 * Utils.frameHeight));
        panelGame.add(lBestScore);

        // Label BestScoreJoueur
        lBestScoreJoueur = new JLabel(String.valueOf(5555));
        lBestScoreJoueur.setFont(new Font("Arial", Font.BOLD, 24));
        lBestScoreJoueur.setSize(lBestScoreJoueur.getPreferredSize());
        lBestScoreJoueur.setLocation(Utils.getFrameX(lBestScoreJoueur.getSize().width, (int) (Utils.frameWidth * 1.5)), (int) (0.13 * Utils.frameHeight));
        panelGame.add(lBestScoreJoueur);

        // Label Moves
        JLabel lMoves = new JLabel("Moves");
        lMoves.setFont(new Font("Arial", Font.BOLD, 24));
        lMoves.setSize(lMoves.getPreferredSize());
        lMoves.setLocation(Utils.getFrameX(lMoves.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
        panelGame.add(lMoves);

        // Label MovesNb
        lMovesNb = new JLabel(String.valueOf(plateau.getNbCoups()));
        lMovesNb.setFont(new Font("Arial", Font.BOLD, 24));
        lMovesNb.setSize(lMovesNb.getPreferredSize());
        lMovesNb.setLocation(Utils.getFrameX(lMovesNb.getSize().width, Utils.frameWidth), (int) (0.13 * Utils.frameHeight));
        panelGame.add(lMovesNb);

        panelGame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                formKeyPressed(e);
            }
        });
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        plateau.afficher(g);

        lScoreJoueur.setText(String.valueOf(plateau.getScore()));
        lScoreJoueur.setSize(lScoreJoueur.getPreferredSize());
        lScoreJoueur.setLocation(Utils.getFrameX(lScoreJoueur.getSize().width, (int) (Utils.frameWidth * 0.5)), (int) (0.13 * Utils.frameHeight));

        lBestScoreJoueur.setText(String.valueOf(5555));
        lBestScoreJoueur.setSize(lBestScoreJoueur.getPreferredSize());
        lBestScoreJoueur.setLocation(Utils.getFrameX(lBestScoreJoueur.getSize().width, (int) (Utils.frameWidth * 1.5)), (int) (0.13 * Utils.frameHeight));

        lMovesNb.setText(String.valueOf(plateau.getNbCoups()));
        lMovesNb.setSize(lMovesNb.getPreferredSize());
        lMovesNb.setLocation(Utils.getFrameX(lMovesNb.getSize().width, Utils.frameWidth), (int) (0.13 * Utils.frameHeight));
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

        panelGame.repaint();
    }

    public void gagne() {
//        panelGame.setFocusable(false);
        System.out.println(Utils.getMessageFin(false, true, plateau.getBestTuile()));
    }

    public void perdre() {
        System.out.println(Utils.getMessageFin(true, plateau.getatteint2048(), plateau.getBestTuile()));
        panelGame.setFocusable(false);
    }
}
