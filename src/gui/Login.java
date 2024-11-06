package gui;

import panel.LoginPanel;
import utils.JButtonPersonalized;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

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

        // Label Register
        JLabel lLogin = new JLabel("Login");
        lLogin.setFont(new Font("Arial", Font.BOLD, 50));
        lLogin.setSize(lLogin.getPreferredSize());
        lLogin.setLocation(Utils.getFrameX(lLogin.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
        loginPanel.add(lLogin);
        System.out.println(lLogin.getFontMetrics(lLogin.getFont()).charWidth('W'));
        System.out.println(lLogin.getFontMetrics(lLogin.getFont()).getHeight());

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

        // Button Register
        JButtonPersonalized bLogin = new JButtonPersonalized("Login", new Font("Arial", Font.BOLD, 35), (int) (Utils.frameWidth * 1.5), 0.8);
        loginPanel.add(bLogin);
        bLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println();
            }
        });

        // Label Username
        JLabel lUsername = new JLabel("Username");
        lUsername.setFont(new Font("Arial", Font.BOLD, 20));
        lUsername.setSize(lUsername.getPreferredSize());
        lUsername.setLocation(Utils.getFrameX(lUsername.getSize().width, (int) (Utils.frameWidth * 0.5)), (int) (0.45 * Utils.frameHeight));
        loginPanel.add(lUsername);

        // TextField Username
        JTextField tfUsername = new JTextField();
        tfUsername.setFont(new Font("Arial", Font.PLAIN, 20));
        tfUsername.setSize(tfUsername.getFontMetrics(tfUsername.getFont()).charWidth('W') * 15, tfUsername.getPreferredSize().height);
        tfUsername.setLocation(Utils.getFrameX(tfUsername.getSize().width, (int) (Utils.frameWidth * 1.3)), (int) (0.45 * Utils.frameHeight));
        loginPanel.add(tfUsername);
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
