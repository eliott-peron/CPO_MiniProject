/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demineur_1_peron;
import demineur_1_peron.Cellule ;
/**
 *
 * @author eliot
 */
public class Demineur_1_peron {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cellule cellule1 = new Cellule();
        System.out.println("État initial de la cellule : " + cellule1.toString()); // Devrait afficher "?"

        // Placer une bombe
        cellule1.placerBombe();
        System.out.println("Après avoir placé une bombe : " + cellule1.toString()); // Toujours "?" car non dévoilée

        // Révéler la cellule avec une bombe
        cellule1.revelerCellule();
        System.out.println("Cellule après révélation (avec bombe) : " + cellule1.toString()); // Devrait afficher "B"

        // Création d'une nouvelle cellule sans bombe
        Cellule cellule2 = new Cellule();
        cellule2.setNbBombesAdjacentes(3); // Définir 3 bombes adjacentes
        System.out.println("\nNouvelle cellule avant révélation : " + cellule2.toString()); // Devrait afficher "?"

        // Révéler la cellule avec bombes adjacentes
        cellule2.revelerCellule();
        System.out.println("Nouvelle cellule après révélation : " + cellule2.toString()); // Devrait afficher "3"

        // Création d'une cellule vide sans bombe ni bombes adjacentes
        Cellule cellule3 = new Cellule();
        cellule3.revelerCellule();
        System.out.println("\nCellule vide dévoilée : " + cellule3.toString()); // Devrait afficher " "
    

    }
    
}
