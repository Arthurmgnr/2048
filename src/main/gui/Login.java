package main.gui;

import main.constants.MessageConstants;
import main.services.LoginService;
import main.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
    private final LoginService loginService = new LoginService();
    private final Timer timer;

    public Login() {
        // Parametres de la fenetre
        Utils.setFrameParameters(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Login
        JLabel lLogin = new JLabel("Login");
        lLogin.setFont(new Font("Arial", Font.BOLD, 50));
        lLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        // Login
        JButton bLogin = new JButton("Login");
        bLogin.setFont(new Font("Arial", Font.BOLD, 35));
        bLogin.setBackground(Utils.blue);
        bLogin.setForeground(Utils.white);
        bLogin.setBorder(BorderFactory.createLineBorder(Utils.blue, 3));
        bLogin.setOpaque(true);
        Dimension dimensionLogin = new Dimension((int) (bLogin.getPreferredSize().width * 1.4), (int) (bLogin.getPreferredSize().height * 1.2));
        bLogin.setMaximumSize(dimensionLogin);
        bLogin.setPreferredSize(dimensionLogin);
        bLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = tfUsername.getText();
                MessageConstants message = loginService.loginUser(username);
                if (message.getBool()) {
                    dispose();
                    new ProfileGame(username, false, true).setVisible(true);
                } else {
                    lError.setText(message.getText());
                    timer.start();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                bLogin.setForeground(Utils.blue);
                bLogin.setBackground(Utils.white);
                bLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                bLogin.setForeground(Utils.white);
                bLogin.setBackground(Utils.blue);
                bLogin.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        bLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        timer = new Timer(5000, e -> lError.setText(""));
        timer.setRepeats(false);

        // Back
        JButton bBack = new JButton("Back");
        bBack.setFont(new Font("Arial", Font.BOLD, 35));
        bBack.setBackground(Utils.blue);
        bBack.setForeground(Utils.white);
        bBack.setBorder(BorderFactory.createLineBorder(Utils.blue, 3));
        bBack.setOpaque(true);
        bBack.setMaximumSize(dimensionLogin);
        bBack.setPreferredSize(dimensionLogin);
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

        usernamePanel.add(lUsername);
        usernamePanel.add(Box.createHorizontalStrut(50));
        usernamePanel.add(tfUsername);

        buttonPanel.add(bBack);
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(bLogin);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(lLogin);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(usernamePanel);
        mainPanel.add(lError);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);


        // Gestionnaire de placement
//        LoginPanel loginPanel = new LoginPanel(this);
//        loginPanel.setLayout(null);
//        loginPanel.setFocusable(true);
//        setContentPane(loginPanel);
//
//        // Label Login
//        JLabelPersonalized lLogin = new JLabelPersonalized("Login", new Font("Arial", Font.BOLD, 50));
//        lLogin.setLocation2(Utils.getFrameX(lLogin.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
//        loginPanel.add(lLogin);
//
//        // Label Error
//        JLabelPersonalized lError = new JLabelPersonalized("", new Font("Arial", Font.BOLD, 14));
//        lError.setForeground(Color.RED);
//        loginPanel.add(lError);
//
//        // Label Username
//        JLabelPersonalized lUsername = new JLabelPersonalized("Username", new Font("Arial", Font.BOLD, 20));
//        lUsername.setLocation2(Utils.getFrameX(lUsername.getSize().width, (int) (0.5 * Utils.frameWidth)), (int) (0.45 * Utils.frameHeight));
//        loginPanel.add(lUsername);
//
//        // TextField Username
//        JTextField tfUsername = new JTextField();
//        tfUsername.setFont(new Font("Arial", Font.PLAIN, 20));
//        tfUsername.setSize(tfUsername.getFontMetrics(tfUsername.getFont()).charWidth('W') * 15, tfUsername.getPreferredSize().height);
//        tfUsername.setLocation(Utils.getFrameX(tfUsername.getSize().width, (int) (Utils.frameWidth * 1.3)), (int) (0.45 * Utils.frameHeight));
//        loginPanel.add(tfUsername);
//
//        // Button Back
//        JButtonPersonalized bBack = new JButtonPersonalized("Back", new Font("Arial", Font.BOLD, 35));
//        bBack.setLocation2(Utils.getFrameX(bBack.getSize().width, (int) (0.5 * Utils.frameWidth)), (int) (0.8 * Utils.frameHeight));
//        loginPanel.add(bBack);
//        bBack.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                dispose();
//                new Home().setVisible(true);
//            }
//        });
//
//        // Button Login
//        JButtonPersonalized bLogin = new JButtonPersonalized("Login", new Font("Arial", Font.BOLD, 35));
//        bLogin.setLocation2(Utils.getFrameX(bLogin.getSize().width, (int) (1.5 * Utils.frameWidth)), (int) (0.8 * Utils.frameHeight));
//        loginPanel.add(bLogin);
//        bLogin.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                String username = tfUsername.getText();
//                MessageConstants message = loginService.loginUser(username);
//                if (message.getBool()) {
//                    dispose();
//                    new ProfileGame(username, false, true).setVisible(true);
//                } else {
//                    lError.setText2(message.getText());
//                    lError.setLocation2(Utils.getFrameX(lError.getSize().width, (int) (1.3 * Utils.frameWidth)), (int) (0.5 * Utils.frameHeight));
//                    timer.start();
//                }
//            }
//        });
//
//        timer = new Timer(5000, e -> lError.setText(""));
//        timer.setRepeats(false);
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
