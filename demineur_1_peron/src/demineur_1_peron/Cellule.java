package demineur_1_peron;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author leoboree
 */
public class Cellule {

    // Attributs privés
    private boolean presenceBombe;         // Indique si la cellule contient une bombe
    private boolean devoilee;              // Indique si la cellule est dévoilée
    private int nbBombesAdjacentes;        // Nombre de bombes dans les cellules adjacentes

    // Constructeur
    public Cellule() {
        this.presenceBombe = false; // Par défaut, pas de bombe
        this.devoilee = false;      // Par défaut, la cellule n'est pas dévoilée
        this.nbBombesAdjacentes = 0; // Par défaut, pas de bombes adjacentes
    }

    // Accesseur pour presenceBombe
    public boolean getPresenceBombe() {
        return presenceBombe;
    }

    // Accesseur pour nbBombesAdjacentes
    public int getNbBombesAdjacentes() {
        return nbBombesAdjacentes;
    }

    // Méthode pour placer une bombe
    public void placerBombe() {
        this.presenceBombe = true;
    }

    // Méthode pour révéler la cellule
    public void revelerCellule() {
        this.devoilee = true;
    }

    // Méthode pour définir le nombre de bombes adjacentes
    public void setNbBombesAdjacentes(int nb) {
        this.nbBombesAdjacentes = nb;
    }

    @Override
    public String toString() {
        if (devoilee) {
            if (presenceBombe) {
                return "B"; // Représente une bombe
            } else if (nbBombesAdjacentes > 0) {
                return getColorForNumber(nbBombesAdjacentes) + nbBombesAdjacentes + "\u001B[0m"; // Nombre coloré
            } else {
                return " "; // Cellule vide
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
                return "\u001B[30m"; // Noir
            case 8:
                return "\u001B[37m"; // Blanc
            default:
                return "\u001B[0m"; // Réinitialisation (aucune couleur)
        }
    }
}