package demineur_1_peron;

import java.util.Scanner;

public class partie {
    private GrilleDeJeu grille;  // La grille du jeu
    private boolean enCours;     // Indique si la partie est en cours

    // Constructeur par défaut
    public partie(int nbLignes, int nbColonnes, int nbBombes) {
        grille = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        enCours = true; // La partie commence
    }

    // Méthode pour choisir le niveau
    private void choisirNiveau() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez un niveau de difficulté :");
        System.out.println("1. Débutant (9x9, 10 mines)");
        System.out.println("2. Intermédiaire (16x16, 40 mines)");
        System.out.println("3. Expert (30x16, 99 mines)");

        int choix = -1;
        while (choix < 1 || choix > 3) {
            try {
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();
                switch (choix) {
                    case 1:
                        grille = new GrilleDeJeu(9, 9, 10);
                        break;
                    case 2:
                        grille = new GrilleDeJeu(16, 16, 40);
                        break;
                    case 3:
                        grille = new GrilleDeJeu(30, 16, 99);
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez entrer 1, 2 ou 3.");
                }
            } catch (Exception e) {
                System.out.println("Entrée invalide, veuillez réessayer.");
                scanner.nextLine(); // Nettoie le buffer
            }
        }

        enCours = true; // La partie commence
    }

    // Méthode pour démarrer la partie
    public void demarrer() {
        // Appel de la méthode pour choisir le niveau
        choisirNiveau();

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