package main.core;

import main.utils.Utils;

import java.awt.*;
import java.util.*;

public class Plateau {
    private ArrayList<ArrayList<Case>> cases = new ArrayList<>();
    private boolean atteint2048 = false;
    private boolean perdu = false;
    private int cote = 4;
    private int score = 0;
    private int nbCoups = 0;

    public Plateau() {
//        int i1, j1, i2, j2;
////        int i3, j3, i4, j4;
////        i1 = 0;
////        j1 = 0;
////        i2 = 0;
////        j2 = 1;
////        i3 = 0; j3 = 2; i4 = 0; j4 = 3;
//        Random rand = new Random();
//        do {
//            i1 = rand.nextInt(4);
//            j1 = rand.nextInt(4);
//            i2 = rand.nextInt(4);
//            j2 = rand.nextInt(4);
//        } while (i1 == i2 && j1 == j2);
//
//        for (int i = 0; i < cote; i++)
//        {
//            ArrayList<Case> ligne = new ArrayList<>();
//            for (int j = 0; j < cote; j++)
//            {
//                if ((i == i1 && j == j1) || (i == i2 && j == j2)) {
//                    int valeur = rand.nextDouble() < 0.9 ? 2 : 4;
//                    ligne.add(new CaseNumero(valeur));
//                }
////                if (i == i1 && j == j1) ligne.add(new CaseNumero(1024));
////                else if (i == i2 && j == j2) ligne.add(new CaseNumero(1024));
////                else if (i == i3 && j == j3) ligne.add(new CaseNumero(4));
////                else if (i == i4 && j == j4) ligne.add(new CaseNumero(4));
//                else ligne.add(new CaseVide());
//            }
//            cases.add(ligne);
//        }

        // Cas de test de Defaite
        ArrayList<Case> ligne1 = new ArrayList<>(Arrays.asList(new CaseNumero(64), new CaseNumero(32), new CaseNumero(16), new CaseNumero(8)));
        ArrayList<Case> ligne2 = new ArrayList<>(Arrays.asList(new CaseNumero(8), new CaseNumero(16), new CaseNumero(32), new CaseNumero(64)));
        ArrayList<Case> ligne3 = new ArrayList<>(Arrays.asList(new CaseNumero(64), new CaseNumero(32), new CaseNumero(16), new CaseNumero(8)));
        ArrayList<Case> ligne4 = new ArrayList<>(Arrays.asList(new CaseNumero(8), new CaseNumero(64), new CaseNumero(32), new CaseVide()));
        cases.add(ligne1);
        cases.add(ligne2);
        cases.add(ligne3);
        cases.add(ligne4);
    }

    public boolean joueurAtteint2048() {
        for (int i = 0; i < cote; i++) {
            for (int j = 0; j < cote; j++) {
                if (cases.get(i).get(j) instanceof CaseNumero &&
                        ((CaseNumero) cases.get(i).get(j)).getValeur() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean testCaseVide() {
        for (int i = 0; i < cote; i++) {
            for (int j = 0; j < cote; j++) {
                if (cases.get(i).get(j) instanceof CaseVide) {
                    return true;
                }
            }
        }
        return false;
    }

    public void deplacement(String fleche) {
        int start_i_move = 0, end_i = 0, step_i = 0, direction_i = 0, start_i_fusion = 0, direction_i_fusion = 0;
        int start_j_move = 0, end_j = 0, step_j = 0, direction_j = 0, start_j_fusion = 0, direction_j_fusion = 0;

        switch (fleche) {
            case "Down":
                start_i_move = 2; end_i = -1; step_i = -1; end_j = 4; step_j = 1;
                direction_i = 1; start_i_fusion = 3; direction_i_fusion = -1;
                break;
            case "Up":
                start_i_move = 1; end_i = 4; step_i = 1; end_j = 4; step_j = 1;
                direction_i = -1; direction_i_fusion = 1;
                break;
            case "Left":
                end_i = 4; step_i = 1; start_j_move = 1; end_j = 4; step_j = 1;
                direction_j = -1; direction_j_fusion = 1;
                break;
            case "Right":
                end_i = 4; step_i = 1; start_j_move = 2; end_j = -1; step_j = -1;
                direction_j = 1; start_j_fusion = 3; direction_j_fusion = -1;
                break;
        }

        deplacerCase(start_i_move, end_i, step_i, direction_i, start_j_move, end_j, step_j, direction_j);
        fusionCase(start_i_fusion, end_i, step_i, direction_i_fusion, start_j_fusion, end_j, step_j, direction_j_fusion);
        deplacerCase(start_i_move, end_i, step_i, direction_i, start_j_move, end_j, step_j, direction_j);
        nbCoups++;
    }

    private void deplacerCase(int start_i_move, int end_i, int step_i, int direction_i, int start_j_move, int end_j, int step_j, int direction_j) {
        for (int i = start_i_move; i != end_i; i += step_i) {
            for (int j = start_j_move; j != end_j; j += step_j) {
                if (cases.get(i).get(j) instanceof CaseNumero) {
                    int last_i = i, last_j = j;

                    while (last_i + direction_i >= 0 && last_i + direction_i < 4 &&
                            last_j + direction_j >= 0 && last_j + direction_j < 4 &&
                            cases.get(last_i + direction_i).get(last_j + direction_j) instanceof CaseVide) {
                        last_i += direction_i;
                        last_j += direction_j;
                    }

                    if (cases.get(last_i).get(last_j) instanceof CaseVide) {
                        cases.get(last_i).set(last_j, new CaseNumero(((CaseNumero) cases.get(i).get(j)).getValeur()));
                        cases.get(i).set(j, new CaseVide());
                    }
                }
            }
        }
    }

    private void fusionCase(int start_i_fusion, int end_i, int step_i, int direction_i_fusion, int start_j_fusion, int end_j, int step_j, int direction_j_fusion) {
        for (int i = start_i_fusion; i != end_i; i += step_i) {
            for (int j = start_j_fusion; j != end_j; j += step_j) {
                if (cases.get(i).get(j) instanceof CaseNumero) {
                    int next_i = i + direction_i_fusion;
                    int next_j = j + direction_j_fusion;

                    if (-1 < next_i && next_i < 4 && -1 < next_j && next_j < 4 &&
                            cases.get(next_i).get(next_j) instanceof CaseNumero &&
                            ((CaseNumero) cases.get(i).get(j)).getValeur() == ((CaseNumero) cases.get(next_i).get(next_j)).getValeur()) {
                        int newValeur = 2 * ((CaseNumero) cases.get(i).get(j)).getValeur();
                        cases.get(i).set(j, new CaseNumero(newValeur));
                        cases.get(i + direction_i_fusion).set(j + direction_j_fusion, new CaseVide());
                        score += newValeur;

                    }
                }
            }
        }
    }

    public void ajoutCase() {
        Random rand = new Random();
        int i, j;
        do {
            i = rand.nextInt(4);
            j = rand.nextInt(4);
        } while (cases.get(i).get(j) instanceof CaseNumero);
        int valeur = rand.nextDouble() < 0.9 ? 2 : 4;
        cases.get(i).set(j, new CaseNumero(valeur));
    }

    public boolean gameOver() {
//        boolean caseVide = false, vertical = false, horizontal = false;
//        if (testCaseVide()) caseVide = true;
//        for (int i = 0; i < hauteur; i++) {
//            for (int j = 0; j < largeur - 1; j++) {
//                if (cases.get(i).get(j) instanceof CaseNumero && cases.get(i).get(j + 1) instanceof CaseNumero &&
//                        ((CaseNumero) cases.get(i).get(j)).getValeur() == ((CaseNumero) cases.get(i).get(j + 1)).getValeur()) {
//                    horizontal = true;
//                    break;
//                }
//            }
//        }
//        for (int i = 0; i < hauteur - 1; i++) {
//            for (int j = 0; j < largeur; j++) {
//                if (cases.get(i).get(j) instanceof CaseNumero && cases.get(i + 1).get(j) instanceof CaseNumero &&
//                        ((CaseNumero) cases.get(i).get(j)).getValeur() == ((CaseNumero) cases.get(i + 1).get(j)).getValeur()) {
//                    vertical = true;
//                    break;
//                }
//            }
//        }
//        return caseVide || horizontal || vertical;

        for (int i = 0; i < cote; i++) {
            for (int j = 0; j < cote; j++) {
                if (cases.get(i).get(j) instanceof CaseVide) return false;
                if (i < cote - 1 &&
                        cases.get(i).get(j) instanceof CaseNumero &&
                        cases.get(i + 1).get(j) instanceof CaseNumero &&
                        ((CaseNumero) cases.get(i).get(j)).getValeur() == ((CaseNumero) cases.get(i + 1).get(j)).getValeur()) return false;
                if (j < cote - 1 &&
                        cases.get(i).get(j) instanceof CaseNumero &&
                        cases.get(i).get(j + 1) instanceof CaseNumero &&
                        ((CaseNumero) cases.get(i).get(j)).getValeur() == ((CaseNumero) cases.get(i).get(j + 1)).getValeur()) return false;
            }
        }
        return true;
    }

    public boolean deplacementAFaire(String fleche) {
        int start = 0, actif_i = 0, actif_j = 0, direction_i = 0, direction_j = 0;

        ArrayList<Boolean> deplacementBool = new ArrayList<>(Collections.nCopies(4, true));

        switch (fleche) {
            case "Down":
                start = 3;
                actif_j = 1;
                direction_i = -1;
                break;
            case "Up":
                actif_j = 1;
                direction_i = 1;
                break;
            case "Left":
                actif_i = 1;
                direction_j = 1;
                break;
            case "Right":
                start = 3;
                actif_i = 1;
                direction_j = -1;
                break;
        }

        for (int ind = 0; ind < 4; ind++) {
            Case case1 = cases.get(ind * actif_i + (1 - actif_i) * (start)).get(ind * actif_j + (1 - actif_j) * (start));
            Case case2 = cases.get(ind * actif_i + (1 - actif_i) * (start + direction_i)).get(ind * actif_j + (1 - actif_j) * (start + direction_j));
            Case case3 = cases.get(ind * actif_i + (1 - actif_i) * (start + direction_i * 2)).get(ind * actif_j + (1 - actif_j) * (start + direction_j * 2));
            Case case4 = cases.get(ind * actif_i + (1 - actif_i) * (start + direction_i * 3)).get(ind * actif_j + (1 - actif_j) * (start + direction_j * 3));

            deplacementBool.set(ind, testDeplacement(case1, case2, case3, case4));
        }

        return deplacementBool.contains(true);
    }

    private boolean testDeplacement(Case case1, Case case2, Case case3, Case case4) {
        // ∅ ∅ ∅ ∅
        if (case1 instanceof CaseVide && case2 instanceof CaseVide && case3 instanceof CaseVide && case4 instanceof CaseVide) return false;
        // 2 ∅ ∅ ∅
        if (case1 instanceof CaseNumero && case2 instanceof CaseVide && case3 instanceof CaseVide && case4 instanceof CaseVide) return false;
        // 2 2 ∅ ∅
        if (case1 instanceof CaseNumero && case2 instanceof CaseNumero && case3 instanceof CaseVide && case4 instanceof CaseVide &&
                ((CaseNumero) case1).getValeur() != ((CaseNumero) case2).getValeur()) return false;
        // 2 2 2 ∅
        if (case1 instanceof CaseNumero && case2 instanceof CaseNumero && case3 instanceof CaseNumero && case4 instanceof CaseVide &&
                ((CaseNumero) case1).getValeur() != ((CaseNumero) case2).getValeur() &&
                ((CaseNumero) case2).getValeur() != ((CaseNumero) case3).getValeur()) return false;
        // 2 2 2 2
        if (case1 instanceof CaseNumero && case2 instanceof CaseNumero && case3 instanceof CaseNumero && case4 instanceof CaseNumero &&
                ((CaseNumero) case1).getValeur() != ((CaseNumero) case2).getValeur() &&
                ((CaseNumero) case2).getValeur() != ((CaseNumero) case3).getValeur() &&
                ((CaseNumero) case3).getValeur() != ((CaseNumero) case4).getValeur()) return false;

        return true;
    }

//    public boolean deplacementAFaire() {
//        boolean caseVide = false, vertical = false, horizontal = false;
//
//        if (testCaseVide()) caseVide = true;
//
//        for (int i = 0; i < hauteur; i++) {
//            for (int j = 0; j < largeur - 1; j++) {
//                if (cases.get(i).get(j) instanceof CaseNumero && cases.get(i).get(j + 1) instanceof CaseNumero &&
//                        ((CaseNumero) cases.get(i).get(j)).getValeur() == ((CaseNumero) cases.get(i).get(j + 1)).getValeur()) {
//                    horizontal = true;
//                    break;
//                }
//            }
//        }
//
//        for (int i = 0; i < hauteur - 1; i++) {
//            for (int j = 0; j < largeur; j++) {
//                if (cases.get(i).get(j) instanceof CaseNumero && cases.get(i + 1).get(j) instanceof CaseNumero &&
//                        ((CaseNumero) cases.get(i).get(j)).getValeur() == ((CaseNumero) cases.get(i + 1).get(j)).getValeur()) {
//                    vertical = true;
//                    break;
//                }
//            }
//        }
//
//        return caseVide || horizontal || vertical;
//    }

//    public void afficher2() {
//        for (int i = 0; i < hauteur; i++) {
//            System.out.print("\t");
//            for (int j = 0; j < largeur; j++) {
//                cases.get(i).get(j).afficher2();
//                System.out.print("\t");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }

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

        for (int i = 0; i < cote; i++) {
//            System.out.print("\t");
            for (int j = 0; j < cote; j++) {
                int x = Utils.getFrameX(Utils.cotePlateau, Utils.frameWidth) + Utils.margeCases * (j + 1) + Utils.coteCase * j;
                int y = (int) ((0.2 * Utils.frameHeight) + Utils.margeCases * (i + 1) + Utils.coteCase * i);
                cases.get(i).get(j).afficher(g, x, y);
//                System.out.print("\t");
            }
//            System.out.println();
        }
//        System.out.println();
    }

    public int getBestTuile() {
        int max = 0;
        for (int i = 0; i < cote; i++) {
            for (int j = 0; j < cote; j++) {
                Case caseActuelle = cases.get(i).get(j);
                if (caseActuelle instanceof CaseNumero) {
                    int valeur = ((CaseNumero) caseActuelle).getValeur();
                    if (valeur > max) max = valeur;
                }
            }
        }
        return max;
    }

    public int getScore() {
        return score;
    }

    public int getNbCoups() {
        return nbCoups;
    }

    public boolean getatteint2048() {
        return atteint2048;
    }

    public void setatteint2048(boolean atteint2048) {
        this.atteint2048 = atteint2048;
    }

    public boolean isPerdu() {
        return perdu;
    }

    public void setPerdu(boolean perdu) {
        this.perdu = perdu;
    }
}
