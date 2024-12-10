package main.gui;

import main.core.Plateau;
import main.services.GameService;
import main.utils.JButtonWithIcon;
import main.utils.JLabelPersonalized;
import main.utils.Utils;
import main.gui.panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JFrame {
    private final GameService gameService = new GameService();
    private final GamePanel panelGame;
    private final JLabelPersonalized lScoreJoueur;
    private final JLabelPersonalized lBestScoreJoueur;
    private final JLabelPersonalized lMovesNb;
    private final Plateau plateau = new Plateau();

    public Game(String username) {
        // Parametres de la fenetre
        Utils.setFrameParameters(this);

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
        JLabelPersonalized lUsername = new JLabelPersonalized(username, new Font("Arial", Font.BOLD, 20));
        lUsername.setLocation2((int) (lAvatarImage.getWidth() + 0.02 * Utils.frameWidth), (int) ((lAvatarImage.getHeight() - lUsername.getPreferredSize().getHeight()) / 2 + 0.01 * Utils.frameHeight));
        panelGame.add(lUsername);

        // Label 2048
        JLabelPersonalized l2048 = new JLabelPersonalized("2048", new Font("Arial", Font.BOLD, 30));
        l2048.setLocation2(Utils.getFrameX(l2048.getSize().width, Utils.frameWidth), (int) (0.02 * Utils.frameHeight));
        panelGame.add(l2048);

        // Label Score
        JLabelPersonalized lScore = new JLabelPersonalized("Score", new Font("Arial", Font.BOLD, 24));
        lScore.setLocation2(Utils.getFrameX(lScore.getSize().width, (int) (0.5 * Utils.frameWidth)), (int) (0.1 * Utils.frameHeight));
        panelGame.add(lScore);

        // Label Score Joueur
        lScoreJoueur = new JLabelPersonalized(String.valueOf(plateau.getScore()), new Font("Arial", Font.BOLD, 24));
        lScoreJoueur.setLocation2(Utils.getFrameX(lScoreJoueur.getSize().width, (int) (0.5 * Utils.frameWidth)), (int) (0.13 * Utils.frameHeight));
        panelGame.add(lScoreJoueur);

        // Label BestScore
        JLabelPersonalized lBestScore = new JLabelPersonalized("Best Score", new Font("Arial", Font.BOLD, 24));
        lBestScore.setLocation2(Utils.getFrameX(lBestScore.getSize().width, (int) (1.5 * Utils.frameWidth)), (int) (0.1 * Utils.frameHeight));
        panelGame.add(lBestScore);

        // Label BestScoreJoueur
        lBestScoreJoueur = new JLabelPersonalized(String.valueOf(userBestScore), new Font("Arial", Font.BOLD, 24));
        lBestScoreJoueur.setLocation2(Utils.getFrameX(lBestScoreJoueur.getSize().width, (int) (1.5 * Utils.frameWidth)), (int) (0.13 * Utils.frameHeight));
        panelGame.add(lBestScoreJoueur);

        // Label Moves
        JLabelPersonalized lMoves = new JLabelPersonalized("Moves", new Font("Arial", Font.BOLD, 24));
        lMoves.setLocation2(Utils.getFrameX(lMoves.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
        panelGame.add(lMoves);

        // Label MovesNb
        lMovesNb = new JLabelPersonalized(String.valueOf(plateau.getNbCoups()), new Font("Arial", Font.BOLD, 24));
        lMovesNb.setLocation2(Utils.getFrameX(lMovesNb.getSize().width, Utils.frameWidth), (int) (0.13 * Utils.frameHeight));
        panelGame.add(lMovesNb);

        // Button Exit
        int widthExit = 50, heightEdit = 50;
//        ImageIcon iconExit = new ImageIcon(Objects.requireNonNull(Profile.class.getResource("/exit.png")));
        ImageIcon iconExit = new ImageIcon("src/main/ressources/exit.png");
//        JButtonWithIcon bExit = new JButtonWithIcon(Utils.resizeImage(iconExit, widthExit, heightEdit), new Rectangle((int) (Utils.frameWidth - widthExit - 0.01 * Utils.frameWidth), (int) (0.01 * Utils.frameHeight), widthExit, heightEdit));
//        panelGame.add(bExit);
//        bExit.setToolTipText("Exit game");
//        bExit.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                String message = Utils.getMessageFin(false, true, plateau.getBestTuile());
//                String[] options = {"Quitter", "Recommencer", "Continuer"};
//                String choice = new JDialogPersonalized(Game.this, message, options).getReponse();
//                System.out.println(choice);
//            }
//        });

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

        lScoreJoueur.setText2(String.valueOf(plateau.getScore()));
        lScoreJoueur.setLocation2(Utils.getFrameX(lScoreJoueur.getSize().width, (int) (0.5 * Utils.frameWidth)), (int) (0.13 * Utils.frameHeight));

        lMovesNb.setText2(String.valueOf(plateau.getNbCoups()));
        lMovesNb.setLocation2(Utils.getFrameX(lMovesNb.getSize().width, Utils.frameWidth), (int) (0.13 * Utils.frameHeight));
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
