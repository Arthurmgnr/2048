package main.gui;

import main.entities.User;
import main.entities.UserGamesDetails;
import main.services.ProfileService;
import main.utils.JButtonWithIcon;
import main.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Profile extends JFrame {
    private final ProfileService profileService = new ProfileService();

    public Profile(String username) {
        // Parametres de la fenetre
        Utils.setFrameParameters(this);

        // Get the userDetails
        User user = profileService.getUserDetails(username);

        // Get the userGamesDetails
        UserGamesDetails userGamesDetails = profileService.getUserGamesDetails(username);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.X_AXIS));

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

        JPanel userAvatar = new JPanel();
        userAvatar.setLayout(new BoxLayout(userAvatar, BoxLayout.Y_AXIS));

        JPanel userDetailsPanelCenter = new JPanel();
        userDetailsPanelCenter.setLayout(new BoxLayout(userDetailsPanelCenter, BoxLayout.X_AXIS));

        JPanel userDetailsPanel = new JPanel(new GridLayout(7, 2, 0, 20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Label Profile
        JLabel lProfile = new JLabel("Profile");
        lProfile.setFont(new Font("Arial", Font.BOLD, 40));
        lProfile.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        Dimension dimension = new Dimension((int) (Utils.frameWidth * 0.5), (int) (lProfile.getPreferredSize().height * 1.2));
        lProfile.setMaximumSize(dimension);
        lProfile.setPreferredSize(dimension);
        lProfile.setForeground(Utils.blue);
        lProfile.setHorizontalAlignment(SwingConstants.CENTER);
        lProfile.setVerticalAlignment(SwingConstants.CENTER);

        // Label Game
        JLabel lGame = new JLabel("Game");
        lGame.setFont(new Font("Arial", Font.BOLD, 40));
        lGame.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lGame.setMaximumSize(dimension);
        lGame.setPreferredSize(dimension);
        lGame.setForeground(Color.BLACK);
        lGame.setHorizontalAlignment(SwingConstants.CENTER);
        lGame.setVerticalAlignment(SwingConstants.CENTER);
        lGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lGame.setBackground(Color.LIGHT_GRAY);
                lGame.setOpaque(true);
                lGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lGame.setBackground(Color.WHITE);
                lGame.setOpaque(false);
                lGame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new ProfileGame(username, false, false).setVisible(true);
            }
        });

        // Label Username
        JLabel lUsername = new JLabel(username);
        lUsername.setFont(new Font("Arial", Font.BOLD, 30));
        lUsername.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Avatar image
        ImageIcon avatar = new ImageIcon(user.getAvatar());
        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, 100, 100);
        JLabel lAvatarImage = new JLabel(resizedImageIcon);
        lAvatarImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button Edit
        int width = 50, height = 50;
        ImageIcon icon = new ImageIcon("src/main/ressources/edit.png");
        JButtonWithIcon bEdit = new JButtonWithIcon(Utils.resizeImage(icon, width, height), new Dimension(width, height));
        bEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new EditProfile(username).setVisible(true);
            }
        });

        // Date
        JLabel lDate = new JLabel("Date de création");
        lDate.setFont(new Font("Arial", Font.BOLD, 20));

        LocalDateTime date = user.getDateTime().toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);
        String formattedDate = date.format(formatter);
        String result = formattedDate.substring(0, 3) + formattedDate.substring(3).substring(0, 1).toUpperCase() + formattedDate.substring(4);
        JLabel lDateValue = new JLabel(result);
        lDateValue.setFont(new Font("Arial", Font.BOLD, 20));

        // Lang
        JLabel lLang = new JLabel("Lang");
        lLang.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel lLangValue = new JLabel(user.getLang());
        lLangValue.setFont(new Font("Arial", Font.BOLD, 20));

        // Best Score
        JLabel lBestScore = new JLabel("Best Score");
        lBestScore.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel lBestScoreValue = new JLabel(String.valueOf(userGamesDetails.getBestScore()));
        lBestScoreValue.setFont(new Font("Arial", Font.BOLD, 20));

        // Nb Parties
        JLabel lNbParties = new JLabel("Nb Parties");
        lNbParties.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel lNbPartiesValue = new JLabel(String.valueOf(userGamesDetails.getNbParties()));
        lNbPartiesValue.setFont(new Font("Arial", Font.BOLD, 20));

        // Score Moyen
        JLabel lScoreMoyen = new JLabel("Score Moyen");
        lScoreMoyen.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel lScoreMoyenValue = new JLabel(String.valueOf(userGamesDetails.getScoreMoyen()));
        lScoreMoyenValue.setFont(new Font("Arial", Font.BOLD, 20));

        // Nb Coups Moyen
        JLabel lNbCoupsMoyen = new JLabel("Nb Coups Moyen");
        lNbCoupsMoyen.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel lNbCoupsMoyenValue = new JLabel(String.valueOf(userGamesDetails.getNbCoupsMoyen()));
        lNbCoupsMoyenValue.setFont(new Font("Arial", Font.BOLD, 20));

        // Nb Parties Gagnees
        JLabel lNbPartiesGagnees = new JLabel("Nb Parties Gagnees");
        lNbPartiesGagnees.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel lNbPartiesGagneesValue = new JLabel(String.valueOf(userGamesDetails.getNbPartiesGagnees()));
        lNbPartiesGagneesValue.setFont(new Font("Arial", Font.BOLD, 20));

        editPanel.add(Box.createHorizontalGlue());
        editPanel.add(bEdit);

        userAvatar.add(lUsername);
        userAvatar.add(Box.createVerticalStrut(25));
        userAvatar.add(lAvatarImage);

        userDetailsPanel.add(lDate); userDetailsPanel.add(lDateValue);
        userDetailsPanel.add(lLang); userDetailsPanel.add(lLangValue);
        userDetailsPanel.add(lBestScore); userDetailsPanel.add(lBestScoreValue);
        userDetailsPanel.add(lNbParties); userDetailsPanel.add(lNbPartiesValue);
        userDetailsPanel.add(lScoreMoyen); userDetailsPanel.add(lScoreMoyenValue);
        userDetailsPanel.add(lNbCoupsMoyen); userDetailsPanel.add(lNbCoupsMoyenValue);
        userDetailsPanel.add(lNbPartiesGagnees); userDetailsPanel.add(lNbPartiesGagneesValue);

        userDetailsPanelCenter.add(Box.createHorizontalGlue());
        userDetailsPanelCenter.add(userDetailsPanel);
        userDetailsPanelCenter.add(Box.createHorizontalGlue());

        userPanel.add(userAvatar);
        userPanel.add(Box.createVerticalStrut(100));
        userPanel.add(userDetailsPanelCenter);
        userPanel.add(Box.createVerticalGlue());

        buttonPanel.add(lProfile);
        buttonPanel.add(lGame);

        mainPanel.add(editPanel, BorderLayout.NORTH);
        mainPanel.add(userPanel);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
