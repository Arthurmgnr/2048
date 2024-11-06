package gui;

import panel.HomePanel;
import utils.JButtonPersonalized;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class Home extends JFrame {
    private final JLabel lRegister;

    public Home() {
        // Parametres de la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(Utils.frameX, Utils.frameY, Utils.frameWidth, Utils.frameHeight);
        setTitle("2048");
        setResizable(false);

        // Gestionnaire de placement
        HomePanel homePanel = new HomePanel(this);
        homePanel.setLayout(null);
        homePanel.setFocusable(true);
        setContentPane(homePanel);

        // Label 2048
        JLabel l2048 = new JLabel("2048");
        l2048.setFont(new Font("Arial", Font.BOLD, 50));
        l2048.setSize(l2048.getPreferredSize());
        l2048.setLocation(Utils.getFrameX(l2048.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
        homePanel.add(l2048);

        // Button Login
        JButtonPersonalized bLogin = new JButtonPersonalized("Login", new Font("Arial", Font.BOLD, 35), Utils.frameWidth, 0.45);
        homePanel.add(bLogin);
        bLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Login().setVisible(true);
            }
        });

        // Label Register
        lRegister = new JLabel("Register");
        lRegister.setFont(new Font("Arial", Font.BOLD, 30));
        lRegister.setSize(lRegister.getPreferredSize());
        lRegister.setLocation(Utils.getFrameX(lRegister.getSize().width, Utils.frameWidth), (int) (0.6 * Utils.frameHeight));
        homePanel.add(lRegister);
        lRegister.setForeground(Utils.blue);
        Font font = lRegister.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes()); // (Map<TextAttribute, Object>) font.getAttributes();
        lRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Register().setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                lRegister.setFont(font.deriveFont(attributes));
                lRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                attributes.remove(TextAttribute.UNDERLINE);
                lRegister.setFont(font.deriveFont(attributes));
                lRegister.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
