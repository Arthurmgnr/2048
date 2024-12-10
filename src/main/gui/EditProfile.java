package main.gui;

import main.constants.MessageConstants;
import main.entities.User;
import main.gui.panels.EditProfilePanel;
import main.gui.panels.ProfilePanel;
import main.services.EditProfileService;
import main.utils.JButtonPersonalized;
import main.utils.JButtonWithIcon;
import main.utils.JLabelPersonalized;
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
        JLabel lEditProfile = new JLabel("Edit Profile");
        lEditProfile.setFont(new Font("Arial", Font.BOLD, 40));
        lEditProfile.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label Username
        JLabel lUsername = new JLabel(username);
        lUsername.setFont(new Font("Arial", Font.BOLD, 30));
        lUsername.setAlignmentX(Component.CENTER_ALIGNMENT);


        // --------------------
        List<String> listOfAvatar = Utils.listOfAvatars();
        actualAvatar = listOfAvatar.indexOf(user.getAvatar());

        // Label Avatar
        JLabel lAvatar = new JLabel("Avatar");
        lAvatar.setFont(new Font("Arial", Font.BOLD, 20));
        lAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Image Avatar
        ImageIcon avatar = new ImageIcon(listOfAvatar.get(actualAvatar));
        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, 100, 100);
        JLabel lAvatarImage = new JLabel(resizedImageIcon);
        lAvatarImage.setBounds(Utils.getFrameX(100, (int) (1.25 * Utils.frameWidth)), (int) (0.35 * Utils.frameHeight), 100, 100);
        lAvatarImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Previous Avatar
        int widthPreviousAvatar = 50, heightPreviousAvatar = 50;
        ImageIcon iconPreviousAvatar = new ImageIcon("src/main/ressources/previous.png");
        JButtonWithIcon bPreviousAvatar = new JButtonWithIcon(Utils.resizeImage(iconPreviousAvatar, widthPreviousAvatar, heightPreviousAvatar), new Dimension(widthPreviousAvatar, heightPreviousAvatar));
        bPreviousAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualAvatar = Utils.changeAvatar(-1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
            }
        });
        bPreviousAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Next Avatar
        int widthNextAvatar = 50, heightNextAvatar = 50;
        ImageIcon iconNextAvatar = new ImageIcon("src/main/ressources/next.png");
        JButtonWithIcon bNextAvatar = new JButtonWithIcon(Utils.resizeImage(iconNextAvatar, widthNextAvatar, heightNextAvatar), new Dimension(widthNextAvatar, heightNextAvatar));
        bNextAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualAvatar = Utils.changeAvatar(1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
            }
        });
        bNextAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Register
        JButton bBack = new JButton("Back");
        bBack.setFont(new Font("Arial", Font.BOLD, 35));
        bBack.setBackground(Utils.blue);
        bBack.setForeground(Utils.white);
        bBack.setBorder(BorderFactory.createLineBorder(Utils.blue, 3));
        bBack.setOpaque(true);
        Dimension dimensionBack = new Dimension((int) (bBack.getPreferredSize().width * 1.4), (int) (bBack.getPreferredSize().height * 1.2));
        bBack.setMaximumSize(dimensionBack);
        bBack.setPreferredSize(dimensionBack);
        bBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Profile(username).setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                bBack.setForeground(Utils.blue);
                bBack.setBackground(Utils.white);
                bBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                bBack.setForeground(Utils.white);
                bBack.setBackground(Utils.blue);
                bBack.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        bBack.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Register
        JButton bSave = new JButton("Save");
        bSave.setFont(new Font("Arial", Font.BOLD, 35));
        bSave.setBackground(Utils.blue);
        bSave.setForeground(Utils.white);
        bSave.setBorder(BorderFactory.createLineBorder(Utils.blue, 3));
        bSave.setOpaque(true);
        bSave.setMaximumSize(dimensionBack);
        bSave.setPreferredSize(dimensionBack);
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
            @Override
            public void mouseEntered(MouseEvent e) {
                bSave.setForeground(Utils.blue);
                bSave.setBackground(Utils.white);
                bSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                bSave.setForeground(Utils.white);
                bSave.setBackground(Utils.blue);
                bSave.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        bSave.setAlignmentX(Component.CENTER_ALIGNMENT);

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

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
