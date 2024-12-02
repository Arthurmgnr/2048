package main.gui.panels;

import main.gui.EditProfile;

import javax.swing.*;
import java.awt.*;

public class EditProfilePanel extends JPanel {
    private final EditProfile editProfile;
    public EditProfilePanel(EditProfile editProfile) {
        this.editProfile = editProfile;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        editProfile.dessiner(g);
    }
}