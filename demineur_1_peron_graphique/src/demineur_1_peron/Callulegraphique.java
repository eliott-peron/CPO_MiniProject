/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur_1_peron;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Callulegraphique extends JButton {

    private Cellule cellule; // Modèle associé
    private int ligne;
    private int colonne;

    public Callulegraphique(Cellule cellule, int ligne, int colonne) {
        this.cellule = cellule;
        this.ligne = ligne;
        this.colonne = colonne;

        // Initialisation graphique
        setText(" "); // Indique que la cellule est cachée
        setFocusPainted(false);

        // Ajout d'un écouteur pour gérer les clics
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action à définir : révéler ou poser un drapeau
                revelerOuDrapeau();
            }
        });
    }

    // Méthode pour révéler une cellule ou poser un drapeau
    private void revelerOuDrapeau() {
        if (!cellule.isDevoilee()) {
            if (cellule.getDrapeau()) {
                cellule.setDrapeau(false);
                setText(" ");
            } else {
                if (cellule.getPresenceBombe()) {
                    setText("💣"); // Bombe découverte
                    setEnabled(false); // Désactiver après révélation
                } else {
                    setText(String.valueOf(cellule.getNbBombesAdjacentes()));
                    cellule.revelerCellule();
                    setEnabled(false); // Désactiver après révélation
                }
            }
        }

    }

    // Mise à jour graphique pour correspondre au modèle
    public void mettreAJour() {
        if (cellule.isDevoilee()) {
            if (cellule.getPresenceBombe()) {
                setText("B");
            } else {
                int nbBombesAdjacentes = cellule.getNbBombesAdjacentes();
                setText(nbBombesAdjacentes > 0 ? String.valueOf(nbBombesAdjacentes) : "");
            }
            setEnabled(false);
        } else if (cellule.getDrapeau()) {
            setText("🚩");
        } else {
            setText(" ");
        }

    }
    
    
}
