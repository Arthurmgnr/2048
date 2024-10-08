package gui;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private JPanel myContentPane;
    private JLabel l2048;
    private JLabel lScore;
    private JLabel lBestScore;
    private Rectangle rect;

    public Game() {
        // Parametres de la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(Utils.frameX, Utils.frameY, Utils.frameWidth, Utils.frameHeight);
        setTitle("2048");

        // Gestionnaire de placement
        myContentPane = new JPanel() ;
        myContentPane.setLayout(null);
        setContentPane(myContentPane);

        // Label 2048
        l2048 = new JLabel("2048");
        l2048.setFont(new Font("Arial", Font.BOLD, 30));
        l2048.setSize(l2048.getPreferredSize());
        l2048.setLocation(Utils.getFrameX(l2048.getSize().width, 0), 100);
        myContentPane.add(l2048);

        // Label Score
        lScore = new JLabel("Score");
        lScore.setFont(new Font("Arial", Font.BOLD, 24));
        lScore.setSize(lScore.getPreferredSize());
        lScore.setLocation(Utils.getFrameX(lScore.getSize().width, 1), 200);
        myContentPane.add(lScore);

        // Label BestScore
        lBestScore = new JLabel("Best Score");
        lBestScore.setFont(new Font("Arial", Font.BOLD, 24));
        lBestScore.setSize(lBestScore.getPreferredSize());
        lBestScore.setLocation(Utils.getFrameX(lBestScore.getSize().width, 2), 200);
        myContentPane.add(lBestScore);

        // Rectangle
    }
}
