package gui;

import panel.RegisterPanel;
import utils.JButtonPersonalized;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register extends JFrame {


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
        JLabel lRegister = new JLabel("Register");
        lRegister.setFont(new Font("Arial", Font.BOLD, 50));
        lRegister.setSize(lRegister.getPreferredSize());
        lRegister.setLocation(Utils.getFrameX(lRegister.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
        registerPanel.add(lRegister);
        System.out.println(lRegister.getFontMetrics(lRegister.getFont()).charWidth('W'));
        System.out.println(lRegister.getFontMetrics(lRegister.getFont()).getHeight());

        // Button Back
        JButtonPersonalized bBack = new JButtonPersonalized("Back", new Font("Arial", Font.BOLD, 35), (int) (Utils.frameWidth * 0.5), 0.8);
        registerPanel.add(bBack);
        bBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home().setVisible(true);
            }
        });

        // Button Register
        JButtonPersonalized bRegister = new JButtonPersonalized("Register", new Font("Arial", Font.BOLD, 35), (int) (Utils.frameWidth * 1.5), 0.8);
        registerPanel.add(bRegister);
        bRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println();
            }
        });

        // Label Username
        JLabel lUsername = new JLabel("Username");
        lUsername.setFont(new Font("Arial", Font.BOLD, 20));
        lUsername.setSize(lUsername.getPreferredSize());
        lUsername.setLocation(Utils.getFrameX(lUsername.getSize().width, (int) (Utils.frameWidth * 0.5)), (int) (0.25 * Utils.frameHeight));
        registerPanel.add(lUsername);

        // TextField Username
        JTextField tfUsername = new JTextField();
        tfUsername.setFont(new Font("Arial", Font.PLAIN, 20));
        tfUsername.setSize(tfUsername.getFontMetrics(tfUsername.getFont()).charWidth('W') * 15, tfUsername.getPreferredSize().height);
        tfUsername.setLocation(Utils.getFrameX(tfUsername.getSize().width, (int) (Utils.frameWidth * 1.3)), (int) (0.25 * Utils.frameHeight));
        registerPanel.add(tfUsername);
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
