package demineur_1_peron;

public class GrilleDeJeu {
    private Cellule[][] matriceCellules;

    // Constructeur de la grille
    public GrilleDeJeu(int nbLignes, int nbColonnes, int nbBombes) {
        matriceCellules = new Cellule[nbLignes][nbColonnes];
        // Initialisation des cellules
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule();
            }
        }
        // Place les bombes et calcule les bombes adjacentes (à ajouter)
    }

    // Getter pour la matrice de cellules
    public Cellule[][] getMatriceCellules() {
        return matriceCellules;
    }

    // Vérifie si toutes les cellules ont été révélées
    public boolean toutesCellulesRevelees() {
        for (int i = 0; i < matriceCellules.length; i++) {
            for (int j = 0; j < matriceCellules[0].length; j++) {
                if (!matriceCellules[i][j].isDevoilee() && !matriceCellules[i][j].getPresenceBombe()) {
                    return false;
                }
            }
        }
        return true;
    }
}
