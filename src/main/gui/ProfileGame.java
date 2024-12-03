package main.gui;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(Utils.frameX, Utils.frameY, Utils.frameWidth, Utils.frameHeight);
        setTitle("2048");
        setResizable(false);

        // Gestionnaire de placement
        ProfileGamePanel profileGamePanel = new ProfileGamePanel(this);
        profileGamePanel.setLayout(null);
        profileGamePanel.setFocusable(true);
        setContentPane(profileGamePanel);

        // Get BestScore
        UserGamesDetails userGamesDetails = profileGameService.getBestScore(username);

        // Get Avatar
        User user = profileGameService.getUserAvatar(username);

        // Avatar Image
        ImageIcon avatar = new ImageIcon(user.getAvatar());
        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, 50, 50);
        JLabel lAvatarImage = new JLabel(resizedImageIcon);
        lAvatarImage.setBounds((int) (0.01 * Utils.frameWidth), (int) (0.01 * Utils.frameHeight), 50, 50);
        profileGamePanel.add(lAvatarImage);

        // Username
//        JLabel lUsername = new JLabel(username);
//        lUsername.setFont(new Font("Arial", Font.BOLD, 20));
//        lUsername.setSize(lUsername.getPreferredSize());
//        lUsername.setLocation((int) (lAvatarImage.getWidth() + 0.02 * Utils.frameWidth), (int) ((lAvatarImage.getHeight() - lUsername.getPreferredSize().getHeight()) / 2 + 0.01 * Utils.frameHeight));
        JLabelPersonalized lUsername = new JLabelPersonalized(username, new Font("Arial", Font.BOLD, 20));
        lUsername.setLocation2(Utils.getFrameX(lUsername.getSize().width, (int) (0.5 * Utils.frameWidth)), (int) (0.35 * Utils.frameHeight));
        profileGamePanel.add(lUsername);

        // Label Welcome
        if (message) {
            JLabel lWelcome = new JLabel(newUser ? "Welcome " + username : "Welcome back " + username);
            lWelcome.setFont(new Font("Arial", Font.BOLD, 20));
            lWelcome.setForeground(Color.BLACK);
            lWelcome.setBackground(Color.GRAY);
            lWelcome.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            lWelcome.setOpaque(true);
            lWelcome.setSize((int) (lWelcome.getPreferredSize().width * 1.5), (int) (lWelcome.getPreferredSize().height * 1.5));
            lWelcome.setHorizontalAlignment(SwingConstants.CENTER);
            lWelcome.setVerticalAlignment(SwingConstants.CENTER);
            lWelcome.setLocation(Utils.getFrameX(lWelcome.getSize().width, Utils.frameWidth), (int) (0.65 * Utils.frameHeight));
            profileGamePanel.add(lWelcome);
            Timer timer = new Timer(3000, e -> {
                lWelcome.setText("");
                lWelcome.setBackground(null);
                lWelcome.setBorder(null);
                lWelcome.setOpaque(false);
            });
            timer.setRepeats(false);
            timer.start();
        }

        // Label BestScore
//        JLabel lBestScore = new JLabel("Best Score");
//        lBestScore.setFont(new Font("Arial", Font.BOLD, 40));
//        lBestScore.setSize(lBestScore.getPreferredSize());
//        lBestScore.setLocation(Utils.getFrameX(lBestScore.getSize().width, Utils.frameWidth), (int) (0.15 * Utils.frameHeight));
        JLabelPersonalized lBestScore = new JLabelPersonalized("Best Score", new Font("Arial", Font.BOLD, 40));
        lBestScore.setLocation2(Utils.getFrameX(lBestScore.getSize().width, Utils.frameWidth), (int) (0.15 * Utils.frameHeight));
        profileGamePanel.add(lBestScore);

        // Label BestScoreJoueur
//        JLabel lBestScoreJoueur = new JLabel(String.valueOf(userGamesDetails.getBestScore()));
//        lBestScoreJoueur.setFont(new Font("Arial", Font.BOLD, 35));
//        lBestScoreJoueur.setSize(lBestScoreJoueur.getPreferredSize());
//        lBestScoreJoueur.setLocation(Utils.getFrameX(lBestScoreJoueur.getSize().width, Utils.frameWidth), (int) (0.25 * Utils.frameHeight));
        JLabelPersonalized lBestScoreJoueur = new JLabelPersonalized(String.valueOf(userGamesDetails.getBestScore()), new Font("Arial", Font.BOLD, 35));
        lBestScoreJoueur.setLocation2(Utils.getFrameX(lBestScoreJoueur.getSize().width, Utils.frameWidth), (int) (0.25 * Utils.frameHeight));
        profileGamePanel.add(lBestScoreJoueur);

        // Button Play
        JButtonPersonalized bPlay = new JButtonPersonalized("Play", new Font("Arial", Font.BOLD, 40), Utils.frameWidth, 0.45);
        bPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Game(username).setVisible(true);
            }
        });
        profileGamePanel.add(bPlay);

        // Label Profile
        JButtonProfileGame lProfile = new JButtonProfileGame("Profile", 0, Color.BLACK, true);
        profileGamePanel.add(lProfile);
        lProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Profile(username).setVisible(true);
            }
        });

        // Label Game
        JButtonProfileGame lGame = new JButtonProfileGame("Game", (int) (Utils.frameWidth * 0.5), Utils.blue, false);
        profileGamePanel.add(lGame);

        // Button Home
        int width = 50, height = 50;
//        ImageIcon icon = new ImageIcon(Objects.requireNonNull(Profile.class.getResource("/home.png")));
        ImageIcon icon = new ImageIcon("src/main/ressources/home.png");
        JButtonWithIcon bHome = new JButtonWithIcon(Utils.resizeImage(icon, width, height), new Rectangle((int) (Utils.frameWidth - width - 0.02 * Utils.frameWidth), (int) (0.01 * Utils.frameHeight), width, height));
        profileGamePanel.add(bHome);
        bHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home().setVisible(true);
            }
        });
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
