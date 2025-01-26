package main.gui;

import main.core.Plateau;
import main.model.Games;
import main.services.GameService;
import main.utils.Utils;
import main.utils.TranslationManager;
import main.utils.JLabelPersonalizedForGridLayout;
import main.utils.ImageIconPersonalized;
import main.utils.JLabelPersonalized;
import main.utils.JButtonWithIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Game extends JFrame {
    // Permet d'appeler le Service associe
    private final GameService gameService = new GameService();
    private final JPanel mainPanel;
    private final JLabelPersonalizedForGridLayout lScoreJoueur;
    private final JLabelPersonalizedForGridLayout lMovesNb;
    private final Plateau plateau = new Plateau();
    private final JPanel plateauPanel;
    private final ArrayList<ArrayList<JPanel>> listOfPanel = new ArrayList<>();
    private final String username;

    public Game(String username) {
        this.username = username;

        Utils.setFrameParameters(this);

        // Get Avatar
        String userAvatar = gameService.getUserAvatar(username);

        // Get BestScore
        int userBestScore = gameService.getUserBestScore(username);

        mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        // Avatar Image
        ImageIconPersonalized lAvatarImage = new ImageIconPersonalized(userAvatar, 50, false);

        // Username
        JLabelPersonalized lUsername = new JLabelPersonalized(username, 20, false);

        // Button Exit
        JButtonWithIcon bExit = new JButtonWithIcon(
                "exit.png",
                TranslationManager.get("game.exit.tooltip"),
                false
        );
        bExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String message = TranslationManager.get("game.exit.message");
                String[] options = {
                        TranslationManager.get("game.exit.yes.button"),
                        TranslationManager.get("game.exit.no.button")
                };
                String[] toolTipTexts = {
                        TranslationManager.get("game.exit.yes.tooltip"),
                        TranslationManager.get("game.exit.no.tooltip")
                };
                int choice = new JDialogPersonalized(Game.this, message, options, toolTipTexts).getReponse();

                if (choice == 0) {
                    dispose();
                    new ProfileGame(username, false, false).setVisible(true);
                } else {
                    mainPanel.requestFocusInWindow();
                }
            }
        });

        topPanel.add(lAvatarImage);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(lUsername);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(bExit);
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Label 2048
        JLabelPersonalized l2048 = new JLabelPersonalized("2048", 30, true);

        // Label Score
        JLabelPersonalizedForGridLayout lScore = new JLabelPersonalizedForGridLayout(
                TranslationManager.get("game.score.label"), 24
        );

        // Label Score Joueur
        lScoreJoueur = new JLabelPersonalizedForGridLayout(String.valueOf(plateau.getScore()), 24);

        // Label BestScore
        JLabelPersonalizedForGridLayout lBestScore = new JLabelPersonalizedForGridLayout(
                TranslationManager.get("game.bestScore.label"), 24
        );

        // Label BestScoreJoueur
        JLabelPersonalizedForGridLayout lBestScoreJoueur = new JLabelPersonalizedForGridLayout(String.valueOf(userBestScore), 24);

        // Label Moves
        JLabelPersonalizedForGridLayout lMoves = new JLabelPersonalizedForGridLayout(
                TranslationManager.get("game.moves.label"), 24
        );

        // Label MovesNb
        lMovesNb = new JLabelPersonalizedForGridLayout(String.valueOf(plateau.getNbCoups()), 24);

        JPanel gameDetailsPanelCenter = new JPanel();
        gameDetailsPanelCenter.setLayout(new BoxLayout(gameDetailsPanelCenter, BoxLayout.X_AXIS));

        JPanel gameDetailsPanel = new JPanel(new GridLayout(2, 3));

        gameDetailsPanel.add(lScore);
        gameDetailsPanel.add(lBestScore);
        gameDetailsPanel.add(lMoves);
        gameDetailsPanel.add(lScoreJoueur);
        gameDetailsPanel.add(lBestScoreJoueur);
        gameDetailsPanel.add(lMovesNb);

        gameDetailsPanelCenter.add(Box.createHorizontalGlue());
        gameDetailsPanelCenter.add(gameDetailsPanel);
        gameDetailsPanelCenter.add(Box.createHorizontalGlue());

        // Creation du plateau
        plateauPanel = new JPanel(new GridLayout(4, 4, Utils.margeCases, Utils.margeCases)) {
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(new Color(188, 172, 160));
                g.fillRoundRect(0,
                        0,
                        Utils.cotePlateau,
                        Utils.cotePlateau,
                        Utils.arrondiPlateau,
                        Utils.arrondiPlateau);
            }
        };
        plateauPanel.setBorder(BorderFactory.createEmptyBorder(Utils.margeCases, Utils.margeCases, Utils.margeCases, Utils.margeCases));
        plateauPanel.setPreferredSize(new Dimension(Utils.cotePlateau, Utils.cotePlateau));
        plateauPanel.setMaximumSize(new Dimension(Utils.cotePlateau, Utils.cotePlateau));

        // Ajout des cases dans le plateau
        for (int i = 0; i < 4; i++) {
            ArrayList<JPanel> list = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                JPanel casePanel = new JPanel(new BorderLayout()) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.setColor(getBackground());
                        g.fillRoundRect(0, 0, Utils.coteCase, Utils.coteCase, Utils.arrondiCase, Utils.arrondiCase);
                    }
                };

                JLabel lCase = new JLabel();
                lCase.setHorizontalAlignment(SwingConstants.CENTER);
                lCase.setVerticalAlignment(SwingConstants.CENTER);

                casePanel.add(lCase, BorderLayout.CENTER);

                plateauPanel.add(casePanel);
                list.add(casePanel);
            }
            listOfPanel.add(list);
        }
        // On met a jour le plateau pour etre sur que les tuiles seront bien affichees
        mettreAJour();

        centerPanel.add(l2048);
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(gameDetailsPanelCenter);
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(plateauPanel);
        centerPanel.add(Box.createVerticalGlue());

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        mainPanel.setFocusable(true);

        add(mainPanel);

        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                formKeyPressed(e);
            }
        });
    }

    // Permet d'indiquer a la methode deroulerPartie quelle direction est choisie
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

    // Effectue toute la logique de deplacement et fusion
    public void deroulerPartie(String fleche) {
        if (plateau.deplacementAFaire(fleche)) {
            // On effectue le deplacement
            plateau.deplacement(fleche);
            // On ajoute une nouvelle case
            plateau.ajoutCase();
            // On met a jour le plateau
            mettreAJour();
            // On verifie si le joueur a atteint 2048
            if (plateau.joueurAtteint2048() && !plateau.getatteint2048()) {
                // On ouvre la fenetre de dialogue
                String message = Utils.getMessageFin(false, true, plateau.getBestTuile());
                String[] options = {
                        TranslationManager.get("game.win.quit.button"),
                        TranslationManager.get("game.win.retry.button"),
                        TranslationManager.get("game.win.continue.button")
                };
                String[] toolTipTexts = {
                        TranslationManager.get("game.win.quit.tooltip"),
                        TranslationManager.get("game.win.retry.tooltip"),
                        TranslationManager.get("game.win.continue.tooltip")
                };
                int choice = new JDialogPersonalized(Game.this, message, options, toolTipTexts).getReponse();

                // Le joueur continue a jouer
                if (choice == 2) {
                    plateau.setatteint2048(true);
                    mainPanel.requestFocusInWindow();
                } else { // Le joueur arrete la partie en cours
                    // Enregistrement dans la BDD
                    Games games = new Games(username, plateau.getScore(), plateau.getNbCoups(), plateau.getBestTuile(), true);

                    gameService.registerGames(games);

                    // Fermeture de la fenetre
                    dispose();
                    // Le joueur arrete de joueur
                    if (choice == 0) {
                        new ProfileGame(username, false, false).setVisible(true);
                    } else { // Le joueur recommence une partie
                        new Game(username).setVisible(true);
                    }
                }
            }
            // On verifie si le joueur ne peut plus effectuer de deplacement
            if (plateau.gameOver()) {
                // On ouvre la fenetre de dialogue
                String message = Utils.getMessageFin(true, plateau.getatteint2048(), plateau.getBestTuile());
                String[] options = {
                        TranslationManager.get("game.lost.quit.button"),
                        TranslationManager.get("game.lost.retry.button")
                };
                String[] toolTipTexts = {
                        TranslationManager.get("game.lost.quit.tooltip"),
                        TranslationManager.get("game.lost.retry.tooltip")
                };
                int choice = new JDialogPersonalized(Game.this, message, options, toolTipTexts).getReponse();

                // Enregistrement dans la BDD
                Games games = new Games(username, plateau.getScore(), plateau.getNbCoups(), plateau.getBestTuile(), plateau.getatteint2048());

                gameService.registerGames(games);

                // On ferme la fenetre
                dispose();
                // Le joueur recommence une partie
                if (choice == 1) {
                    new Game(username).setVisible(true);
                } else { // Le joueur arrete de jouer
                    new ProfileGame(username, false, false).setVisible(true);
                }
            }
        }
    }

    // Permet de mettre a jour les cases du plateau ainsi que le score et le nombre de coups
    public void mettreAJour() {
        plateau.afficher(listOfPanel);

        plateauPanel.repaint();

        lScoreJoueur.setText(String.valueOf(plateau.getScore()));

        lMovesNb.setText(String.valueOf(plateau.getNbCoups()));
    }
}
