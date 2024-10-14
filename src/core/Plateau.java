package core;

import utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Plateau {

    private boolean enCours = true;
    private boolean estGagne = false;
    private boolean estPerdu = false;
    ArrayList<ArrayList<Case>> cases = new ArrayList<>();

    private int hauteur = 4;
    private int largeur = 4;

    public Plateau() {
        int i1, j1, i2, j2;
        Random rand = new Random();
        do {
            i1 = rand.nextInt(4);
            j1 = rand.nextInt(4);
            i2 = rand.nextInt(4);
            j2 = rand.nextInt(4);
        } while (i1 == i2 && j1 == j2);

        for (int i = 0; i < hauteur; i++)
        {
            ArrayList<Case> ligne = new ArrayList<>();
            for (int j = 0; j < largeur; j++)
            {
                int x = Utils.getFrameX(Utils.cotePlateau, 0) + Utils.margeCases * (j + 1) + Utils.coteCase * j;
                int y = (int) ((0.2 * Utils.frameHeight) + Utils.margeCases * (i + 1) + Utils.coteCase * i);
                if ((i == i1 && j == j1) || (i == i2 && j == j2)) {
                    int valeur = rand.nextDouble() < 0.9 ? 2 : 4;
                    ligne.add(new CaseNumero(x, y, valeur));
                }
                else ligne.add(new CaseVide(x, y));
            }
            cases.add(ligne);
        }
    }

    public void testGagne() {

    }

    public void testPerdu() {
        if (!testCaseVide()) {
            estPerdu = true;
        }
    }

    public boolean testCaseVide() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (cases.get(i).get(j) instanceof CaseVide) {
                    return true;
                }
            }
        }
        return false;
    }

    public void deplacement() {

    }

    public void afficher(Graphics g) {
        g.setColor(new Color(188, 172, 160));
        g.fillRoundRect(
                Utils.getFrameX(Utils.cotePlateau, 0),
                (int) (0.2 * Utils.frameHeight),
                Utils.cotePlateau,
                Utils.cotePlateau,
                Utils.arrondiPlateau,
                Utils.arrondiPlateau
        );

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                cases.get(i).get(j).afficher(g);
            }
        }
    }
}
