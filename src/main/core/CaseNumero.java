package main.core;

import main.utils.Utils;

import java.awt.*;

public class CaseNumero extends Case {

    private int valeur;

    public CaseNumero(int valeur) {
//        super(x, y);
        this.valeur = valeur;
    }

    @Override
    public void deplacement() {
        System.out.println("Hello and welcome in the 2048!");
    }

//    public void afficher2() {
//        System.out.print(valeur);
//    }

    @Override
    public void afficher(Graphics g, int x, int y) {
        g.setColor(Utils.getColorCase(valeur));
        g.fillRoundRect(
                x,
                y,
                Utils.coteCase,
                Utils.coteCase,
                Utils.arrondiCase,
                Utils.arrondiCase
        );
        g.setFont(new Font("Arial", Font.BOLD, Utils.getSizeText(valeur)));
        g.setColor(Utils.getColorText(valeur));
        g.drawString(
                String.valueOf(valeur),
                x - g.getFontMetrics().stringWidth(String.valueOf(valeur)) / 2 + Utils.coteCase / 2,
                y + g.getFontMetrics().getAscent() / 2 + Utils.coteCase / 2
        );
//        System.out.print(valeur);
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}
