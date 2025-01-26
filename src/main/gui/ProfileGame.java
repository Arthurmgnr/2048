package main.gui;

import main.model.User;
import main.model.UserGamesDetails;
import main.services.ProfileGameService;
import main.utils.Utils;
import main.utils.TranslationManager;
import main.utils.ImageIconPersonalized;
import main.utils.JLabelPersonalized;
import main.utils.JButtonWithIcon;
import main.utils.JButtonPersonalized;
import main.utils.JButtonProfileGame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfileGame extends JFrame {

    public ProfileGame(String username, Boolean newUser, Boolean message) {
        Utils.setFrameParameters(this);

        // To get data from database
        ProfileGameService profileGameService = new ProfileGameService();

        // Get BestScore
        UserGamesDetails userGamesDetails = profileGameService.getBestScore(username);

        // Get avatar and lang
        User user = profileGameService.getUserDetails(username);

        // Set the language
        TranslationManager.setLanguage(user.getLang());

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Avatar Image
        ImageIconPersonalized lAvatarImage = new ImageIconPersonalized(user.getAvatar(), 50, false);

        // Username
        JLabelPersonalized lUsername = new JLabelPersonalized(username, 20, false);

        // Label Profile
        JButtonProfileGame lProfile = new JButtonProfileGame(
                TranslationManager.get("profileGame.profile.button"),
                true,
                TranslationManager.get("profileGame.profile.tooltip"));
        lProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Profile(username).setVisible(true);
            }
        });

        // Label Game
        JButtonProfileGame lGame = new JButtonProfileGame(
                TranslationManager.get("profileGame.game.button"), false, ""
        );

        // Set sizes of the buttons
        Dimension maxDimension = Utils.getMaxDimension(
                new Dimension((int) (Utils.frameWidth * 0.5), (int) (lProfile.getPreferredSize().height * 1.2)),
                new Dimension((int) (Utils.frameWidth * 0.5), (int) (lGame.getPreferredSize().height * 1.2))
        );
        lProfile.setBothSize(maxDimension);
        lGame.setBothSize(maxDimension);

        // Label BestScore
        JLabelPersonalized lBestScore = new JLabelPersonalized(
                TranslationManager.get("profileGame.bestScore.label"), 40, true
        );

        // Label BestScoreJoueur
        JLabelPersonalized lBestScoreJoueur = new JLabelPersonalized(
                String.valueOf(userGamesDetails.getBestScore()), 35, true
        );

        // Button Play
        JButtonPersonalized bPlay = new JButtonPersonalized(
                TranslationManager.get("profileGame.play.button"), TranslationManager.get("profileGame.play.tooltip")
        );
        Dimension dimensionPlay = new Dimension((int) (bPlay.getPreferredSize().width * 1.4), (int) (bPlay.getPreferredSize().height * 1.2));
        bPlay.setBothSize(dimensionPlay);
        bPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Game(username).setVisible(true);
            }
        });

        // Button Home
        JButtonWithIcon bHome = new JButtonWithIcon(
                "home.png",
                TranslationManager.get("profileGame.home.tooltip"),
                false
        );
        bHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home().setVisible(true);
            }
        });

        topPanel.add(lAvatarImage);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(lUsername);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(bHome);
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(lBestScore);
        centerPanel.add(lBestScoreJoueur);
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(bPlay);
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(Box.createVerticalGlue());

        buttonPanel.add(lProfile);
        buttonPanel.add(lGame);

        // Label Welcome pour dire bienvenue au joueur, seulement la premiere fois qu'on arrive sur cette fenetre
        if (message) {
            JLabel lWelcome = new JLabel(
                    newUser
                            ? TranslationManager.get("profileGame.message.newUser") + username
                            : TranslationManager.get("profileGame.message.oldUser") + username
            );
            lWelcome.setFont(new Font("Arial", Font.BOLD, 20));
            lWelcome.setForeground(Color.BLACK);
            lWelcome.setBackground(Color.GRAY);
            lWelcome.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            lWelcome.setOpaque(true);
            Dimension dimensionWelcome = new Dimension((int) (lWelcome.getPreferredSize().width * 1.5), (int) (lWelcome.getPreferredSize().height * 1.5));
            lWelcome.setPreferredSize(dimensionWelcome);
            lWelcome.setMaximumSize(dimensionWelcome);
            lWelcome.setHorizontalAlignment(SwingConstants.CENTER);
            lWelcome.setVerticalAlignment(SwingConstants.CENTER);
            lWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);

            centerPanel.add(lWelcome, centerPanel.getComponents().length - 1);

            // Le message s'affiche pendant 3s et ensuite on l'efface
            Timer timer = new Timer(3000, e -> {
                lWelcome.setText("");
                lWelcome.setBackground(null);
                lWelcome.setBorder(null);
                lWelcome.setOpaque(false);
            });
            timer.setRepeats(false);
            timer.start();
        }

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
