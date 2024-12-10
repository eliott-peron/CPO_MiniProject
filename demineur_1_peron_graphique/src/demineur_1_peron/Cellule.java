package demineur_1_peron;

/**
 * Classe représentant une cellule de la grille du jeu.
 */
public class Cellule {

    // Attributs privés
    private boolean presenceBombe;   // Indique si la cellule contient une bombe
    private boolean devoilee;        // Indique si la cellule est dévoilée
    private boolean drapeau;         // Indique si la cellule est marquée par un drapeau
    private int nbBombesAdjacentes;  // Nombre de bombes dans les cellules adjacentes
    
    // Constructeur
    public Cellule() {
        this.presenceBombe = false; // Par défaut, pas de bombe
        this.devoilee = false;      // Par défaut, la cellule n'est pas dévoilée
        this.drapeau = false;       // Par défaut, pas de drapeau
        this.nbBombesAdjacentes = 0; // Par défaut, pas de bombes adjacentes
    }

    // Accesseur pour savoir si une bombe est présente
    public boolean getPresenceBombe() {
        return presenceBombe;
    }

    // Mutateur pour placer une bombe
    public void placerBombe() {
        this.presenceBombe = true;
    }

    // Accesseur pour savoir si la cellule est dévoilée
    public boolean isDevoilee() {
        return devoilee;
    }

    // Méthode pour dévoiler une cellule
    public void revelerCellule() {
        if (!drapeau) { // Ne peut pas être dévoilée si un drapeau est présent
            this.devoilee = true;
        }
    }

    // Accesseur pour le nombre de bombes adjacentes
    public int getNbBombesAdjacentes() {
        return nbBombesAdjacentes;
    }

    // Mutateur pour définir le nombre de bombes adjacentes
    public void setNbBombesAdjacentes(int nb) {
        this.nbBombesAdjacentes = nb;
    }

    // Accesseur pour vérifier la présence d'un drapeau
    public boolean getDrapeau() {
        return drapeau;
    }

    // Mutateur pour poser ou retirer un drapeau
    public void setDrapeau(boolean drapeau) {
        if (!devoilee) { // On ne peut pas poser de drapeau sur une cellule déjà dévoilée
            this.drapeau = drapeau;
        }
    }

    @Override
    public String toString() {
        if (drapeau) {
            return "D"; // Représentation d'un drapeau
        } else if (devoilee) {
            if (presenceBombe) {
                return "B"; // Représentation d'une bombe
            } else if (nbBombesAdjacentes > 0) {
                return getColorForNumber(nbBombesAdjacentes) + nbBombesAdjacentes + "\u001B[0m"; // Nombre coloré
            } else {
                return "."; // Cellule vide
            }
        } else {
            return "?"; // Cellule non dévoilée
        }
    }

    /**
     * Retourne le code couleur ANSI selon le nombre.
     *
     * @param number Le nombre de bombes adjacentes.
     * @return Un code couleur ANSI.
     */
    private String getColorForNumber(int number) {
        switch (number) {
            case 1:
                return "\u001B[34m"; // Bleu
            case 2:
                return "\u001B[32m"; // Vert
            case 3:
                return "\u001B[31m"; // Rouge
            case 4:
                return "\u001B[35m"; // Magenta
            case 5:
                return "\u001B[33m"; // Jaune
            case 6:
                return "\u001B[36m"; // Cyan
            case 7:
                return "\u001B[37m"; // Blanc
            case 8:
                return "\u001B[30m"; // Noir
            default:
                return "\u001B[0m"; // Réinitialisation (aucune couleur)
        }
    }
}
