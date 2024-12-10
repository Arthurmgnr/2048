package main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Dialog extends JFrame {
    private String userChoice = "CLOSED"; // Valeur par défaut

    // Interface pour gérer le résultat
    public interface Callback {
        void onResult(String choice);
    }

    // Constructeur
    public Dialog(JFrame parent, Callback callback) {
        super("2048");

        setSize(300, 150);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Désactiver la fenêtre principale
        parent.setEnabled(false);

        // Message
        JLabel message = new JLabel("Do you want to continue?", JLabel.CENTER);
        add(message, BorderLayout.CENTER);

        // Boutons Oui et Non
        JPanel buttonPanel = new JPanel();
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        // Action Oui
        yesButton.addActionListener(e -> {
            userChoice = "YES";
            callback.onResult(userChoice); // Transmettre le résultat via le callback
            dispose();
        });

        // Action Non
        noButton.addActionListener(e -> {
            userChoice = "NO";
            callback.onResult(userChoice); // Transmettre le résultat via le callback
            dispose();
        });

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Gérer la fermeture par la croix rouge
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                userChoice = "CLOSED";
                callback.onResult(userChoice); // Transmettre le résultat via le callback
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                parent.setEnabled(true); // Réactiver la fenêtre principale
            }
        });

        setVisible(true);
    }
}
