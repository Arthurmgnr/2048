package main.gui;

import main.constants.MessageConstants;
import main.gui.panels.RegisterPanel;
import main.services.RegisterService;
import main.utils.JButtonPersonalized;
import main.utils.JButtonWithIcon;
import main.utils.JLabelPersonalized;
import main.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.List;

public class Register extends JFrame {
    private final RegisterService registerService = new RegisterService();
    private final Timer timer;
    private int actualAvatar;

    public Register() {
        // Parametres de la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(Utils.frameX, Utils.frameY, Utils.frameWidth, Utils.frameHeight);
        setTitle("2048");
        setResizable(false);

        // Gestionnaire de placement
        RegisterPanel registerPanel = new RegisterPanel(this);
        registerPanel.setLayout(null);
        registerPanel.setFocusable(true);
        setContentPane(registerPanel);

        // Label Register
//        JLabel lRegister = new JLabel("Register");
//        lRegister.setFont(new Font("Arial", Font.BOLD, 50));
//        lRegister.setSize(new Dimension(lRegister.getFontMetrics(lRegister.getFont()).charWidth('R') * 8, lRegister.getFontMetrics(lRegister.getFont()).getHeight()));
//        lRegister.setHorizontalAlignment(JLabel.CENTER);
//        lRegister.setLocation(Utils.getFrameX(lRegister.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
        JLabelPersonalized lRegister = new JLabelPersonalized("Register", new Font("Arial", Font.BOLD, 50));
        lRegister.setLocation2(Utils.getFrameX(lRegister.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
        registerPanel.add(lRegister);
//        System.out.println(lRegister.getFontMetrics(lRegister.getFont()).charWidth('W'));
//        System.out.println(lRegister.getFontMetrics(lRegister.getFont()).getHeight());

        // Label Username
//        JLabel lUsername = new JLabel("Username");
//        lUsername.setFont(new Font("Arial", Font.BOLD, 20));
//        lUsername.setSize(lUsername.getPreferredSize());
//        lUsername.setLocation(Utils.getFrameX(lUsername.getSize().width, (int) (Utils.frameWidth * 0.5)), (int) (0.25 * Utils.frameHeight));
        JLabelPersonalized lUsername = new JLabelPersonalized("Username", new Font("Arial", Font.BOLD, 20));
        lUsername.setLocation2(Utils.getFrameX(lUsername.getSize().width, (int) (0.5 * Utils.frameWidth)), (int) (0.25 * Utils.frameHeight));
        registerPanel.add(lUsername);

        // Label Error
//        JLabel lError = new JLabel();
//        lError.setFont(new Font("Arial", Font.BOLD, 14));
        JLabelPersonalized lError = new JLabelPersonalized("", new Font("Arial", Font.BOLD, 14));
        lError.setForeground(Color.RED);
        registerPanel.add(lError);

        // TextField Username
        JTextField tfUsername = new JTextField();
        tfUsername.setFont(new Font("Arial", Font.PLAIN, 18));
        tfUsername.setSize(tfUsername.getFontMetrics(tfUsername.getFont()).charWidth('W') * 20, lUsername.getPreferredSize().height);
        tfUsername.setLocation(Utils.getFrameX(tfUsername.getSize().width, (int) (1.3 * Utils.frameWidth)), (int) (0.25 * Utils.frameHeight));
        registerPanel.add(tfUsername);

//        System.out.println(lUsername.getLocation().x + " " + lUsername.getLocation().y + " " + lUsername.getSize().height + " " + lUsername.getSize().width);
//        System.out.println(tfUsername.getLocation().x + " " + tfUsername.getLocation().y + " " + tfUsername.getSize().height + " " + tfUsername.getSize().width);

        // Button Back
        JButtonPersonalized bBack = new JButtonPersonalized("Back", new Font("Arial", Font.BOLD, 35), (int) (0.5 * Utils.frameWidth), 0.8);
        registerPanel.add(bBack);
        bBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home().setVisible(true);
            }
        });

        // --------------------
        List<String> listOfAvatar = Utils.listOfAvatars();
        actualAvatar = 0;

        // Avatar Label
//        JLabel lAvatar = new JLabel("Avatar");
//        lAvatar.setFont(new Font("Arial", Font.BOLD, 20));
//        lAvatar.setSize(lAvatar.getPreferredSize());
//        lAvatar.setLocation(Utils.getFrameX(lAvatar.getSize().width, (int) (Utils.frameWidth * 0.5)), (int) (0.35 * Utils.frameHeight));
        JLabelPersonalized lAvatar = new JLabelPersonalized("Avatar", new Font("Arial", Font.BOLD, 20));
        lAvatar.setLocation2(Utils.getFrameX(lAvatar.getSize().width, (int) (0.5 * Utils.frameWidth)), (int) (0.35 * Utils.frameHeight));
        registerPanel.add(lAvatar);

        // Image Avatar
        ImageIcon avatar = new ImageIcon(listOfAvatar.get(actualAvatar));
        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, 100, 100);
        JLabel lAvatarImage = new JLabel(resizedImageIcon);
        lAvatarImage.setBounds(Utils.getFrameX(100, (int) (1.25 * Utils.frameWidth)), (int) (0.35 * Utils.frameHeight), 100, 100);
        registerPanel.add(lAvatarImage);

        // Button Previous Avatar
        int widthPreviousAvatar = 50, heightPreviousAvatar = 50;
//        ImageIcon iconPreviousAvatar = new ImageIcon(Objects.requireNonNull(Profile.class.getResource("/previous.png")));
        ImageIcon iconPreviousAvatar = new ImageIcon("src/main/ressources/previous.png");
        JButtonWithIcon bPreviousAvatar = new JButtonWithIcon(Utils.resizeImage(iconPreviousAvatar, widthPreviousAvatar, heightPreviousAvatar), new Rectangle((int) (0.4 * Utils.frameWidth), (int) (0.35 * Utils.frameHeight), widthPreviousAvatar, heightPreviousAvatar));
        registerPanel.add(bPreviousAvatar);
        bPreviousAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualAvatar = Utils.changeAvatar(-1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
            }
        });

        // Button Next Avatar
        int widthNextAvatar = 50, heightNextAvatar = 50;
//        ImageIcon iconNextAvatar = new ImageIcon(Objects.requireNonNull(Profile.class.getResource("/next.png")));
        ImageIcon iconNextAvatar = new ImageIcon("src/main/ressources/next.png");
        JButtonWithIcon bNextAvatar = new JButtonWithIcon(Utils.resizeImage(iconNextAvatar, widthNextAvatar, heightNextAvatar), new Rectangle((int) (0.8 * Utils.frameWidth), (int) (0.35 * Utils.frameHeight), widthNextAvatar, heightNextAvatar));
        registerPanel.add(bNextAvatar);
        bNextAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualAvatar = Utils.changeAvatar(1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
            }
        });

        // ---------------------
        // Button Register
        JButtonPersonalized bRegister = new JButtonPersonalized("Register", new Font("Arial", Font.BOLD, 35), (int) (Utils.frameWidth * 1.5), 0.8);
        registerPanel.add(bRegister);
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
                    lError.setText2(message.getText());
                    lError.setLocation2(Utils.getFrameX(lError.getSize().width, (int) (1.3 * Utils.frameWidth)), (int) (0.3 * Utils.frameHeight));
                    timer.start();
                }
            }
        });


        timer = new Timer(5000, e -> lError.setText(""));
        timer.setRepeats(false);
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
