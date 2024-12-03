package main.gui;

import main.constants.MessageConstants;
import main.gui.panels.LoginPanel;
import main.services.LoginService;
import main.utils.JButtonPersonalized;
import main.utils.JLabelPersonalized;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(Utils.frameX, Utils.frameY, Utils.frameWidth, Utils.frameHeight);
        setTitle("2048");
        setResizable(false);

        // Gestionnaire de placement
        LoginPanel loginPanel = new LoginPanel(this);
        loginPanel.setLayout(null);
        loginPanel.setFocusable(true);
        setContentPane(loginPanel);

        // Label Login
//        JLabel lLogin = new JLabel("Login");
//        lLogin.setFont(new Font("Arial", Font.BOLD, 50));
//        lLogin.setSize(lLogin.getPreferredSize());
//        lLogin.setLocation(Utils.getFrameX(lLogin.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
        JLabelPersonalized lLogin = new JLabelPersonalized("Login", new Font("Arial", Font.BOLD, 50));
        lLogin.setLocation2(Utils.getFrameX(lLogin.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
        loginPanel.add(lLogin);
//        System.out.println(lLogin.getFontMetrics(lLogin.getFont()).charWidth('W'));
//        System.out.println(lLogin.getFontMetrics(lLogin.getFont()).getHeight());

        // Label Error
//        JLabel lError = new JLabel("Username");
//        lError.setFont(new Font("Arial", Font.BOLD, 14));
        JLabelPersonalized lError = new JLabelPersonalized("", new Font("Arial", Font.BOLD, 14));
        lError.setForeground(Color.RED);
        loginPanel.add(lError);

        // Label Username
//        JLabel lUsername = new JLabel("Username");
//        lUsername.setFont(new Font("Arial", Font.BOLD, 20));
//        lUsername.setSize(lUsername.getPreferredSize());
//        lUsername.setLocation(Utils.getFrameX(lUsername.getSize().width, (int) (Utils.frameWidth * 0.5)), (int) (0.45 * Utils.frameHeight));
        JLabelPersonalized lUsername = new JLabelPersonalized("Username", new Font("Arial", Font.BOLD, 20));
        lUsername.setLocation2(Utils.getFrameX(lUsername.getSize().width, (int) (0.5 * Utils.frameWidth)), (int) (0.45 * Utils.frameHeight));
        loginPanel.add(lUsername);

        // TextField Username
        JTextField tfUsername = new JTextField();
        tfUsername.setFont(new Font("Arial", Font.PLAIN, 20));
        tfUsername.setSize(tfUsername.getFontMetrics(tfUsername.getFont()).charWidth('W') * 15, tfUsername.getPreferredSize().height);
        tfUsername.setLocation(Utils.getFrameX(tfUsername.getSize().width, (int) (Utils.frameWidth * 1.3)), (int) (0.45 * Utils.frameHeight));
        loginPanel.add(tfUsername);

        // Button Back
        JButtonPersonalized bBack = new JButtonPersonalized("Back", new Font("Arial", Font.BOLD, 35), (int) (Utils.frameWidth * 0.5), 0.8);
        loginPanel.add(bBack);
        bBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home().setVisible(true);
            }
        });

        // Button Login
        JButtonPersonalized bLogin = new JButtonPersonalized("Login", new Font("Arial", Font.BOLD, 35), (int) (Utils.frameWidth * 1.5), 0.8);
        loginPanel.add(bLogin);
        bLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = tfUsername.getText();
                MessageConstants message = loginService.loginUser(username);
                if (message.getBool()) {
                    dispose();
                    new ProfileGame(username, false, true).setVisible(true);
                } else {
                    lError.setText2(message.getText());
                    lError.setLocation2(Utils.getFrameX(lError.getSize().width, (int) (1.3 * Utils.frameWidth)), (int) (0.5 * Utils.frameHeight));
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
