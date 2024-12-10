package main.gui;

import main.gui.panels.GamePanel;
import main.gui.panels.JDialogPersonalizedPanel;
import main.utils.JButtonPersonalized;
import main.utils.JLabelPersonalized;
import main.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JDialogPersonalized extends JDialog {
    public static String choice;

    public JDialogPersonalized(JFrame parent, String message, String[] options) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize((int) (0.5 * Utils.frameWidth), (int) (0.5 * Utils.frameHeight));
        setLocationRelativeTo(parent);
        setTitle("2048");
        setResizable(false);
        setModal(true);

        JDialogPersonalizedPanel buttonPanel = new JDialogPersonalizedPanel(this);
        buttonPanel.setLayout(null);
        buttonPanel.setFocusable(true);
        setContentPane(buttonPanel);

        // JLabel message
        JLabelPersonalized lMessage = new JLabelPersonalized(message, new Font("Arial", Font.BOLD, 20));
        lMessage.setLocation2(Utils.getFrameX(lMessage.getWidth(), getWidth()), (int) (0.01 * getHeight()));
//        buttonPanel.add(lMessage);

        // JButton for all options
        JButtonPersonalized bYes = new JButtonPersonalized("Yes", new Font("Arial", Font.BOLD, 40));
//        System.out.println(bYes.getSize().height);
//        System.out.println(bYes.getPreferredSize().height);
//        System.out.println(getHeight());
//        System.out.println(buttonPanel.getHeight());
//        System.out.println(0.99 * getHeight() - bYes.getSize().height);
//        System.out.println("Frame Height: " + getHeight());
//        System.out.println("Button Height: " + bYes.getSize().height);
//        System.out.println("Calculated Y Position: " + (getHeight() - bYes.getSize().height));
//        System.out.println("Frame Height: " + getHeight());
//        System.out.println("ContentPane Height: " + getContentPane().getHeight());
//        System.out.println(this.getInsets().top);
//        bYes.setLocation2(Utils.getFrameX(bYes.getSize().width, (int) (0.5 * getWidth())), (int) (0.95 * getHeight() - bYes.getSize().height));
//        bYes.setLocation2(0, 0);
//        System.out.println(bYes.getSize().height);
//        System.out.println(bYes.getMargin().top);
//        System.out.println(bYes.getMargin().bottom);
//        System.out.println(bYes.getBorder().getBorderInsets(bYes));
//        bYes.setBorder(null);
//        System.out.println(bYes.getSize().height);
//        System.out.println(bYes.getBorder());
//        buttonPanel.add(bYes);
        bYes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        JLabel r = new JLabel();
//        r.setBounds(25, (int) (getHeight() - 0.2 * getHeight() - 100), 50, 100);
        r.setBounds(25, (int) (getHeight() - 70 - 100), 50, 100);
        r.setOpaque(true);
        r.setBackground(Color.RED);
        buttonPanel.add(r);


        // Boutons Oui et Non
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        // Action Oui
        yesButton.addActionListener(e -> {
            JDialogPersonalized.choice = "YES";
//            callback.onResult(userChoice); // Transmettre le résultat via le callback
            dispose();
        });
        yesButton.setBounds(25, 50, 100, 50);

        // Action Non
        noButton.addActionListener(e -> {
            JDialogPersonalized.choice = "NO";
//            callback.onResult(userChoice); // Transmettre le résultat via le callback
            dispose();
        });
        noButton.setBounds(150, 50, 100, 50);

//        buttonPanel.add(yesButton);
//        buttonPanel.add(noButton);

        // Gérer la fermeture par la croix rouge
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JDialogPersonalized.choice = "CLOSED";
//                callback.onResult(userChoice); // Transmettre le résultat via le callback
                dispose();
            }

//            @Override
//            public void windowClosed(WindowEvent e) {
//                parent.setEnabled(true); // Réactiver la fenêtre principale
//            }
        });
    }

    public void dessiner(Graphics g) {
        g.setColor(Utils.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public String getReponse() {
        setVisible(true);
        return choice;
    }
}
