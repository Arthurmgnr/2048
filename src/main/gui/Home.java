package main.gui;

import main.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class Home extends JFrame {

    public Home() {
        // Parametres de la fenetre
        Utils.setFrameParameters(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // 2048
        JLabel l2048 = new JLabel("2048");
        l2048.setFont(new Font("Arial", Font.BOLD, 50));
        l2048.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login
        JButton bLogin = new JButton("Login");
        bLogin.setFont(new Font("Arial", Font.BOLD, 35));
        bLogin.setBackground(Utils.blue);
        bLogin.setForeground(Utils.white);
        bLogin.setBorder(BorderFactory.createLineBorder(Utils.blue, 3));
        bLogin.setOpaque(true);
        Dimension dimension = new Dimension((int) (bLogin.getPreferredSize().width * 1.4), (int) (bLogin.getPreferredSize().height * 1.2));
        bLogin.setMaximumSize(dimension);
        bLogin.setPreferredSize(dimension);
        bLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Login().setVisible(true);
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

        // Register
        JLabel lRegister = new JLabel("Register");
        lRegister.setFont(new Font("Arial", Font.BOLD, 30));
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
        lRegister.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(l2048);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(bLogin);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(lRegister);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
