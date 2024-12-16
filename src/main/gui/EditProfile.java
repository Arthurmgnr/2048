package main.gui;

import main.constants.LanguageConstants;
import main.entities.User;
import main.services.EditProfileService;
import main.utils.*;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class EditProfile extends JFrame {
    private final EditProfileService editProfileService = new EditProfileService();
    private int actualAvatar;
    private String lang;

    public EditProfile(String username) {
        Utils.setFrameParameters(this);

        // Get userDetails
        User user = editProfileService.getUserDetails(username);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new BoxLayout(avatarPanel, BoxLayout.X_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // EditProfile
        JLabelPersonalized lEditProfile = new JLabelPersonalized(
                TranslationManager.get("editProfile.title"), 40, true
        );

        // Label Username
        JLabelPersonalized lUsername = new JLabelPersonalized(username, 30, true);

        // --------------------
        List<String> listOfAvatar = Utils.listOfAvatars();
        actualAvatar = listOfAvatar.indexOf(user.getAvatar());

        // Label Avatar
        JLabelPersonalized lAvatar = new JLabelPersonalized("Avatar", 20, true);

        // Image Avatar
        ImageIconPersonalized lAvatarImage = new ImageIconPersonalized(listOfAvatar.get(actualAvatar), 100, true);

        // Previous Avatar
        JButtonWithIcon bPreviousAvatar = new JButtonWithIcon(
                "previous.png",
                TranslationManager.get("editProfile.previousAvatar.tooltip"),
                true
        );
        bPreviousAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualAvatar = Utils.changeAvatar(-1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
            }
        });

        // Next Avatar
        JButtonWithIcon bNextAvatar = new JButtonWithIcon(
                "next.png",
                TranslationManager.get("editProfile.nextAvatar.tooltip"),
                true
        );
        bNextAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualAvatar = Utils.changeAvatar(1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
            }
        });

        // Back
        JButtonPersonalized bBack = new JButtonPersonalized(
                TranslationManager.get("editProfile.back.button"), TranslationManager.get("editProfile.back.tooltip")
        );
        bBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Profile(username).setVisible(true);
            }
        });

        // Save
        JButtonPersonalized bSave = new JButtonPersonalized(
                TranslationManager.get("editProfile.save.button"), TranslationManager.get("editProfile.save.tooltip")
        );
        bSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String avatar = listOfAvatar.get(actualAvatar);
                String language = lang;
                boolean message = editProfileService.updateUser(username, language, avatar);
                if (message) {
                    TranslationManager.setLanguage(language);
                    dispose();
                    new Profile(username).setVisible(true);
                }
            }
        });

        // Set sized of the buttons
        Dimension maxDimension = Utils.getMaxDimension(
                new Dimension((int) (bBack.getPreferredSize().width * 1.4), (int) (bBack.getPreferredSize().height * 1.2)),
                new Dimension((int) (bSave.getPreferredSize().width * 1.4), (int) (bSave.getPreferredSize().height * 1.2))
        );
        bBack.setBothSize(maxDimension);
        bSave.setBothSize(maxDimension);

        // Language
        JPanel comboBoxLanguagePanel = new JPanel();
        comboBoxLanguagePanel.setLayout(new BoxLayout(comboBoxLanguagePanel, BoxLayout.X_AXIS));

        JLabelPersonalized lLanguage = new JLabelPersonalized(
                TranslationManager.get("register.language.label"), 20, true
        );
        JComboBox<LanguageConstants> comboBoxLanguage = new JComboBox<>(LanguageConstants.values());
        Dimension dimension = lUsername.getPreferredSize();
        dimension.width *= 2;
        comboBoxLanguage.setMaximumSize(dimension);
        comboBoxLanguage.setPreferredSize(dimension);
        comboBoxLanguage.setSelectedItem(LanguageConstants.getLangItem(user.getLang()));
        lang = user.getLang();
        comboBoxLanguage.addActionListener(e -> {
            LanguageConstants selectedLang = (LanguageConstants) comboBoxLanguage.getSelectedItem();
            lang = selectedLang.getLang();
        });

        comboBoxLanguagePanel.add(lLanguage);
        comboBoxLanguagePanel.add(Box.createHorizontalStrut(50));
        comboBoxLanguagePanel.add(comboBoxLanguage);

        avatarPanel.add(lAvatar);
        avatarPanel.add(Box.createHorizontalStrut(50));
        avatarPanel.add(bPreviousAvatar);
        avatarPanel.add(Box.createHorizontalStrut(50));
        avatarPanel.add(lAvatarImage);
        avatarPanel.add(Box.createHorizontalStrut(50));
        avatarPanel.add(bNextAvatar);

        userPanel.add(lUsername);
        userPanel.add(Box.createVerticalGlue());
        userPanel.add(comboBoxLanguagePanel);
        userPanel.add(Box.createVerticalGlue());
        userPanel.add(avatarPanel);

        buttonPanel.add(bBack);
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(bSave);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(lEditProfile);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(userPanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
    }
}
