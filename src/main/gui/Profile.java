package main.gui;

import main.entities.User;
import main.entities.UserGamesDetails;
import main.services.ProfileService;
import main.utils.ImageIconPersonalized;
import main.utils.JButtonProfileGame;
import main.utils.JButtonWithIcon;
import main.utils.JLabelPersonalized;
import main.utils.Utils;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Profile extends JFrame {

    public Profile(String username) {
        Utils.setFrameParameters(this);

        // To get data from database
        ProfileService profileService = new ProfileService();

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
        JButtonProfileGame lProfile = new JButtonProfileGame("Profile", false, "");
        Dimension dimensionLProfile = new Dimension((int) (Utils.frameWidth * 0.5), (int) (lProfile.getPreferredSize().height * 1.2));
        lProfile.setBothSize(dimensionLProfile);

        // Label Game
        JButtonProfileGame lGame = new JButtonProfileGame("Game", true, "Aller a l'écran de jeu");
        lGame.setBothSize(dimensionLProfile);
        lGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new ProfileGame(username, false, false).setVisible(true);
            }
        });

        // Label Username
        JLabelPersonalized lUsername = new JLabelPersonalized(username, 30, true);

        // Avatar image
        ImageIconPersonalized lAvatarImage = new ImageIconPersonalized(user.getAvatar(), 100, true);

        // Button Edit
        JButtonWithIcon bEdit = new JButtonWithIcon(
                "edit.png",
                "Modifier les informations du profil",
                false
        );
        bEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new EditProfile(username).setVisible(true);
            }
        });

        // Date
        JLabelPersonalized lDate = new JLabelPersonalized("Date de création", 20, false);

        LocalDateTime date = user.getDateTime().toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);
        String formattedDate = date.format(formatter);
        String result = formattedDate.substring(0, 3) + formattedDate.substring(3).substring(0, 1).toUpperCase() + formattedDate.substring(4);
        JLabelPersonalized lDateValue = new JLabelPersonalized(result, 20, false);

        // Lang
        JLabelPersonalized lLang = new JLabelPersonalized("Lang", 20, false);
        JLabelPersonalized lLangValue = new JLabelPersonalized(user.getLang(), 20, false);

        // Best Score
        JLabelPersonalized lBestScore = new JLabelPersonalized("Best Score", 20, false);
        JLabelPersonalized lBestScoreValue = new JLabelPersonalized(String.valueOf(userGamesDetails.getBestScore()), 20, false);

        // Nb Parties
        JLabelPersonalized lNbParties = new JLabelPersonalized("Nb Parties", 20, false);
        JLabelPersonalized lNbPartiesValue = new JLabelPersonalized(String.valueOf(userGamesDetails.getNbParties()), 20, false);

        // Score Moyen
        JLabelPersonalized lScoreMoyen = new JLabelPersonalized("Score Moyen", 20, false);
        JLabelPersonalized lScoreMoyenValue = new JLabelPersonalized(String.valueOf(userGamesDetails.getScoreMoyen()), 20, false);

        // Nb Coups Moyen
        JLabelPersonalized lNbCoupsMoyen = new JLabelPersonalized("Nb Coups Moyen", 20, false);
        JLabelPersonalized lNbCoupsMoyenValue = new JLabelPersonalized(String.valueOf(userGamesDetails.getNbCoupsMoyen()), 20, false);

        // Nb Parties Gagnees
        JLabelPersonalized lNbPartiesGagnees = new JLabelPersonalized("Nb Parties Gagnees", 20, false);
        JLabelPersonalized lNbPartiesGagneesValue = new JLabelPersonalized(String.valueOf(userGamesDetails.getNbPartiesGagnees()), 20, false);

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
}
