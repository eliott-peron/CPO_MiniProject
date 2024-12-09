package demineur_1_peron;

import javax.swing.JButton;

public class CelluleGraphique extends JButton {

    private Cellule cellule;
    private boolean revelee;

    public CelluleGraphique(Cellule cellule, int largeur, int hauteur) {
        this.cellule = cellule;
        this.revelee = false;
        setPreferredSize(new java.awt.Dimension(largeur, hauteur));
        setText(""); // Par défaut, la cellule est vide
        setEnabled(true); // La cellule est cliquable
    }

   

    public boolean isRevelee() {
        return revelee;
    }

    public Cellule getCellule() {
        return cellule;
    }
}
