package main.gui;

import main.core.Plateau;
import main.entities.User;
import main.services.GameService;
import main.utils.JButtonWithIcon;
import main.utils.Utils;
import main.gui.panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Game extends JFrame {
    private final GameService gameService = new GameService();
    private final GamePanel panelGame;
    private final JLabel lScoreJoueur;
    private final JLabel lBestScoreJoueur;
    private final JLabel lMovesNb;
    private final Plateau plateau = new Plateau();

    public Game(String username) {
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

        // Get Avatar
        String userAvatar = gameService.getUserAvatar(username);

        // Get BestScore
        int userBestScore = gameService.getUserBestScore(username);

        // Avatar Image
        ImageIcon avatar = new ImageIcon(userAvatar);
        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, 50, 50);
        JLabel lAvatarImage = new JLabel(resizedImageIcon);
        lAvatarImage.setBounds((int) (0.01 * Utils.frameWidth), (int) (0.01 * Utils.frameHeight), 50, 50);
        panelGame.add(lAvatarImage);

        // Username
        JLabel lUsername = new JLabel(username);
        lUsername.setFont(new Font("Arial", Font.BOLD, 20));
        lUsername.setSize(lUsername.getPreferredSize());
        lUsername.setLocation((int) (lAvatarImage.getWidth() + 0.02 * Utils.frameWidth), (int) ((lAvatarImage.getHeight() - lUsername.getPreferredSize().getHeight()) / 2 + 0.01 * Utils.frameHeight));
        panelGame.add(lUsername);

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
        lBestScoreJoueur = new JLabel(String.valueOf(userBestScore));
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

        // Button Exit
        int widthExit = 50, heightEdit = 50;
        ImageIcon iconExit = new ImageIcon(Objects.requireNonNull(Profile.class.getResource("/exit.png")));
        JButtonWithIcon bExit = new JButtonWithIcon(Utils.resizeImage(iconExit, widthExit, heightEdit), new Rectangle((int) (Utils.frameWidth - widthExit - 0.01 * Utils.frameWidth), (int) (0.01 * Utils.frameHeight), widthExit, heightEdit));
        panelGame.add(bExit);
        bExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                new Dialog().setVisible(true);
//                String choice = Dialog.showDialog(Game.this);
//                System.out.println(choice);

            }
        });

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
//                gagne();
                plateau.setatteint2048(true);

                String message = Utils.getMessageFin(false, true, plateau.getBestTuile());
                String[] options = {"Quitter", "Recommencer", "Continuer"};
                JLabel messageLabel = new JLabel(message);
                messageLabel.setFont(new Font("Arial", Font.BOLD, 30));
                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                int choice = JOptionPane.showOptionDialog(
                        panelGame,
                        messageLabel,
                        "2048",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[2]
                );
                if (choice == 0) {
                    System.out.println("Quitter");
                    // Enregistrer dans la BDD
                    // Revenir a la page ProfileGame
                } else if (choice == 1) {
                    System.out.println("Recommencer");
                    // Enregistrer dans la BDD
                    // Recreer une page Game
                } else if (choice == 2) {
                    System.out.println("Continuer");
                    // On passe a true le atteint2048 du plateau
                } else {
                    System.out.println("Annulation.");
                    // On fait comme dans le cas 2
                }
            }
            // On verifie si le joueur ne peut plus effectuer de deplacement
            if (plateau.gameOver()) {
//                perdre();
                SwingUtilities.invokeLater(() -> {
                    String message = Utils.getMessageFin(true, plateau.getatteint2048(), plateau.getBestTuile());
                    String[] options = {"Quitter", "Recommencer"};
                    JLabel messageLabel = new JLabel(message);
                    messageLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    int choice = JOptionPane.showOptionDialog(
                            panelGame,
                            messageLabel,
                            "2048",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            options[1]
                    );
                    if (choice == 0) {
                        System.out.println("Quitter");
                        // Enregistrer dans la BDD
                        // Revenir a la page ProfileGame
                    } else if (choice == 1) {
                        System.out.println("Recommencer");
                        // Enregistrer dans la BDD
                        // Recreer une page Game
                    } else {
                        System.out.println("Annulation.");
                        // On fait comme dans le cas 1
                    }
                });
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
