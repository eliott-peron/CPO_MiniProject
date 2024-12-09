package demineur_1_peron;

import java.util.Random;

public class GrilleDeJeu {

    public Cellule[][] matriceCellules; // Grille contenant toutes les cellules
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
                if (voisinLigne >= 0 && voisinLigne < nbLignes
                        && voisinColonne >= 0 && voisinColonne < nbColonnes) {
                    if (matriceCellules[voisinLigne][voisinColonne].getPresenceBombe()) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    // Méthode pour poser ou retirer un drapeau sur une cellule
    public void gererDrapeau(int ligne, int colonne) {
        // Vérification des coordonnées
        if (ligne < 0 || ligne >= nbLignes || colonne < 0 || colonne >= nbColonnes) {
            System.out.println("Coordonnees invalides !");
            return;
        }

        Cellule cellule = matriceCellules[ligne][colonne];
        if (cellule.isDevoilee()) {
            System.out.println("Impossible de poser un drapeau sur une cellule devoilee !");
        } else {
            // Basculer l'état du drapeau
            cellule.setDrapeau(!cellule.getDrapeau());
            System.out.println("Drapeau " + (cellule.getDrapeau() ? "pose" : "retire") + " en (" + ligne + ", " + colonne + ").");
        }
    }

    // Méthode pour révéler une cellule
    public void revelerCellule(int ligne, int colonne) {
        // Vérifier si les coordonnées sont valides ou si la cellule est déjà dévoilée
        if (ligne < 0 || ligne >= nbLignes || colonne < 0 || colonne >= nbColonnes) {
            System.out.println("Coordonnees invalides !");
            return;
        }

        Cellule cellule = matriceCellules[ligne][colonne];
        if (cellule.isDevoilee()) {
            System.out.println("Cellule deja devoilee !");
            return;
        }

        if (cellule.getDrapeau()) {
            System.out.println("Impossible de reveler une cellule avec un drapeau !");
            return;
        }

        // Révéler la cellule
        cellule.revelerCellule();

        // Si la cellule contient une bombe, le joueur a perdu
        if (cellule.getPresenceBombe()) {
            System.out.println("Bombe revelee ! Vous avez perdu.");
        } else if (cellule.getNbBombesAdjacentes() == 0) {
            // Si la cellule est vide, révéler ses voisins
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
                Cellule cellule = matriceCellules[i][j];
                if (!cellule.getPresenceBombe() && !cellule.isDevoilee()) {
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

        // Afficher les lignes de la grille avec délimitations
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                builder.append("| " + matriceCellules[i][j].toString() + " ");
            }
            builder.append("|\n");

            // Ajouter une ligne de délimitation après chaque ligne de la grille
            for (int j = 0; j < nbColonnes; j++) {
                builder.append("----");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
