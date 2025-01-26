package main.gui;

import main.constants.LanguageConstants;
import main.constants.MessageConstants;
import main.services.RegisterService;
import main.utils.Utils;
import main.utils.TranslationManager;
import main.utils.JLabelPersonalized;
import main.utils.ImageIconPersonalized;
import main.utils.JButtonWithIcon;
import main.utils.JTextFieldPersonalized;
import main.utils.JButtonPersonalized;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

public class Register extends JFrame {
    // Permet d'appeler le Service associe
    private final RegisterService registerService = new RegisterService();
    // Timer pour gerer le temps d'apparition du message d'erreur
    private final Timer timer;
    // Indice pour gerer la liste des avatar
    private int actualAvatar = 0;
    // Permet de changer la langue du joueur
    private String lang;

    public Register() {
        Utils.setFrameParameters(this);

        // Set the language to 'en' to ensure problems
        TranslationManager.setLanguage("en");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new BoxLayout(avatarPanel, BoxLayout.X_AXIS));

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Label Avatar
        JLabelPersonalized lAvatar = new JLabelPersonalized("Avatar", 20, true);

        // List of avatars
        List<String> listOfAvatar = Utils.listOfAvatars();

        // Image Avatar
        ImageIconPersonalized lAvatarImage = new ImageIconPersonalized(listOfAvatar.get(actualAvatar), 100, true);

        // Previous Avatar
        JButtonWithIcon bPreviousAvatar = new JButtonWithIcon(
                "previous.png",
                TranslationManager.get("register.previousAvatar.tooltip"),
                true
        );
        bPreviousAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualAvatar = Utils.changeAvatar(-1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
            }
        });

        // Next Avatar
        JButtonWithIcon bNextAvatar = new JButtonWithIcon(
                "next.png",
                TranslationManager.get("register.nextAvatar.tooltip"),
                true
        );
        bNextAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualAvatar = Utils.changeAvatar(1, actualAvatar, listOfAvatar, lAvatarImage, 100, 100);
            }
        });

        // Register
        JLabelPersonalized lRegister = new JLabelPersonalized(
                TranslationManager.get("register.register.title"), 50, true
        );

        // Label Username
        JLabelPersonalized lUsername = new JLabelPersonalized(
                TranslationManager.get("register.username.label"), 20, true
        );

        // Label Error
        JLabelPersonalized lError = new JLabelPersonalized("", 14, true);
        lError.setForeground(Color.RED);

        // TextField Username
        JTextFieldPersonalized tfUsername = new JTextFieldPersonalized(lUsername);

        // Register
        JButtonPersonalized bRegister = new JButtonPersonalized(
                TranslationManager.get("register.register.button"),
                TranslationManager.get("register.register.tooltip")
        );
        bRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = tfUsername.getText();
                Timestamp date = Timestamp.from(ZonedDateTime.now().toInstant());
                String avatar = listOfAvatar.get(actualAvatar);
                String language = lang;
                MessageConstants message = registerService.registerUser(username, date, avatar, language);
                if (message.getBool()) {
                    TranslationManager.setLanguage(language);
                    dispose();
                    new ProfileGame(username, true, true).setVisible(true);
                } else {
                    lError.setText(message.getText());
                    timer.start();
                }
            }
        });

        // Le timer s'affiche pendant 5s et ensuite on efface le texte
        timer = new Timer(5000, e -> lError.setText(""));
        timer.setRepeats(false);

        // Back
        JButtonPersonalized bBack = new JButtonPersonalized(
                TranslationManager.get("register.back.button"),
                TranslationManager.get("register.back.tooltip")
        );
        bBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home().setVisible(true);
            }
        });

        // Set the size of buttons with the max size
        Dimension maxDimension = Utils.getMaxDimension(
                new Dimension((int) (bRegister.getPreferredSize().width * 1.4), (int) (bRegister.getPreferredSize().height * 1.2)),
                new Dimension((int) (bBack.getPreferredSize().width * 1.4), (int) (bBack.getPreferredSize().height * 1.2))
        );
        bRegister.setBothSize(maxDimension);
        bBack.setBothSize(maxDimension);

        // Permet de modifier la langue du joueur avec un menu deroulant
        JPanel comboBoxLanguagePanel = new JPanel();
        comboBoxLanguagePanel.setLayout(new BoxLayout(comboBoxLanguagePanel, BoxLayout.X_AXIS));

        JLabelPersonalized lLanguage = new JLabelPersonalized(
                TranslationManager.get("register.language.label"), 20, true
        );
        JComboBox<LanguageConstants> comboBoxLanguage = new JComboBox<>(LanguageConstants.values());
        Dimension dimension = lUsername.getPreferredSize();
        dimension.width *= 2;
        comboBoxLanguage.setMaximumSize(dimension);
        comboBoxLanguage.setPreferredSize(dimension);
        lang = LanguageConstants.values()[0].getLang();
        comboBoxLanguage.addActionListener(e -> {
            LanguageConstants selectedLang = (LanguageConstants) comboBoxLanguage.getSelectedItem();
            lang = selectedLang.getLang();
        });

        comboBoxLanguagePanel.add(lLanguage);
        comboBoxLanguagePanel.add(Box.createHorizontalStrut(50));
        comboBoxLanguagePanel.add(comboBoxLanguage);

        avatarPanel.add(lAvatar);
        avatarPanel.add(Box.createHorizontalStrut(50));
        avatarPanel.add(bPreviousAvatar);
        avatarPanel.add(Box.createHorizontalStrut(50));
        avatarPanel.add(lAvatarImage);
        avatarPanel.add(Box.createHorizontalStrut(50));
        avatarPanel.add(bNextAvatar);

        usernamePanel.add(lUsername);
        usernamePanel.add(Box.createHorizontalStrut(50));
        usernamePanel.add(tfUsername);

        buttonPanel.add(bBack);
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(bRegister);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(lRegister);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(usernamePanel);
        mainPanel.add(lError);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(comboBoxLanguagePanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(avatarPanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
    }
}
