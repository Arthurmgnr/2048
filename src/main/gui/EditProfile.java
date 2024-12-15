package main.gui;

import main.entities.User;
import main.services.EditProfileService;
import main.utils.ImageIconPersonalized;
import main.utils.JButtonPersonalized;
import main.utils.JButtonWithIcon;
import main.utils.JLabelPersonalized;
import main.utils.Utils;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class EditProfile extends JFrame {
    private final EditProfileService editProfileService = new EditProfileService();
    private int actualAvatar;

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
        JLabelPersonalized lEditProfile = new JLabelPersonalized("Edit Profile", 40, true);

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
                "Avatar précédent",
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
                "Avatar suivant",
                true
        );
        bNextAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualAvatar = Utils.changeAvatar(1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
            }
        });

        // Back
        JButtonPersonalized bBack = new JButtonPersonalized("Back", "Retour à l'écran du profil");
        Dimension dimensionBack = new Dimension((int) (bBack.getPreferredSize().width * 1.4), (int) (bBack.getPreferredSize().height * 1.2));
        bBack.setBothSize(dimensionBack);
        bBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Profile(username).setVisible(true);
            }
        });

        // Save
        JButtonPersonalized bSave = new JButtonPersonalized("Save", "Sauvegarder les modifications");
        bSave.setBothSize(dimensionBack);
        bSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String avatar = listOfAvatar.get(actualAvatar);
                boolean message = editProfileService.updateUser(username, avatar);
                if (message) {
                    dispose();
                    new Profile(username).setVisible(true);
                }
            }
        });

        avatarPanel.add(lAvatar);
        avatarPanel.add(Box.createHorizontalStrut(50));
        avatarPanel.add(bPreviousAvatar);
        avatarPanel.add(Box.createHorizontalStrut(50));
        avatarPanel.add(lAvatarImage);
        avatarPanel.add(Box.createHorizontalStrut(50));
        avatarPanel.add(bNextAvatar);

        userPanel.add(lUsername);
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
