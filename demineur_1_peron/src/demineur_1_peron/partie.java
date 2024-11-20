/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur_1_peron;

import java.util.Scanner;

public class partie {
    private GrilleDeJeu grille;  // La grille du jeu
    private boolean enCours;     // Indique si la partie est en cours

    // Constructeur
    public partie(int nbLignes, int nbColonnes, int nbBombes) {
        grille = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        enCours = true; // La partie commence
    }

    // Méthode pour démarrer la partie
    public void demarrer() {
        Scanner scanner = new Scanner(System.in);

        // Affichage initial de la grille
        System.out.println("Bienvenue dans le jeu Démineur !");
        System.out.println("Voici la grille de jeu :");
        System.out.println(grille.toString());

        while (enCours) {
            System.out.println("Entrez les coordonnées de la cellule à révéler (ligne et colonne) :");
            try {
                // Récupération des coordonnées du joueur
                System.out.print("Ligne : ");
                int ligne = scanner.nextInt();
                System.out.print("Colonne : ");
                int colonne = scanner.nextInt();

                // Révélation de la cellule
                boolean resultat = tourDeJeu(ligne, colonne);

                // Affichage de la grille après le tour
                System.out.println("\nÉtat actuel de la grille :");
                System.out.println(grille.toString());

                // Vérification du résultat
                if (!resultat) {
                    System.out.println("Vous avez révélé une bombe ! Partie terminée.");
                    enCours = false;
                } else if (grille.toutesCellulesRevelees()) {
                    System.out.println("Félicitations ! Vous avez découvert toutes les cellules sûres.");
                    enCours = false;
                }
            } catch (Exception e) {
                System.out.println("Entrée invalide, veuillez entrer des coordonnées valides.");
                scanner.nextLine(); // Pour nettoyer le buffer
            }
        }

        scanner.close();
        System.out.println("Merci d'avoir joué !");
    }

    // Méthode pour gérer un tour de jeu
    private boolean tourDeJeu(int ligne, int colonne) {
        try {
            // Révéler la cellule
            grille.revelerCellule(ligne, colonne);

            // Si la cellule contient une bombe, le joueur perd
            if (grille.getMatriceCellules()[ligne][colonne].getPresenceBombe()) {
                return false; // Bombe révélée
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Les coordonnées sont hors des limites de la grille !");
        }

        return true; // Le joueur peut continuer
    }
}

