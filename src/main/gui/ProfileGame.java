package main.gui;

import main.entities.User;
import main.entities.UserGamesDetails;
import main.services.ProfileGameService;
import main.utils.*;

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

        // Get Avatar
        User user = profileGameService.getUserAvatar(username);

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
        JButtonProfileGame lProfile = new JButtonProfileGame("Profile", true, "Aller à l'écran du profil");
        Dimension dimensionLProfile = new Dimension((int) (Utils.frameWidth * 0.5), (int) (lProfile.getPreferredSize().height * 1.2));
        lProfile.setBothSize(dimensionLProfile);
        lProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Profile(username).setVisible(true);
            }
        });

        // Label Game
        JButtonProfileGame lGame = new JButtonProfileGame("Game", false, "");
        lGame.setBothSize(dimensionLProfile);

        // Label BestScore
        JLabelPersonalized lBestScore = new JLabelPersonalized("Best Score", 40, true);

        // Label BestScoreJoueur
        JLabelPersonalized lBestScoreJoueur = new JLabelPersonalized(
                String.valueOf(userGamesDetails.getBestScore()), 35, true
        );

        // Button Play
        JButtonPersonalized bPlay = new JButtonPersonalized("Play", "Jouer une partie de 2048");
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
                "Retourner à l'écran d'accueil",
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

        // Label Welcome
        if (message) {
            JLabel lWelcome = new JLabel(newUser ? "Welcome " + username : "Welcome back " + username);
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
