package main.gui;

import main.constants.MessageConstants;
import main.services.RegisterService;
import main.utils.Utils;
import main.utils.ImageIconPersonalized;
import main.utils.JButtonPersonalized;
import main.utils.JButtonWithIcon;
import main.utils.JLabelPersonalized;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.Box;
import java.awt.Font;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

public class Register extends JFrame {
    private final RegisterService registerService = new RegisterService();
    private final Timer timer;
    private int actualAvatar = 0;

    public Register() {
        Utils.setFrameParameters(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new BoxLayout(avatarPanel, BoxLayout.X_AXIS));

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Label Avatar
        JLabelPersonalized lAvatar = new JLabelPersonalized("Avatar", 20, true);

        // List of avatars
        List<String> listOfAvatar = Utils.listOfAvatars();

        // Image Avatar
        ImageIconPersonalized lAvatarImage = new ImageIconPersonalized(listOfAvatar.get(actualAvatar), 100, true);

        // Previous Avatar
        JButtonWithIcon bPreviousAvatar = new JButtonWithIcon(
                "previous.png",
                "Avatar précédent",
                true
        );
        bPreviousAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        bNextAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        bNextAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualAvatar = Utils.changeAvatar(1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
            }
        });

        // Register
        JLabelPersonalized lRegister = new JLabelPersonalized("Register", 50, true);

        // Label Username
        JLabelPersonalized lUsername = new JLabelPersonalized("Username", 20, true);

        // Label Error
        JLabelPersonalized lError = new JLabelPersonalized("", 14, true);
        lError.setForeground(Color.RED);

        // TextField Username
        JTextField tfUsername = new JTextField();
        tfUsername.setFont(new Font("Arial", Font.PLAIN, 18));
        Dimension dimensionUsername = new Dimension(tfUsername.getFontMetrics(tfUsername.getFont()).charWidth('W') * 20, lUsername.getPreferredSize().height);
        tfUsername.setMaximumSize(dimensionUsername);
        tfUsername.setPreferredSize(dimensionUsername);
        tfUsername.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Register
        JButtonPersonalized bRegister = new JButtonPersonalized("Register", "Créer un compte");
        Dimension dimensionRegister = new Dimension((int) (bRegister.getPreferredSize().width * 1.4), (int) (bRegister.getPreferredSize().height * 1.2));
        bRegister.setBothSize(dimensionRegister);
        bRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = tfUsername.getText();
                Timestamp date = Timestamp.from(ZonedDateTime.now().toInstant());
                String avatar = listOfAvatar.get(actualAvatar);
                String language = "EN";
                MessageConstants message = registerService.registerUser(username, date, avatar, language);
                if (message.getBool()) {
                    dispose();
                    new ProfileGame(username, true, true).setVisible(true);
                } else {
                    lError.setText(message.getText());
                    timer.start();
                }
            }
        });

        timer = new Timer(5000, e -> lError.setText(""));
        timer.setRepeats(false);

        // Back
        JButtonPersonalized bBack = new JButtonPersonalized("Back", "Retour à l'écran d'accueil");
        bBack.setBothSize(dimensionRegister);
        bBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home().setVisible(true);
            }
        });

        avatarPanel.add(lAvatar);
        avatarPanel.add(Box.createHorizontalStrut(50));
        avatarPanel.add(bPreviousAvatar);
        avatarPanel.add(Box.createHorizontalStrut(50));
        avatarPanel.add(lAvatarImage);
        avatarPanel.add(Box.createHorizontalStrut(50));
        avatarPanel.add(bNextAvatar);

        usernamePanel.add(lUsername);
        usernamePanel.add(Box.createHorizontalStrut(50));
        usernamePanel.add(tfUsername);

        buttonPanel.add(bBack);
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(bRegister);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(lRegister);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(usernamePanel);
        mainPanel.add(lError);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(avatarPanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
    }
}
