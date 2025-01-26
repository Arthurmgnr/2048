package main.core;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Plateau {
    // Liste de listes de cases
    private final ArrayList<ArrayList<Case>> cases = new ArrayList<>();
    // Permet de savoir si le joueur a atteint 2048
    private boolean atteint2048 = false;
    // Nombre de cases du plateau
    private final int cote = 4;
    // Score du joueur au cours de la partie
    private int score = 0;
    // Nombre de coups du joueur au cours de la partie
    private int nbCoups = 0;

    // Permet de remplir le plateau de cases vides et 2 cases numerotees avec un 2 ou un 4
    public Plateau() {
        int i1, j1, i2, j2;
        Random rand = new Random();
        do {
            i1 = rand.nextInt(4);
            j1 = rand.nextInt(4);
            i2 = rand.nextInt(4);
            j2 = rand.nextInt(4);
        } while (i1 == i2 && j1 == j2);

        for (int i = 0; i < cote; i++)
        {
            ArrayList<Case> ligne = new ArrayList<>();
            for (int j = 0; j < cote; j++)
            {
                if ((i == i1 && j == j1) || (i == i2 && j == j2)) {
                    int valeur = rand.nextDouble() < 0.9 ? 2 : 4;
                    ligne.add(new CaseNumero(valeur));
                }
                else ligne.add(new CaseVide());
            }
            cases.add(ligne);
        }
    }

    // Permet de savoir si le joueur a atteint 2048
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

    /**
     * Permet de definir toutes les variables necessaires au deplacement et la fusion selon la direction choisie par le joueur
     * @param fleche la direction choisie par le joueur
     */
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

    /**
     * Permet de deplacer toutes les cases du plateau dans la direction choisie
     * @param start_i_move debut de la boucle pour le premier indice
     * @param end_i fin de la boucle pour le premier indice
     * @param step_i increment pour le premier indice (+1 ou -1)
     * @param direction_i increment pour obtenir la valeur du premier indice de la case ou l'on doit deplacer la case courante (+1 ou -1)
     * @param start_j_move debut de la boucle pour le deuxieme indice
     * @param end_j fin de la boucle pour le deuxieme indice
     * @param step_j increment pour le deuxieme indice (+1 ou -1)
     * @param direction_j increment pour obtenir la valeur du deuxieme indice de la case ou l'on doit deplacer la case courante (+1 ou -1)
     */
    private void deplacerCase(int start_i_move, int end_i, int step_i, int direction_i, int start_j_move, int end_j, int step_j, int direction_j) {
        // On parcourt toutes les cases
        for (int i = start_i_move; i != end_i; i += step_i) {
            for (int j = start_j_move; j != end_j; j += step_j) {
                // Des qu'on trouve un numero
                if (cases.get(i).get(j) instanceof CaseNumero) {
                    int last_i = i, last_j = j;

                    // On parcourt les cases de la meme ligne ou colonne pour savoir avec laquelle on doit echanger
                    while (last_i + direction_i >= 0 && last_i + direction_i < 4 &&
                            last_j + direction_j >= 0 && last_j + direction_j < 4 &&
                            cases.get(last_i + direction_i).get(last_j + direction_j) instanceof CaseVide) {
                        last_i += direction_i;
                        last_j += direction_j;
                    }

                    // Si on a trouve une case vide alors on echange
                    if (cases.get(last_i).get(last_j) instanceof CaseVide) {
                        cases.get(last_i).set(last_j, new CaseNumero(((CaseNumero) cases.get(i).get(j)).getValeur()));
                        cases.get(i).set(j, new CaseVide());
                    }
                }
            }
        }
    }

    /**
     * Permet de fusionner les cases selon la direction choisie
     * @param start_i_fusion debut de la boucle pour le premier indice
     * @param end_i fin de la boucle pour le premier indice
     * @param step_i increment pour le premier indice (+1 ou -1)
     * @param direction_i_fusion increment pour obtenir la valeur du premier indice de la case ou l'on doit deplacer la case courante (+1 ou -1)
     * @param start_j_fusion debut de la boucle pour le deuxieme indice
     * @param end_j fin de la boucle pour le deuxieme indice
     * @param step_j increment pour le deuxieme indice (+1 ou -1)
     * @param direction_j_fusion increment pour obtenir la valeur du deuxieme indice de la case ou l'on doit deplacer la case courante (+1 ou -1)
     */
    private void fusionCase(int start_i_fusion, int end_i, int step_i, int direction_i_fusion, int start_j_fusion, int end_j, int step_j, int direction_j_fusion) {
        // On parcourt toutes les cases
        for (int i = start_i_fusion; i != end_i; i += step_i) {
            for (int j = start_j_fusion; j != end_j; j += step_j) {
                // Des qu'on trouve un numero
                if (cases.get(i).get(j) instanceof CaseNumero) {
                    int next_i = i + direction_i_fusion;
                    int next_j = j + direction_j_fusion;

                    // Si on trouve bien une case numero de meme valeur juste a cote, alors on fusionne et augmente le score
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

    // Permet d'ajouter un 2 ou un 4 au hasard dans la grille
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

    // Permet de savoir si le joueur ne peut plus jouer : plateau plein et plus de deplacement possible
    public boolean gameOver() {
        // On parcourt toutes les cases
        for (int i = 0; i < cote; i++) {
            for (int j = 0; j < cote; j++) {
                // Si on trouve une case vide, alors le joueur peut encore jouer
                if (cases.get(i).get(j) instanceof CaseVide) return false;
                // Si on trouve 2 cases de meme valeur cote a cote en ligne, alors le joueur peut encore jouer
                if (i < cote - 1 &&
                        cases.get(i).get(j) instanceof CaseNumero &&
                        cases.get(i + 1).get(j) instanceof CaseNumero &&
                        ((CaseNumero) cases.get(i).get(j)).getValeur() == ((CaseNumero) cases.get(i + 1).get(j)).getValeur()) return false;
                // Si on trouve 2 cases de meme valeur cote a cote en colonne, alors le joueur peut encore jouer
                if (j < cote - 1 &&
                        cases.get(i).get(j) instanceof CaseNumero &&
                        cases.get(i).get(j + 1) instanceof CaseNumero &&
                        ((CaseNumero) cases.get(i).get(j)).getValeur() == ((CaseNumero) cases.get(i).get(j + 1)).getValeur()) return false;
            }
        }
        // Arrive ici, le joueur ne peut plus jouer car aucune condition ci-dessus n'est remplie
        return true;
    }

    // Permet de verifier que le joueur peut encore effectuer un deplacement
    public boolean deplacementAFaire(String fleche) {
        // Definition des variables permettant de factoriser le code
        int start = 0, actif_i = 0, actif_j = 0, direction_i = 0, direction_j = 0;

        // Liste de booleens de taille 4 car on va parcourir soit par ligne soit par colonne, dans tous les cas on a besoin de 4 valeurs
        // Imaginons que l'on verifie selon la direction vers la droite alors :
        //      Si le premier element est true, cela signifie que dans la premiere ligne, un deplacement est possible
        //      Si le premier element est false, cela signifie que dans la premiere ligne, aucun deplacement n'est possible
        ArrayList<Boolean> deplacementBool = new ArrayList<>(Collections.nCopies(4, true));

        // Selon la direction, on met a jour ou non les variables
        switch (fleche) {
            case "Down":
                start = 3; actif_j = 1; direction_i = -1;
                break;
            case "Up":
                actif_j = 1; direction_i = 1;
                break;
            case "Left":
                actif_i = 1; direction_j = 1;
                break;
            case "Right":
                start = 3; actif_i = 1; direction_j = -1;
                break;
        }

        // On parcourt soit par ligne soit par colonne
        for (int ind = 0; ind < cote; ind++) {
            // On recupere les 4 cases qui constituent la ligne ou la colonne
            Case case1 = cases.get(ind * actif_i + (1 - actif_i) * (start)).get(ind * actif_j + (1 - actif_j) * (start));
            Case case2 = cases.get(ind * actif_i + (1 - actif_i) * (start + direction_i)).get(ind * actif_j + (1 - actif_j) * (start + direction_j));
            Case case3 = cases.get(ind * actif_i + (1 - actif_i) * (start + direction_i * 2)).get(ind * actif_j + (1 - actif_j) * (start + direction_j * 2));
            Case case4 = cases.get(ind * actif_i + (1 - actif_i) * (start + direction_i * 3)).get(ind * actif_j + (1 - actif_j) * (start + direction_j * 3));

            // On met soit true soit false a la position correspondante
            deplacementBool.set(ind, testDeplacement(case1, case2, case3, case4));
        }

        // On renvoit true ou false, selon qu'un deplacement est possible ou non
        return deplacementBool.contains(true);
    }

    /**
     * Permet de tester toutes les combinaisons possibles de 4 cases pour savoir si un deplacement est possible
     * @param case1 premiere case
     * @param case2 deuxieme case
     * @param case3 troisieme case
     * @param case4 quatrieme case
     * @return true ou false selon qu'un deplacement est possible ou non
     */
    private boolean testDeplacement(Case case1, Case case2, Case case3, Case case4) {
        // ∅ ∅ ∅ ∅ => 4 cases vides => pas de deplacement
        if (case1 instanceof CaseVide && case2 instanceof CaseVide && case3 instanceof CaseVide && case4 instanceof CaseVide) return false;
        // 2 ∅ ∅ ∅ => 1 numero et 3 cases vides => pas de deplacement
        if (case1 instanceof CaseNumero && case2 instanceof CaseVide && case3 instanceof CaseVide && case4 instanceof CaseVide) return false;
        // 2 4 ∅ ∅ => 2 numeros differents et 2 cases vides => pas de deplacement
        if (case1 instanceof CaseNumero && case2 instanceof CaseNumero && case3 instanceof CaseVide && case4 instanceof CaseVide &&
                ((CaseNumero) case1).getValeur() != ((CaseNumero) case2).getValeur()) return false;
        // 2 4 8 ∅ => 3 numeros differents et 1 case vide => pas de deplacement
        if (case1 instanceof CaseNumero && case2 instanceof CaseNumero && case3 instanceof CaseNumero && case4 instanceof CaseVide &&
                ((CaseNumero) case1).getValeur() != ((CaseNumero) case2).getValeur() &&
                ((CaseNumero) case2).getValeur() != ((CaseNumero) case3).getValeur()) return false;
        // 2 4 8 16 => 4 numeros differents => pas de deplacement
        if (case1 instanceof CaseNumero && case2 instanceof CaseNumero && case3 instanceof CaseNumero && case4 instanceof CaseNumero &&
                ((CaseNumero) case1).getValeur() != ((CaseNumero) case2).getValeur() &&
                ((CaseNumero) case2).getValeur() != ((CaseNumero) case3).getValeur() &&
                ((CaseNumero) case3).getValeur() != ((CaseNumero) case4).getValeur()) return false;

        // Dans toutes les autres configurations, un deplacement est possible
        return true;
    }

    // Permet d'appeler la methode afficher de chaque case pour afficher tout le plateau
    public void afficher(ArrayList<ArrayList<JPanel>> listOfPanel) {
        for (int i = 0; i < cote; i++) {
            for (int j = 0; j < cote; j++) {
                cases.get(i).get(j).afficher(listOfPanel.get(i).get(j));
            }
        }
    }

    // Permet de recuperer la valeur maximale des tuiles du plateau
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
}
