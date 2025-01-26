package main.gui;

import main.utils.JButtonPersonalized;
import main.utils.Utils;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JDialogPersonalized extends JDialog {
    // Permet de savoir et de renvoyer le choix du joueur
    private static int choice;

    public JDialogPersonalized(JFrame parent, String message, String[] options, String[] toolTipTexts) {
        // Recupere la largeur maximale parmis tous les boutons que l'on doit afficher
        Dimension maxDimension = new Dimension(0, 0);
        for (int i = 0; i < options.length; i++) {
            JButtonPersonalized bOption = new JButtonPersonalized(options[i], toolTipTexts[i]);
            bOption.setFont(new Font("Arial", Font.BOLD, 20));
            Dimension dimension = new Dimension((int) (bOption.getPreferredSize().width * 1.4), (int) (bOption.getPreferredSize().height * 1.2));
            if (dimension.width > maxDimension.width) {
                maxDimension.height = dimension.height;
                maxDimension.width = dimension.width;
            }
        }

        // Parametres de la fenetre
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        int testWidth = (options.length + 1) * maxDimension.width + (options.length - 1) * 50;
        int width = Math.max(testWidth, (int) (0.5 * Utils.frameWidth));
        setSize(width, (int) (0.5 * Utils.frameHeight));
        setLocationRelativeTo(parent);
        setTitle("2048");
        setResizable(false);
        setModal(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // JLabel message
        JTextArea tMessage = new JTextArea(message);
        tMessage.setWrapStyleWord(true); // Passe à la ligne au mot le plus proche
        tMessage.setLineWrap(true); // Active le retour à la ligne
        tMessage.setEditable(false); // Rend non éditable
        tMessage.setOpaque(false); // Fond transparent comme un JLabel
        tMessage.setFocusable(false); // Ne capte pas le focus
        tMessage.setFont(new Font("Arial", Font.BOLD, 20)); // Police par défaut
        tMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        tMessage.setMaximumSize(new Dimension((int) (0.8 * getWidth()), tMessage.getPreferredSize().height));

        // Creation d'un bouton pour chaque option
        for (int i = 0; i < options.length; i++) {
            JButtonPersonalized bOption = new JButtonPersonalized(options[i], toolTipTexts[i]);
            bOption.setFont(new Font("Arial", Font.BOLD, 20));
            bOption.setBothSize(maxDimension);
            int finalI = i;
            bOption.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JDialogPersonalized.choice = finalI;
                    dispose();
                }
            });

            buttonPanel.add(bOption);
            buttonPanel.add(Box.createHorizontalStrut(50));
        }
        buttonPanel.remove(buttonPanel.getComponents().length - 1);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(tMessage);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);

        // Gérer la fermeture par la croix rouge
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JDialogPersonalized.choice = -1;
                dispose();
            }
        });
    }

    // Permet d'afficher la fenetre de dialogue et renvoyer l'option choisie
    public int getReponse() {
        setVisible(true);
        return choice;
    }
}
