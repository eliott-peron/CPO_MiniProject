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

    public void reveler() {
        this.revelee = true;
        if (cellule.getPresenceBombe()) {
            setText("B");
        } else {
            int bombesAdjacentes = cellule.getNbBombesAdjacentes();
            if (bombesAdjacentes > 0) {
                setText(String.valueOf(bombesAdjacentes));
            } else {
                setText(""); // Case vide
            }
        }
        setEnabled(false); // Désactiver la cellule après qu'elle ait été cliquée
    }

    public boolean isRevelee() {
        return revelee;
    }

    public Cellule getCellule() {
        return cellule;
    }
}
