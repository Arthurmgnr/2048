package main.gui.panels;

import main.gui.Dialog;

import javax.swing.*;
import java.awt.*;

public class DialogPanel extends JPanel {
    // Réference sur la fenêtre principale
    private final Dialog dialog;
    public DialogPanel(Dialog dialog) {
        this.dialog = dialog;
    }

//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        dialog.dessiner(g);
//    }
}