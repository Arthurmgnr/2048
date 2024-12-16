package main.gui;

import main.entities.User;
import main.entities.UserGamesDetails;
import main.services.ProfileService;
import main.utils.*;

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

    // Essayer de mettre une colonne vide entre les colonnes du GridLayout

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
        JButtonProfileGame lProfile = new JButtonProfileGame(
                TranslationManager.get("profile.profile.button"), false, ""
        );

        // Label Game
        JButtonProfileGame lGame = new JButtonProfileGame(
                TranslationManager.get("profile.game.button"),
                true,
                TranslationManager.get("profile.game.tooltip")
        );
        lGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new ProfileGame(username, false, false).setVisible(true);
            }
        });

        // Set sizes of the buttons
        Dimension maxDimension = Utils.getMaxDimension(
                new Dimension((int) (Utils.frameWidth * 0.5), (int) (lProfile.getPreferredSize().height * 1.2)),
                new Dimension((int) (Utils.frameWidth * 0.5), (int) (lGame.getPreferredSize().height * 1.2))
        );
        lProfile.setBothSize(maxDimension);
        lGame.setBothSize(maxDimension);

        // Label Username
        JLabelPersonalized lUsername = new JLabelPersonalized(username, 30, true);

        // Avatar image
        ImageIconPersonalized lAvatarImage = new ImageIconPersonalized(user.getAvatar(), 100, true);

        // Button Edit
        JButtonWithIcon bEdit = new JButtonWithIcon(
                "edit.png",
                TranslationManager.get("profile.edit.tooltip"),
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
        JLabelPersonalized lDate = new JLabelPersonalized(
                TranslationManager.get("profile.creationDate.label"), 20, false
        );

        LocalDateTime date = user.getDateTime().toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);
        String formattedDate = date.format(formatter);
        String result = formattedDate.substring(0, 3) + formattedDate.substring(3).substring(0, 1).toUpperCase() + formattedDate.substring(4);
        JLabelPersonalized lDateValue = new JLabelPersonalized(result, 20, false);

        // Lang
        JLabelPersonalized lLang = new JLabelPersonalized(
                TranslationManager.get("profile.lang.label"), 20, false
        );
        JLabelPersonalized lLangValue = new JLabelPersonalized(user.getLang(), 20, false);

        // Best Score
        JLabelPersonalized lBestScore = new JLabelPersonalized(
                TranslationManager.get("profile.bestScore.label"), 20, false
        );
        JLabelPersonalized lBestScoreValue = new JLabelPersonalized(String.valueOf(userGamesDetails.getBestScore()), 20, false);

        // Score Moyen
        JLabelPersonalized lScoreMoyen = new JLabelPersonalized(
                TranslationManager.get("profile.averageScore.label"), 20, false
        );
        JLabelPersonalized lScoreMoyenValue = new JLabelPersonalized(String.valueOf(userGamesDetails.getScoreMoyen()), 20, false);

        // Nb Coups Moyen
        JLabelPersonalized lNbCoupsMoyen = new JLabelPersonalized(
                TranslationManager.get("profile.averageNumberMoves.label"), 20, false
        );
        JLabelPersonalized lNbCoupsMoyenValue = new JLabelPersonalized(String.valueOf(userGamesDetails.getNbCoupsMoyen()), 20, false);

        // Nb Parties
        JLabelPersonalized lNbParties = new JLabelPersonalized(
                TranslationManager.get("profile.numberGames.label"), 20, false
        );
        JLabelPersonalized lNbPartiesValue = new JLabelPersonalized(String.valueOf(userGamesDetails.getNbParties()), 20, false);

        // Nb Parties Gagnees
        JLabelPersonalized lNbPartiesGagnees = new JLabelPersonalized(
                TranslationManager.get("profile.numberGamesWon.label"), 20, false
        );
        JLabelPersonalized lNbPartiesGagneesValue = new JLabelPersonalized(String.valueOf(userGamesDetails.getNbPartiesGagnees()), 20, false);

        editPanel.add(Box.createHorizontalGlue());
        editPanel.add(bEdit);

        userAvatar.add(lUsername);
        userAvatar.add(Box.createVerticalStrut(25));
        userAvatar.add(lAvatarImage);

        userDetailsPanel.add(lDate); userDetailsPanel.add(lDateValue);
        userDetailsPanel.add(lLang); userDetailsPanel.add(lLangValue);
        userDetailsPanel.add(lBestScore); userDetailsPanel.add(lBestScoreValue);
        userDetailsPanel.add(lScoreMoyen); userDetailsPanel.add(lScoreMoyenValue);
        userDetailsPanel.add(lNbCoupsMoyen); userDetailsPanel.add(lNbCoupsMoyenValue);
        userDetailsPanel.add(lNbParties); userDetailsPanel.add(lNbPartiesValue);
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
