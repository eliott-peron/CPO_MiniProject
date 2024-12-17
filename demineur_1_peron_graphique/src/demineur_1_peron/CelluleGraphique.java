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

         setBackground(new java.awt.Color(50, 50, 80)); // Fond sombre
          setForeground(java.awt.Color.BLACK); // Texte blanc
          setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 100, 150), 1)); // Bordure fine
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

    public void revelerCellule() {
        revelee = true;
        cellule.revelerCellule();
        setEnabled(false);

        if (cellule.getPresenceBombe()) {
            setText("💣"); // Marque une bombe
            ((FenetrePrincipale) getTopLevelAncestor()).terminerPartie(false); // Fin de partie avec défaite
        } else {
            int bombesAdjacentes = cellule.getNbBombesAdjacentes();
            setText(bombesAdjacentes > 0 ? String.valueOf(bombesAdjacentes) : "");

            // Vérification de victoire
            ((FenetrePrincipale) getTopLevelAncestor()).verifierVictoire();
        }
    }
    
    
    
    
    
    

}
