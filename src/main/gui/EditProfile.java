package main.gui;

import main.constants.MessageConstants;
import main.entities.User;
import main.gui.panels.EditProfilePanel;
import main.gui.panels.ProfilePanel;
import main.services.EditProfileService;
import main.utils.JButtonPersonalized;
import main.utils.JButtonWithIcon;
import main.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class EditProfile extends JFrame {
    private final EditProfileService editProfileService = new EditProfileService();
    private int actualAvatar;

    public EditProfile(String username) {
        // Parametres de la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(Utils.frameX, Utils.frameY, Utils.frameWidth, Utils.frameHeight);
        setTitle("2048");
        setResizable(false);

        // Gestionnaire de placement
        EditProfilePanel editProfilePanel = new EditProfilePanel(this);
        editProfilePanel.setLayout(null);
        editProfilePanel.setFocusable(true);
        setContentPane(editProfilePanel);

        // Get userDetails
        User user = editProfileService.getUserDetails(username);

        // Label EditProfile
        JLabel lEditProfile = new JLabel("Edit Profile");
        lEditProfile.setFont(new Font("Arial", Font.BOLD, 40));
        lEditProfile.setSize(lEditProfile.getPreferredSize());
        lEditProfile.setLocation(Utils.getFrameX(lEditProfile.getSize().width, Utils.frameWidth), (int) (0.05 * Utils.frameHeight));
        editProfilePanel.add(lEditProfile);

        // Label Username
        JLabel lUsername = new JLabel(username);
        lUsername.setFont(new Font("Arial", Font.BOLD, 30));
        lUsername.setSize(lUsername.getPreferredSize());
        lUsername.setLocation(Utils.getFrameX(lUsername.getSize().width, Utils.frameWidth), (int) (0.15 * Utils.frameHeight));
        editProfilePanel.add(lUsername);

        // --------------------
        List<String> listOfAvatar = Utils.listOfAvatars();
        actualAvatar = listOfAvatar.indexOf(user.getAvatar());

        // Avatar Label
        JLabel lAvatar = new JLabel("Avatar");
        lAvatar.setFont(new Font("Arial", Font.BOLD, 20));
        lAvatar.setSize(lAvatar.getPreferredSize());
        lAvatar.setLocation(Utils.getFrameX(lAvatar.getSize().width, (int) (Utils.frameWidth * 0.5)), (int) (0.35 * Utils.frameHeight));
        editProfilePanel.add(lAvatar);

        // Image Avatar
        ImageIcon avatar = new ImageIcon(listOfAvatar.get(actualAvatar));
        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, 100, 100);
        JLabel lAvatarImage = new JLabel(resizedImageIcon);
        lAvatarImage.setBounds(400, 250, 100, 100);
        editProfilePanel.add(lAvatarImage);

        // Button Previous Avatar
        int widthPreviousAvatar = 50, heightPreviousAvatar = 50;
        ImageIcon iconPreviousAvatar = new ImageIcon(Objects.requireNonNull(Profile.class.getResource("/previous.png")));
        JButtonWithIcon bPreviousAvatar = new JButtonWithIcon(Utils.resizeImage(iconPreviousAvatar, widthPreviousAvatar, heightPreviousAvatar), new Rectangle((int) (0.4 * Utils.frameWidth), (int) (0.35 * Utils.frameHeight), widthPreviousAvatar, heightPreviousAvatar));
        editProfilePanel.add(bPreviousAvatar);
        bPreviousAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualAvatar = Utils.changeAvatar(-1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
            }
        });

        // Button Next Avatar
        int widthNextAvatar = 50, heightNextAvatar = 50;
        ImageIcon iconNextAvatar = new ImageIcon(Objects.requireNonNull(Profile.class.getResource("/next.png")));
        JButtonWithIcon bNextAvatar = new JButtonWithIcon(Utils.resizeImage(iconNextAvatar, widthNextAvatar, heightNextAvatar), new Rectangle((int) (0.8 * Utils.frameWidth), (int) (0.35 * Utils.frameHeight), widthNextAvatar, heightNextAvatar));
        editProfilePanel.add(bNextAvatar);
        bNextAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualAvatar = Utils.changeAvatar(1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
            }
        });

        // Button Back
        JButtonPersonalized bBack = new JButtonPersonalized("Back", new Font("Arial", Font.BOLD, 35), (int) (Utils.frameWidth * 0.5), 0.8);
        editProfilePanel.add(bBack);
        bBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Profile(username).setVisible(true);
            }
        });

        // Button Save
        JButtonPersonalized bSave = new JButtonPersonalized("Save", new Font("Arial", Font.BOLD, 35), (int) (Utils.frameWidth * 1.5), 0.8);
        editProfilePanel.add(bSave);
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
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
