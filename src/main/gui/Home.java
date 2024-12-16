package main.gui;

import main.utils.JButtonPersonalized;
import main.utils.JLabelPersonalized;
import main.utils.TranslationManager;
import main.utils.Utils;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class Home extends JFrame {

    public Home() {
        Utils.setFrameParameters(this);

        // Set the language to 'en' to ensure problems
        TranslationManager.setLanguage("en");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // 2048
        JLabel l2048 = new JLabelPersonalized("2048", 50, true);

        // Login
        JButtonPersonalized bLogin = new JButtonPersonalized(
                TranslationManager.get("home.login.button"),
                TranslationManager.get("home.login.tooltip"));
        Dimension dimensionBLogin = new Dimension((int) (bLogin.getPreferredSize().width * 1.4), (int) (bLogin.getPreferredSize().height * 1.2));
        bLogin.setBothSize(dimensionBLogin);
        bLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Login().setVisible(true);
            }
        });

        // Register
        JLabelPersonalized lRegister = new JLabelPersonalized(TranslationManager.get("home.register.button"), 30, true);
        lRegister.setToolTipText(TranslationManager.get("home.register.tooltip"));
        lRegister.setForeground(Utils.blue);
        Font font = lRegister.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
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
}
