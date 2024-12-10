package main.gui;

import main.constants.MessageConstants;
import main.services.RegisterService;
import main.utils.JButtonWithIcon;
import main.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

public class Register extends JFrame {
    private final RegisterService registerService = new RegisterService();
    private final Timer timer;
    private int actualAvatar;

    public Register() {
        // Parametres de la fenetre
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
        JLabel lAvatar = new JLabel("Avatar");
        lAvatar.setFont(new Font("Arial", Font.BOLD, 20));
        lAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // List of avatars
        List<String> listOfAvatar = Utils.listOfAvatars();
        actualAvatar = 0;

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
        JLabel lRegister = new JLabel("Register");
        lRegister.setFont(new Font("Arial", Font.BOLD, 50));
        lRegister.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label Username
        JLabel lUsername = new JLabel("Username");
        lUsername.setFont(new Font("Arial", Font.BOLD, 20));
        lUsername.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label Error
        JLabel lError = new JLabel("");
        lError.setFont(new Font("Arial", Font.BOLD, 14));
        lError.setForeground(Color.RED);
        lError.setAlignmentX(Component.CENTER_ALIGNMENT);

        // TextField Username
        JTextField tfUsername = new JTextField();
        tfUsername.setFont(new Font("Arial", Font.PLAIN, 18));
        Dimension dimensionUsername = new Dimension(tfUsername.getFontMetrics(tfUsername.getFont()).charWidth('W') * 20, lUsername.getPreferredSize().height);
        tfUsername.setMaximumSize(dimensionUsername);
        tfUsername.setPreferredSize(dimensionUsername);
        tfUsername.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Register
        JButton bRegister = new JButton("Register");
        bRegister.setFont(new Font("Arial", Font.BOLD, 35));
        bRegister.setBackground(Utils.blue);
        bRegister.setForeground(Utils.white);
        bRegister.setBorder(BorderFactory.createLineBorder(Utils.blue, 3));
        bRegister.setOpaque(true);
        Dimension dimensionRegister = new Dimension((int) (bRegister.getPreferredSize().width * 1.4), (int) (bRegister.getPreferredSize().height * 1.2));
        bRegister.setMaximumSize(dimensionRegister);
        bRegister.setPreferredSize(dimensionRegister);
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
            @Override
            public void mouseEntered(MouseEvent e) {
                bRegister.setForeground(Utils.blue);
                bRegister.setBackground(Utils.white);
                bRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                bRegister.setForeground(Utils.white);
                bRegister.setBackground(Utils.blue);
                bRegister.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        bRegister.setAlignmentX(Component.CENTER_ALIGNMENT);

        timer = new Timer(5000, e -> lError.setText(""));
        timer.setRepeats(false);

        // Back
        JButton bBack = new JButton("Back");
        bBack.setFont(new Font("Arial", Font.BOLD, 35));
        bBack.setBackground(Utils.blue);
        bBack.setForeground(Utils.white);
        bBack.setBorder(BorderFactory.createLineBorder(Utils.blue, 3));
        bBack.setOpaque(true);
        bBack.setMaximumSize(dimensionRegister);
        bBack.setPreferredSize(dimensionRegister);
        bBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home().setVisible(true);
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



        // Gestionnaire de placement
//        RegisterPanel registerPanel = new RegisterPanel(this);
//        registerPanel.setLayout(null);
//        registerPanel.setFocusable(true);
//        setContentPane(registerPanel);
//
//        // Label Register
//        JLabelPersonalized lRegister = new JLabelPersonalized("Register", new Font("Arial", Font.BOLD, 50));
//        lRegister.setLocation2(Utils.getFrameX(lRegister.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
//        registerPanel.add(lRegister);
//
//        // Label Username
//        JLabelPersonalized lUsername = new JLabelPersonalized("Username", new Font("Arial", Font.BOLD, 20));
//        lUsername.setLocation2(Utils.getFrameX(lUsername.getSize().width, (int) (0.5 * Utils.frameWidth)), (int) (0.25 * Utils.frameHeight));
//        registerPanel.add(lUsername);
//
//        // Label Error
//        JLabelPersonalized lError = new JLabelPersonalized("", new Font("Arial", Font.BOLD, 14));
//        lError.setForeground(Color.RED);
//        registerPanel.add(lError);
//
//        // TextField Username
//        JTextField tfUsername = new JTextField();
//        tfUsername.setFont(new Font("Arial", Font.PLAIN, 18));
//        tfUsername.setSize(tfUsername.getFontMetrics(tfUsername.getFont()).charWidth('W') * 20, lUsername.getPreferredSize().height);
//        tfUsername.setLocation(Utils.getFrameX(tfUsername.getSize().width, (int) (1.3 * Utils.frameWidth)), (int) (0.25 * Utils.frameHeight));
//        registerPanel.add(tfUsername);
//
//
//        // Button Back
//        JButtonPersonalized bBack = new JButtonPersonalized("Back", new Font("Arial", Font.BOLD, 35));
//        bBack.setLocation2(Utils.getFrameX(bBack.getSize().width, (int) (0.5 * Utils.frameHeight)), (int) (0.8 * Utils.frameHeight));
//        registerPanel.add(bBack);
//        bBack.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                dispose();
//                new Home().setVisible(true);
//            }
//        });
//
//        // --------------------
//        List<String> listOfAvatar = Utils.listOfAvatars();
//        actualAvatar = 0;
//
//        // Avatar Label
//        JLabelPersonalized lAvatar = new JLabelPersonalized("Avatar", new Font("Arial", Font.BOLD, 20));
//        lAvatar.setLocation2(Utils.getFrameX(lAvatar.getSize().width, (int) (0.5 * Utils.frameWidth)), (int) (0.35 * Utils.frameHeight));
//        registerPanel.add(lAvatar);
//
//        // Image Avatar
//        ImageIcon avatar = new ImageIcon(listOfAvatar.get(actualAvatar));
//        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, 100, 100);
//        JLabel lAvatarImage = new JLabel(resizedImageIcon);
//        lAvatarImage.setBounds(Utils.getFrameX(100, (int) (1.25 * Utils.frameWidth)), (int) (0.35 * Utils.frameHeight), 100, 100);
//        registerPanel.add(lAvatarImage);
//
//        // Button Previous Avatar
//        int widthPreviousAvatar = 50, heightPreviousAvatar = 50;
////        ImageIcon iconPreviousAvatar = new ImageIcon(Objects.requireNonNull(Profile.class.getResource("/previous.png")));
//        ImageIcon iconPreviousAvatar = new ImageIcon("src/main/ressources/previous.png");
//        JButtonWithIcon bPreviousAvatar = new JButtonWithIcon(Utils.resizeImage(iconPreviousAvatar, widthPreviousAvatar, heightPreviousAvatar), new Rectangle((int) (0.4 * Utils.frameWidth), (int) (0.35 * Utils.frameHeight), widthPreviousAvatar, heightPreviousAvatar));
//        registerPanel.add(bPreviousAvatar);
//        bPreviousAvatar.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                actualAvatar = Utils.changeAvatar(-1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
//            }
//        });
//
//        // Button Next Avatar
//        int widthNextAvatar = 50, heightNextAvatar = 50;
////        ImageIcon iconNextAvatar = new ImageIcon(Objects.requireNonNull(Profile.class.getResource("/next.png")));
//        ImageIcon iconNextAvatar = new ImageIcon("src/main/ressources/next.png");
//        JButtonWithIcon bNextAvatar = new JButtonWithIcon(Utils.resizeImage(iconNextAvatar, widthNextAvatar, heightNextAvatar), new Rectangle((int) (0.8 * Utils.frameWidth), (int) (0.35 * Utils.frameHeight), widthNextAvatar, heightNextAvatar));
//        registerPanel.add(bNextAvatar);
//        bNextAvatar.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                actualAvatar = Utils.changeAvatar(1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
//            }
//        });
//
//        // ---------------------
//        // Button Register
//        JButtonPersonalized bRegister = new JButtonPersonalized("Register", new Font("Arial", Font.BOLD, 35));
//        bRegister.setLocation2(Utils.getFrameX(bRegister.getSize().width, (int) (1.5 * Utils.frameWidth)), (int) (0.8 * Utils.frameHeight));
//        registerPanel.add(bRegister);
//        bRegister.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                String username = tfUsername.getText();
//                Timestamp date = Timestamp.from(ZonedDateTime.now().toInstant());
//                String avatar = listOfAvatar.get(actualAvatar);
//                String language = "EN";
//                MessageConstants message = registerService.registerUser(username, date, avatar, language);
//                if (message.getBool()) {
//                    dispose();
//                    new ProfileGame(username, true, true).setVisible(true);
//                } else {
//                    lError.setText2(message.getText());
//                    lError.setLocation2(Utils.getFrameX(lError.getSize().width, (int) (1.3 * Utils.frameWidth)), (int) (0.3 * Utils.frameHeight));
//                    timer.start();
//                }
//            }
//        });


//        timer = new Timer(5000, e -> lError.setText(""));
//        timer.setRepeats(false);
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
