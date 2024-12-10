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
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
