/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demineur_1_peron;


/**
 *
 * @author eliot
 */
public class Demineur_1_peron {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Création d'une première cellule (par défaut : pas de bombe, non dévoilée)
        Cellule cellule1 = new Cellule();
        System.out.println("Etat initial de la cellule : " + cellule1.toString()); // Attendu : "?"

        // Placer une bombe sur cette cellule
        cellule1.placerBombe();
        System.out.println("Apres avoir place une bombe : " + cellule1.toString()); // Attendu : "?" (non dévoilée)

        // Révéler la cellule (avec bombe)
        cellule1.revelerCellule();
        System.out.println("Cellule apres revelation (avec bombe) : " + cellule1.toString()); // Attendu : "B"

        // Création d'une nouvelle cellule sans bombe
        Cellule cellule2 = new Cellule();
        cellule2.setNbBombesAdjacentes(3); // Indiquer 3 bombes adjacentes
        System.out.println("\nNouvelle cellule avant revelation : " + cellule2.toString()); // Attendu : "?"

        // Révéler la cellule avec des bombes adjacentes
        cellule2.revelerCellule();
        System.out.println("Nouvelle cellule apres revelation : " + cellule2.toString()); // Attendu : "3"

        // Création d'une cellule vide (pas de bombe et 0 bombes adjacentes)
        Cellule cellule3 = new Cellule();
        cellule3.revelerCellule(); // Révéler la cellule vide
        System.out.println("\nCellule vide devoilee : " + cellule3.toString()); // Attendu : " "

        // Affichage final pour valider le comportement des cellules
        System.out.println("\nTests termines !");

        GrilleDeJeu grille = new GrilleDeJeu(5, 5, 5);

        // Affichage de la grille initiale (toutes les cellules sont non dévoilées)
        System.out.println("Grille initiale :");
        System.out.println(grille.toString());

        // Révélation d'une cellule spécifique (par exemple, la cellule en (2,2))
        System.out.println("Revelation de la cellule (2,2) :");
        grille.revelerCellule(2, 2);

        // Affichage de la grille après la révélation
        System.out.println("Grille apres la revelation :");
        System.out.println(grille.toString());

        // Vérification de la victoire
        if (grille.toutesCellulesRevelees()) {
            System.out.println("Felicitations ! Vous avez gagne !");
        } else {
            System.out.println("Continuez a jouer !");
        }
        
        partie partie1 = new partie(5,5,4) ;
        partie1.demarrer();
    }
}


