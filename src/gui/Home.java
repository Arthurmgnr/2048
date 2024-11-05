package gui;

import panel.GamePanel;
import panel.HomePanel;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class Home extends JFrame {
    private HomePanel homePanel;
    private JLabel l2048;
    private JButton bLogin;
    private JLabel lRegister;

    public Home() {
        // Parametres de la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(Utils.frameX, Utils.frameY, Utils.frameWidth, Utils.frameHeight);
        setTitle("2048");
        setResizable(false);

        // Gestionnaire de placement
        homePanel = new HomePanel(this);
        homePanel.setLayout(null);
        homePanel.setFocusable(true);
        setContentPane(homePanel);

        // Label 2048
        l2048 = new JLabel("2048");
        l2048.setFont(new Font("Arial", Font.BOLD, 50));
        l2048.setSize(l2048.getPreferredSize());
        l2048.setLocation(Utils.getFrameX(l2048.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
        homePanel.add(l2048);

        // Button Login
        bLogin = new JButton("Login");
        bLogin.setFont(new Font("Arial", Font.BOLD, 35));
        bLogin.setSize(bLogin.getPreferredSize());
        bLogin.setSize((int) (bLogin.getPreferredSize().width * 1.5), (int) (bLogin.getPreferredSize().height * 1.5));
        bLogin.setLocation(Utils.getFrameX(bLogin.getSize().width, Utils.frameWidth), (int) (0.45 * Utils.frameHeight));
        homePanel.add(bLogin);
        bLogin.setBackground(Utils.blue);
        bLogin.setForeground(Utils.white);
        bLogin.setBorder(BorderFactory.createLineBorder(Utils.blue, 3));
        bLogin.setOpaque(true);
        bLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("");
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
                System.out.println("");
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
