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

    // Méthode pour afficher les informations de la cellule
    @Override
    public String toString() {
        if (devoilee) {
            if (presenceBombe) {
                return "B"; // Représente une bombe
            } else if (nbBombesAdjacentes > 0) {
                return String.valueOf(nbBombesAdjacentes); // Affiche le nombre de bombes adjacentes
            } else {
                return " "; // Cellule vide
            }
        } else {
            return "?"; // Cellule non dévoilée
        }
    }
}