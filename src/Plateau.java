import java.awt.*;
import java.util.ArrayList;

public class Plateau {

    private boolean enCours = true;
    private boolean estGagne = false;
    private boolean estPerdu = false;
    ArrayList<ArrayList<Case>> cases = new ArrayList<>();

    private int hauteur = 4;
    private int largeur = 4;

    Plateau() {
        for (int i = 0; i < hauteur; i++)
        {
            ArrayList<Case> ligne = new ArrayList<>();
            for (int j = 0; j < largeur; j++)
            {
                ligne.add(new CaseVide());
            }
            cases.add(ligne);
        }
    }

    private void testGagne() {

    }

    private void testPerdu() {
        if (!testCaseVide()) {
            estPerdu = true;
        }
    }

    private boolean testCaseVide() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (cases.get(i).get(j) instanceof CaseVide) {
                    return true;
                }
            }
        }
        return false;
    }

    private void deplacement() {

    }

    private void afficher() {

    }
}
