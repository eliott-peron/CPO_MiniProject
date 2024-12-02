/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur_1_peron;

import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author eliot
 */
public class CelluleGraphique extends JButton {

    int largeur; // largeur en pixel de la cellule
    int hauteur; // hauteur en pixel de la cellule
    Cellule celluleAssociee;
    // constructeur (appelé depuis FenetrePrincipale)

    public CelluleGraphique(Cellule celluleAssociee, int l, int h) {
        this.largeur = l;
        this.hauteur = h;
        this.celluleAssociee = celluleAssociee;
    }
    // Methode gérant le dessin de la cellule

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setText(celluleAssociee.toString());
        
    }
}


