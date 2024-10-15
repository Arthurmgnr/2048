package core;

import utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
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
                int x = Utils.getFrameX(Utils.cotePlateau, Utils.frameWidth) + Utils.margeCases * (j + 1) + Utils.coteCase * j;
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

    public void deplacement(String fleche) {
        int debut_i, fin_i, debut_j, fin_j;
        if (Objects.equals(fleche, "UP")) {
            debut_i = 1;
            fin_i = 4;
            debut_j = 0;
            fin_j = 4;
        } else if (Objects.equals(fleche, "DOWN")) {
            debut_i = 2;
            fin_i = -1;
            debut_j = 0;
            fin_j = 4;
        } else if (Objects.equals(fleche, "RIGHT")) {
            debut_i = 0;
            fin_i = 4;
            debut_j = 2;
            fin_j = -1;
        } else if (Objects.equals(fleche, "LEFT")) {
            debut_i = 0;
            fin_i = 4;
            debut_j = 1;
            fin_j = 4;
        } else {
            debut_i = 0;
            fin_i = 0;
            debut_j = 0;
            fin_j = 0;
        }

        for (int i = 2; i > -1; i--) {
            for (int j = 0; j < 4; j++) {
                if (cases.get(i).get(j) instanceof CaseNumero) {
                    int last_i = i;
                    while (last_i + 1 < 4 && cases.get(i + 1).get(j) instanceof CaseVide) {
                        last_i++;
                    }
                    if (cases.get(last_i).get(j) instanceof CaseVide) {
                        Case temp = cases.get(last_i).get(j);
                        cases.get(last_i).set(j, new CaseNumero(cases.get(i).get(j).getX(), cases.get(i).get(j).getY(), ((CaseNumero) cases.get(i).get(j)).getValeur()));
                        cases.get(i).set(j, new CaseVide(temp.getX(), temp.getY()));
                    }
                }
            }
        }
    }

    public void switchCase(int i, int j, int i2, int j2) {
    }

    public void afficher(Graphics g) {
        g.setColor(new Color(188, 172, 160));
        g.fillRoundRect(
                Utils.getFrameX(Utils.cotePlateau, Utils.frameWidth),
                (int) (0.2 * Utils.frameHeight),
                Utils.cotePlateau,
                Utils.cotePlateau,
                Utils.arrondiPlateau,
                Utils.arrondiPlateau
        );

        for (int i = 0; i < hauteur; i++) {
            System.out.print("\t");
            for (int j = 0; j < largeur; j++) {
                cases.get(i).get(j).afficher(g);
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
