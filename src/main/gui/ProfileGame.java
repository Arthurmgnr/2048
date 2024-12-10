package main.gui;

import main.constants.MessageConstants;
import main.entities.User;
import main.entities.UserGamesDetails;
import main.gui.panels.ProfileGamePanel;
import main.services.ProfileGameService;
import main.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class ProfileGame extends JFrame {
    private final ProfileGameService profileGameService = new ProfileGameService();

    public ProfileGame(String username, Boolean newUser, Boolean message) {
        // Parametres de la fenetre
        Utils.setFrameParameters(this);

        // Get BestScore
        UserGamesDetails userGamesDetails = profileGameService.getBestScore(username);

        // Get Avatar
        User user = profileGameService.getUserAvatar(username);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Avatar Image
        ImageIcon avatar = new ImageIcon(user.getAvatar());
        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, 50, 50);
        JLabel lAvatarImage = new JLabel(resizedImageIcon);

        // Username
        JLabel lUsername = new JLabel(username);
        lUsername.setFont(new Font("Arial", Font.BOLD, 20));

        // Label Profile
        JLabel lProfile = new JLabel("Profile");
        lProfile.setFont(new Font("Arial", Font.BOLD, 40));
        lProfile.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        Dimension dimension = new Dimension((int) (Utils.frameWidth * 0.5), (int) (lProfile.getPreferredSize().height * 1.2));
        lProfile.setMaximumSize(dimension);
        lProfile.setPreferredSize(dimension);
        lProfile.setForeground(Color.BLACK);
        lProfile.setHorizontalAlignment(SwingConstants.CENTER);
        lProfile.setVerticalAlignment(SwingConstants.CENTER);
        lProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lProfile.setBackground(Color.LIGHT_GRAY);
                lProfile.setOpaque(true);
                lProfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lProfile.setBackground(Color.WHITE);
                lProfile.setOpaque(false);
                lProfile.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Profile(username).setVisible(true);
            }
        });

        // Label Game
        JLabel lGame = new JLabel("Game");
        lGame.setFont(new Font("Arial", Font.BOLD, 40));
        lGame.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lGame.setMaximumSize(dimension);
        lGame.setPreferredSize(dimension);
        lGame.setForeground(Utils.blue);
        lGame.setHorizontalAlignment(SwingConstants.CENTER);
        lGame.setVerticalAlignment(SwingConstants.CENTER);

        // Label BestScore
        JLabel lBestScore = new JLabel("Best Score");
        lBestScore.setFont(new Font("Arial", Font.BOLD, 40));
        lBestScore.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label BestScoreJoueur
        JLabel lBestScoreJoueur = new JLabel(String.valueOf(userGamesDetails.getBestScore()));
        lBestScoreJoueur.setFont(new Font("Arial", Font.BOLD, 35));
        lBestScoreJoueur.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button Play
        JButton bPlay = new JButton("Play");
        bPlay.setFont(new Font("Arial", Font.BOLD, 35));
        bPlay.setBackground(Utils.blue);
        bPlay.setForeground(Utils.white);
        bPlay.setBorder(BorderFactory.createLineBorder(Utils.blue, 3));
        bPlay.setOpaque(true);
        Dimension dimensionPlay = new Dimension((int) (bPlay.getPreferredSize().width * 1.4), (int) (bPlay.getPreferredSize().height * 1.2));
        bPlay.setMaximumSize(dimensionPlay);
        bPlay.setPreferredSize(dimensionPlay);
        bPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Game(username).setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                bPlay.setForeground(Utils.blue);
                bPlay.setBackground(Utils.white);
                bPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                bPlay.setForeground(Utils.white);
                bPlay.setBackground(Utils.blue);
                bPlay.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        bPlay.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button Home
        int width = 50, height = 50;
        ImageIcon icon = new ImageIcon("src/main/ressources/home.png");
        JButtonWithIcon bHome = new JButtonWithIcon(Utils.resizeImage(icon, width, height), new Dimension(width, height));
        bHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home().setVisible(true);
            }
        });

        usernamePanel.add(lAvatarImage);
        usernamePanel.add(Box.createHorizontalStrut(10));
        usernamePanel.add(lUsername);
        usernamePanel.add(Box.createHorizontalGlue());
        usernamePanel.add(bHome);
        usernamePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(lBestScore);
        centerPanel.add(lBestScoreJoueur);
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(bPlay);
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(Box.createVerticalGlue());

        buttonPanel.add(lProfile);
        buttonPanel.add(lGame);

        mainPanel.add(usernamePanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);



        // Gestionnaire de placement
//        ProfileGamePanel profileGamePanel = new ProfileGamePanel(this);
//        profileGamePanel.setLayout(null);
//        profileGamePanel.setFocusable(true);
//        setContentPane(profileGamePanel);
//
//        // Get BestScore
//        UserGamesDetails userGamesDetails = profileGameService.getBestScore(username);
//
//        // Get Avatar
//        User user = profileGameService.getUserAvatar(username);
//
//        // Avatar Image
//        ImageIcon avatar = new ImageIcon(user.getAvatar());
//        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, 50, 50);
//        JLabel lAvatarImage = new JLabel(resizedImageIcon);
//        lAvatarImage.setBounds((int) (0.01 * Utils.frameWidth), (int) (0.01 * Utils.frameHeight), 50, 50);
//        profileGamePanel.add(lAvatarImage);
//
//        // Username
//        JLabelPersonalized lUsername = new JLabelPersonalized(username, new Font("Arial", Font.BOLD, 20));
//        lUsername.setLocation2(Utils.getFrameX(lUsername.getSize().width, (int) (0.5 * Utils.frameWidth)), (int) (0.35 * Utils.frameHeight));
//        profileGamePanel.add(lUsername);
//
//        // Label Welcome
//        if (message) {
//            JLabel lWelcome = new JLabel(newUser ? "Welcome " + username : "Welcome back " + username);
//            lWelcome.setFont(new Font("Arial", Font.BOLD, 20));
//            lWelcome.setForeground(Color.BLACK);
//            lWelcome.setBackground(Color.GRAY);
//            lWelcome.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
//            lWelcome.setOpaque(true);
//            lWelcome.setSize((int) (lWelcome.getPreferredSize().width * 1.5), (int) (lWelcome.getPreferredSize().height * 1.5));
//            lWelcome.setHorizontalAlignment(SwingConstants.CENTER);
//            lWelcome.setVerticalAlignment(SwingConstants.CENTER);
//            lWelcome.setLocation(Utils.getFrameX(lWelcome.getSize().width, Utils.frameWidth), (int) (0.65 * Utils.frameHeight));
//            profileGamePanel.add(lWelcome);
//            Timer timer = new Timer(3000, e -> {
//                lWelcome.setText("");
//                lWelcome.setBackground(null);
//                lWelcome.setBorder(null);
//                lWelcome.setOpaque(false);
//            });
//            timer.setRepeats(false);
//            timer.start();
//        }
//
//        // Label BestScore
//        JLabelPersonalized lBestScore = new JLabelPersonalized("Best Score", new Font("Arial", Font.BOLD, 40));
//        lBestScore.setLocation2(Utils.getFrameX(lBestScore.getSize().width, Utils.frameWidth), (int) (0.15 * Utils.frameHeight));
//        profileGamePanel.add(lBestScore);
//
//        // Label BestScoreJoueur
//        JLabelPersonalized lBestScoreJoueur = new JLabelPersonalized(String.valueOf(userGamesDetails.getBestScore()), new Font("Arial", Font.BOLD, 35));
//        lBestScoreJoueur.setLocation2(Utils.getFrameX(lBestScoreJoueur.getSize().width, Utils.frameWidth), (int) (0.25 * Utils.frameHeight));
//        profileGamePanel.add(lBestScoreJoueur);
//
//        // Button Play
//        JButtonPersonalized bPlay = new JButtonPersonalized("Play", new Font("Arial", Font.BOLD, 40));
//        bPlay.setLocation2(Utils.getFrameX(bPlay.getSize().width, Utils.frameWidth), (int) (0.45 * Utils.frameHeight));
//        profileGamePanel.add(bPlay);
//        bPlay.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                dispose();
//                new Game(username).setVisible(true);
//            }
//        });
//
//        // Label Profile
//        JButtonProfileGame lProfile = new JButtonProfileGame("Profile", 0, Color.BLACK, true);
//        profileGamePanel.add(lProfile);
//        lProfile.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                dispose();
//                new Profile(username).setVisible(true);
//            }
//        });
//
//        // Label Game
//        JButtonProfileGame lGame = new JButtonProfileGame("Game", (int) (Utils.frameWidth * 0.5), Utils.blue, false);
//        profileGamePanel.add(lGame);
//
//        // Button Home
//        int width = 50, height = 50;
//        ImageIcon icon = new ImageIcon("src/main/ressources/home.png");
////        JButtonWithIcon bHome = new JButtonWithIcon(Utils.resizeImage(icon, width, height), new Rectangle((int) (Utils.frameWidth - width - 0.02 * Utils.frameWidth), (int) (0.01 * Utils.frameHeight), width, height));
////        profileGamePanel.add(bHome);
////        bHome.addMouseListener(new MouseAdapter() {
////            @Override
////            public void mouseClicked(MouseEvent e) {
////                dispose();
////                new Home().setVisible(true);
////            }
////        });
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
