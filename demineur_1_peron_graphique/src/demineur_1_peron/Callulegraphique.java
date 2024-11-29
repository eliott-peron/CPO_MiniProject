/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur_1_peron;

import javax.swing.JButton;

/**
 *
 * @author leoboree
 */
public class Callulegraphique extends JButton {
    int largeur;
    int longueur;
    Cellule celluleDMR;

    public Callulegraphique(int largeur, int longueur, Cellule celluleDMR) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.celluleDMR = celluleDMR;
    }
    
    
    
}
