package main.gui;

import main.constants.MessageConstants;
import main.services.LoginService;
import main.utils.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Timer;
import javax.swing.Box;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
    private final LoginService loginService = new LoginService();
    private final Timer timer;

    public Login() {
        // Parametres de la fenetre
        Utils.setFrameParameters(this);

        // Set the language to 'en' to ensure problems
        TranslationManager.setLanguage("en");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Login
        JLabelPersonalized lLogin = new JLabelPersonalized(
                TranslationManager.get("login.login.title"), 50, true
        );

        // Label Username
        JLabelPersonalized lUsername = new JLabelPersonalized(
                TranslationManager.get("login.username.label"), 20, true
        );

        // Label Error
        JLabelPersonalized lError = new JLabelPersonalized("", 14, true);
        lError.setForeground(Color.RED);

        // TextField Username
        JTextFieldPersonalized tfUsername = new JTextFieldPersonalized(lUsername);

        // Login
        JButtonPersonalized bLogin = new JButtonPersonalized(
                TranslationManager.get("login.login.button"), TranslationManager.get("login.login.tooltip")
        );
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
        });

        timer = new Timer(5000, e -> lError.setText(""));
        timer.setRepeats(false);

        // Back
        JButtonPersonalized bBack = new JButtonPersonalized(
                TranslationManager.get("login.back.button"), TranslationManager.get("login.back.tooltip")
        );
        bBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home().setVisible(true);
            }
        });

        // Set sizes of the buttons
        Dimension maxDimension = Utils.getMaxDimension(
                new Dimension((int) (bLogin.getPreferredSize().width * 1.4), (int) (bLogin.getPreferredSize().height * 1.2)),
                new Dimension((int) (bBack.getPreferredSize().width * 1.4), (int) (bBack.getPreferredSize().height * 1.2))
        );
        bLogin.setBothSize(maxDimension);
        bBack.setBothSize(maxDimension);

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
    }
}
