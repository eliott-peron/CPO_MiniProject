/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur_1_peron;

/**
 *
 * @author eliot
 */
import java.util.Random;

public class GrilleDeJeu {
    private Cellule[][] matriceCellules; // Grille contenant toutes les cellules
    private int nbLignes;               // Nombre de lignes de la grille
    private int nbColonnes;             // Nombre de colonnes de la grille
    private int nbBombes;               // Nombre total de bombes dans la grille

    // Constructeur
    public GrilleDeJeu(int nbLignes, int nbColonnes, int nbBombes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbBombes = nbBombes;
        matriceCellules = new Cellule[nbLignes][nbColonnes];
        initialiserGrille();
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public int getNbBombes() {
        return nbBombes;
    }

    public Cellule[][] getMatriceCellules() {
        return matriceCellules;
    }
    
    
    
    

    // Méthode pour initialiser la grille
    private void initialiserGrille() {
        // Remplir la grille avec des instances de Cellule
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule();
            }
        }
        // Placer les bombes de manière aléatoire
        placerBombesAleatoirement();
        // Calculer le nombre de bombes adjacentes pour chaque cellule
        calculerBombesAdjacentes();
    }

    // Méthode pour placer les bombes aléatoirement
    private void placerBombesAleatoirement() {
        Random random = new Random();
        int bombesPlacees = 0;

        while (bombesPlacees < nbBombes) {
            int ligne = random.nextInt(nbLignes);
            int colonne = random.nextInt(nbColonnes);

            // Si la cellule ne contient pas déjà une bombe, on en place une
            if (!matriceCellules[ligne][colonne].getPresenceBombe()) {
                matriceCellules[ligne][colonne].placerBombe();
                bombesPlacees++;
            }
        }
    }

    // Méthode pour calculer le nombre de bombes adjacentes à chaque cellule
    private void calculerBombesAdjacentes() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe()) {
                    int bombesAdjacentes = compterBombesAdjacentes(i, j);
                    matriceCellules[i][j].setNbBombesAdjacentes(bombesAdjacentes);
                }
            }
        }
    }

    // Méthode pour compter les bombes adjacentes à une cellule donnée
    private int compterBombesAdjacentes(int ligne, int colonne) {
        int count = 0;

        // Parcourir les 8 cellules voisines
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int voisinLigne = ligne + i;
                int voisinColonne = colonne + j;

                // Vérifier si la cellule voisine est dans les limites de la grille
                if (voisinLigne >= 0 && voisinLigne < nbLignes &&
                    voisinColonne >= 0 && voisinColonne < nbColonnes) {
                    if (matriceCellules[voisinLigne][voisinColonne].getPresenceBombe()) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    // Méthode pour révéler une cellule
    public void revelerCellule(int ligne, int colonne) {
        // Vérifier si les coordonnées sont valides ou si la cellule est déjà dévoilée
        if (ligne < 0 || ligne >= nbLignes || colonne < 0 || colonne >= nbColonnes ||
            matriceCellules[ligne][colonne].toString().equals(" ")) {
            return;
        }

        // Révéler la cellule
        matriceCellules[ligne][colonne].revelerCellule();

        // Si la cellule contient une bombe, le joueur a perdu
        if (matriceCellules[ligne][colonne].getPresenceBombe()) {
            System.out.println("Bombe revelee ! Vous avez perdu.");
        }
        // Si la cellule est vide (0 bombes adjacentes), révéler les voisins
        else if (matriceCellules[ligne][colonne].getNbBombesAdjacentes() == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    revelerCellule(ligne + i, colonne + j);
                }
            }
        }
    }

    // Méthode pour vérifier si toutes les cellules sûres ont été révélées
    public boolean toutesCellulesRevelees() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe() && matriceCellules[i][j].toString().equals("?")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Méthode pour afficher la grille (représentation textuelle)
    @Override
public String toString() {
    StringBuilder builder = new StringBuilder();

    // Calculer le nombre maximal de chiffres pour les colonnes et les lignes
    int maxColonnes = Integer.toString(nbColonnes - 1).length(); // Longueur du plus grand numéro de colonne
    int maxLignes = Integer.toString(nbLignes - 1).length(); // Longueur du plus grand numéro de ligne

    // Afficher la numérotation des colonnes
    builder.append("   "); // Espace pour la ligne de numérotation des colonnes
    for (int j = 0; j < nbColonnes; j++) {
        builder.append(String.format("% " + maxColonnes + "d ", j)); // Formatage pour aligner les numéros des colonnes
    }
    builder.append("\n");

    // Afficher les lignes de la grille avec délimitations
    for (int i = 0; i < nbLignes; i++) {
        // Numérotation des lignes avec formatage pour aligner correctement
        builder.append(String.format("%" + (maxLignes + 1) + "d", i)); // Formatage pour aligner les numéros de ligne

        for (int j = 0; j < nbColonnes; j++) {
            // Affichage des cellules avec une largeur fixe pour un alignement correct
            builder.append("| " + matriceCellules[i][j].toString() + " ");
        }
        builder.append("|\n");

        // Ajouter une ligne de délimitation après chaque ligne de la grille
        builder.append("   ");
        for (int j = 0; j < nbColonnes; j++) {
            builder.append("----"); // Délimitation de chaque case
        }
        builder.append("\n");
    }

    return builder.toString();
}
}
