package demineur_1_peron;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CelluleGraphique extends JButton {

    private Cellule cellule;
    private boolean revelee;

    public CelluleGraphique(Cellule cellule, int largeur, int hauteur) {
        this.cellule = cellule;
        this.revelee = false;

        setPreferredSize(new java.awt.Dimension(largeur, hauteur));
        setText(" "); // Par défaut, la cellule est vide
        setEnabled(true); // La cellule est cliquable

        // Ajout de l'écouteur de souris
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (javax.swing.SwingUtilities.isRightMouseButton(evt)) {
                    // Gestion du drapeau (clic droit)
                    cellule.setDrapeau(!cellule.getDrapeau());
                    setText(cellule.getDrapeau() ? "⚑" : "");
                } else if (javax.swing.SwingUtilities.isLeftMouseButton(evt)) {
                    // Gestion de la révélation (clic gauche)
                    if (!cellule.isDevoilee() && !cellule.getDrapeau()) {
                        revelerCellule();
                    }
                }
            }
        });
    }

    public boolean isRevelee() {
        return revelee;
    }

    public Cellule getCellule() {
        return cellule;
    }

    private void revelerCellule() {
        // Implémentation de la logique de révélation
        revelee = true;
        cellule.revelerCellule();
        setEnabled(false);

        if (cellule.getPresenceBombe()) {
            setText("💣"); // Marque une bombe
        } else {
            int bombesAdjacentes = cellule.getNbBombesAdjacentes();
            setText(bombesAdjacentes > 0 ? String.valueOf(bombesAdjacentes) : "");
        }
    }

    // private void revelerVoisins() {
    // int ligne = ...; // Récupérer l'index de la ligne de la cellule
    //int colonne = ...; // Récupérer l'index de la colonne de la cellule
    // for (int i = -1; i <= 1; i++) {
    //       for (int j = -1; j <= 1; j++) {
    //         int voisinLigne = ligne + i;
    //       int voisinColonne = colonne + j;
//
    //              if (voisinLigne >= 0 && voisinLigne < grille.getNbLignes()
    //                    && voisinColonne >= 0 && voisinColonne < grille.getNbColonnes()) {
    //              CelluleGraphique voisin = (CelluleGraphique) grille.getMatriceCellules()[voisinLigne][voisinColonne];
    //            if (!voisin.isRevelee()) {
    //              voisin.revelerCellule();


}
