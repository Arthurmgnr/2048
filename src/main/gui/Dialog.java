package main.gui;

import main.gui.panels.DialogPanel;
import main.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.CountDownLatch;

//public class Dialog extends JFrame {
public class Dialog {
//    private final DialogPanel dialogPanel;
    private static String userChoice = "CLOSED";

//    public Dialog() {
    public static String showDialog(JFrame parent) {
        // Parametres de la fenetre
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        setBounds(Utils.frameX, Utils.frameY, Utils.frameWidth, Utils.frameHeight);
//        setTitle("2048");
//        setResizable(false);

        // Gestionnaire de placement
//        dialogPanel = new DialogPanel(this);
//        dialogPanel.setLayout(null);
//        dialogPanel.setFocusable(true);
//        setContentPane(dialogPanel);


        CountDownLatch latch = new CountDownLatch(1);
        JFrame dialogFrame = new JFrame("Confirm Action");
        dialogFrame.setSize(300, 150);
        dialogFrame.setLayout(new BorderLayout());
        dialogFrame.setLocationRelativeTo(parent);
        dialogFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Désactiver la fenêtre principale
        parent.setEnabled(false);

        // Message
        JLabel message = new JLabel("Do you want to continue?", JLabel.CENTER);
        dialogFrame.add(message, BorderLayout.CENTER);

        // Boutons Oui et Non
        JPanel buttonPanel = new JPanel();
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        // Action Oui
        yesButton.addActionListener(e -> {
            userChoice = "YES";
            dialogFrame.dispose();
            latch.countDown();
        });

        // Action Non
        noButton.addActionListener(e -> {
            userChoice = "NO";
            dialogFrame.dispose();
            latch.countDown();
        });

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        dialogFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Gérer la fermeture par la croix rouge
        dialogFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                userChoice = "CLOSED";
                dialogFrame.dispose();
                latch.countDown();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                parent.setEnabled(true); // Réactiver la fenêtre principale
            }
        });

        dialogFrame.setVisible(true);

        // Attente bloquante
        try {
            latch.await(); // Attendre que le verrou soit libéré
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        return userChoice; // Retourner le choix
    }

//    public void dessiner(Graphics g) {
//        g.setColor(Utils.white);
//        g.fillRect(0, 0, getWidth(), getHeight());
//    }
}
