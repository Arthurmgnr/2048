package main.gui;

import main.entities.User;
import main.entities.UserGamesDetails;
import main.gui.panels.ProfilePanel;
import main.services.ProfileService;
import main.utils.JButtonProfileGame;
import main.utils.JButtonWithIcon;
import main.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Profile extends JFrame {
    private final ProfileService profileService = new ProfileService();

    public Profile(String username) {
        // Parametres de la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(Utils.frameX, Utils.frameY, Utils.frameWidth, Utils.frameHeight);
        setTitle("2048");
        setResizable(false);

        // Gestionnaire de placement
        ProfilePanel profilePanel = new ProfilePanel(this);
        profilePanel.setLayout(null);
        profilePanel.setFocusable(true);
        setContentPane(profilePanel);

        // Get the userDetails
        User user = profileService.getUserDetails(username);

        // Get the userGamesDetails
        UserGamesDetails userGamesDetails = profileService.getUserGamesDetails(username);

        // Label Username
        JLabel lUsername = new JLabel(username);
        lUsername.setFont(new Font("Arial", Font.BOLD, 30));
        lUsername.setSize(lUsername.getPreferredSize());
        lUsername.setLocation(Utils.getFrameX(lUsername.getSize().width, Utils.frameWidth), (int) (0.1 * Utils.frameHeight));
        profilePanel.add(lUsername);

        // Label Profile
        JButtonProfileGame lProfile = new JButtonProfileGame("Profile", 0, Utils.blue, false);
        profilePanel.add(lProfile);

        // Label Game
        JButtonProfileGame lGame = new JButtonProfileGame("Game", (int) (Utils.frameWidth * 0.5), Color.BLACK, true);
        profilePanel.add(lGame);
        lGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new ProfileGame(username, false, false).setVisible(true);
            }
        });

        // Button Edit
        int widthEdit = 50, heightEdit = 50;
        ImageIcon iconEdit = new ImageIcon(Objects.requireNonNull(Profile.class.getResource("/edit.png")));
        JButtonWithIcon bEdit = new JButtonWithIcon(Utils.resizeImage(iconEdit, widthEdit, heightEdit), new Rectangle((int) (Utils.frameWidth - widthEdit - 0.01 * Utils.frameWidth), (int) (0.01 * Utils.frameHeight), widthEdit, heightEdit));
        profilePanel.add(bEdit);
        bEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new EditProfile(username).setVisible(true);
            }
        });

        // Button Home
        int widthHome = 50, heightHome = 50;
        ImageIcon iconHome = new ImageIcon(Objects.requireNonNull(Profile.class.getResource("/home.png")));
        JButtonWithIcon bHome = new JButtonWithIcon(Utils.resizeImage(iconHome, widthHome, heightHome), new Rectangle((int) (Utils.frameWidth - widthHome - 0.01 * Utils.frameWidth), (int) (0.01 * Utils.frameHeight), widthHome, heightHome));
        profilePanel.add(bHome);
        bHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Home().setVisible(true);
            }
        });

        // --------------------
        // Avatar image
        ImageIcon avatar = new ImageIcon(user.getAvatar());
        ImageIcon resizedImageIcon = Utils.resizeImage(avatar, 100, 100);
        JLabel lAvatarImage = new JLabel(resizedImageIcon);
        lAvatarImage.setBounds((Utils.frameWidth - resizedImageIcon.getIconWidth()) / 2, 200, 100, 100);
        profilePanel.add(lAvatarImage);

        // Date
        JLabel lDate = new JLabel("Date: " + String.valueOf(user.getDateTime()));
        lDate.setFont(new Font("Arial", Font.BOLD, 20));
        lDate.setSize(lDate.getPreferredSize());
        lDate.setLocation(Utils.getFrameX(lDate.getSize().width, Utils.frameWidth), (int) (0.45 * Utils.frameHeight));
        profilePanel.add(lDate);

        // Lang
        JLabel lLang = new JLabel("Lang: " + user.getLang());
        lLang.setFont(new Font("Arial", Font.BOLD, 20));
        lLang.setSize(lLang.getPreferredSize());
        lLang.setLocation(Utils.getFrameX(lLang.getSize().width, Utils.frameWidth), (int) (0.5 * Utils.frameHeight));
        profilePanel.add(lLang);

        // --------------------
        // BestScore
        JLabel lBestScore = new JLabel("Best score: " + userGamesDetails.getBestScore());
        lBestScore.setFont(new Font("Arial", Font.BOLD, 20));
        lBestScore.setSize(lBestScore.getPreferredSize());
        lBestScore.setLocation(Utils.getFrameX(lBestScore.getSize().width, Utils.frameWidth), (int) (0.6 * Utils.frameHeight));
        profilePanel.add(lBestScore);

        // nbParties
        JLabel lNbParties = new JLabel("Nb parties: " + userGamesDetails.getNbParties());
        lNbParties.setFont(new Font("Arial", Font.BOLD, 20));
        lNbParties.setSize(lNbParties.getPreferredSize());
        lNbParties.setLocation(Utils.getFrameX(lNbParties.getSize().width, Utils.frameWidth), (int) (0.65 * Utils.frameHeight));
        profilePanel.add(lNbParties);

        // scoreMoyen
        JLabel lScoreMoyen = new JLabel("Score moyen: " + userGamesDetails.getScoreMoyen());
        lScoreMoyen.setFont(new Font("Arial", Font.BOLD, 20));
        lScoreMoyen.setSize(lScoreMoyen.getPreferredSize());
        lScoreMoyen.setLocation(Utils.getFrameX(lScoreMoyen.getSize().width, Utils.frameWidth), (int) (0.7 * Utils.frameHeight));
        profilePanel.add(lScoreMoyen);

        // nbCoupsMoyen
        JLabel lNbCoupsMoyen = new JLabel("Nb coups moyen: " + userGamesDetails.getNbCoupsMoyen());
        lNbCoupsMoyen.setFont(new Font("Arial", Font.BOLD, 20));
        lNbCoupsMoyen.setSize(lNbCoupsMoyen.getPreferredSize());
        lNbCoupsMoyen.setLocation(Utils.getFrameX(lNbCoupsMoyen.getSize().width, Utils.frameWidth), (int) (0.75 * Utils.frameHeight));
        profilePanel.add(lNbCoupsMoyen);

        // nbPartiesGagnees
        JLabel lNbPartiesGagnees = new JLabel("Nb parties gagnees: " + userGamesDetails.getNbPartiesGagnees());
        lNbPartiesGagnees.setFont(new Font("Arial", Font.BOLD, 20));
        lNbPartiesGagnees.setSize(lNbPartiesGagnees.getPreferredSize());
        lNbPartiesGagnees.setLocation(Utils.getFrameX(lNbPartiesGagnees.getSize().width, Utils.frameWidth), (int) (0.8 * Utils.frameHeight));
        profilePanel.add(lNbPartiesGagnees);
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
