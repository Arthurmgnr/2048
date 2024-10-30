package core;

import utils.Utils;

import java.awt.*;

public class CaseVide extends Case {

//    public CaseVide(int x, int y) {
//        super(x, y);
//    }

    @Override
    public void deplacement() {
        System.out.println("Hello and welcome in the 2048!");
    }

//    public void afficher2() {
//        System.out.print("∅");
//    }

    @Override
    public void afficher(Graphics g, int x, int y) {
        g.setColor(new Color(205, 192, 180));
        g.fillRoundRect(
                x,
                y,
                Utils.coteCase,
                Utils.coteCase,
                Utils.arrondiCase,
                Utils.arrondiCase
        );
//        System.out.print("∅");
    }
}
