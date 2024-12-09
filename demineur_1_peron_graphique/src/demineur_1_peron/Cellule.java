package demineur_1_peron;

public class Cellule {
    private boolean presenceBombe;
    private boolean devoilee;
    private int nbBombesAdjacentes;

    // Constructeur par défaut
    public Cellule() {
        this.presenceBombe = false;
        this.devoilee = false;
        this.nbBombesAdjacentes = 0;
    }

    // Getter pour vérifier si la cellule contient une bombe
    public boolean getPresenceBombe() {
        return presenceBombe;
    }

    // Setter pour placer une bombe
    public void placerBombe() {
        this.presenceBombe = true;
    }

    // Vérifie si la cellule a été révélée
    public boolean isDevoilee() {
        return devoilee;
    }

    // Méthode pour révéler la cellule
    public void revelerCellule() {
        this.devoilee = true;
    }

    // Getter et setter pour le nombre de bombes adjacentes
    public int getNbBombesAdjacentes() {
        return nbBombesAdjacentes;
    }

    public void setNbBombesAdjacentes(int nbBombes) {
        this.nbBombesAdjacentes = nbBombes;
    }
    
    @Override
    public String toString() {
        if (devoilee) {
            return presenceBombe ? "B" : String.valueOf(nbBombesAdjacentes);
        } else {
            return "?";
        }
    }
}
