import gui.Game;

import java.awt.*;

public class Main {

    private double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public static void main(String[] args) {
        Game frame = new Game();
        frame.setVisible(true);
    }
}