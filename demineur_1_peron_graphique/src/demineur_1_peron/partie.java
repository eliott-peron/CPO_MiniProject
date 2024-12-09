package demineur_1_peron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class partie {
    private GrilleDeJeu grille;
    private boolean enCours;
    private JFrame frame; // Fenêtre principale
    private JButton[][] boutons; // Boutons représentant la grille

    public partie(int nbLignes, int nbColonnes, int nbBombes) {
        grille = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        enCours = true;
        initInterface(nbLignes, nbColonnes);
    }

    private void initInterface(int nbLignes, int nbColonnes) {
        frame = new JFrame("Démineur");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(nbLignes, nbColonnes));

        boutons = new JButton[nbLignes][nbColonnes];

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                JButton bouton = new JButton("?");
                boutons[i][j] = bouton;

                // Ajouter un ActionListener pour chaque bouton
                int finalI = i;
                int finalJ = j;

                bouton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gererClic(finalI, finalJ);
                    }
                });

                frame.add(bouton);
            }
        }

        frame.pack();
        frame.setVisible(true);
    }

    // Cette méthode est utilisée pour démarrer la partie
    public void demarrer() {
        // Vous pouvez initialiser ou réinitialiser des éléments si nécessaire
        enCours = true;
        grille = new GrilleDeJeu(grille.getMatriceCellules().length, grille.getMatriceCellules()[0].length, 4); // Exemple avec 4 bombes
        System.out.println("La partie a commencé !");
    }

    private void gererClic(int ligne, int colonne) {
        if (!enCours) {
            return;
        }

        Cellule cellule = grille.getMatriceCellules()[ligne][colonne];

        if (cellule.isDevoilee()) {
            return; // Ne rien faire si la cellule est déjà dévoilée
        }

        cellule.revelerCellule(); // Révèle la cellule

        if (cellule.getPresenceBombe()) {
            // Si c'est une bombe, afficher "B" et terminer la partie
            boutons[ligne][colonne].setText("B");
            boutons[ligne][colonne].setEnabled(false);
            JOptionPane.showMessageDialog(frame, "Vous avez cliqué sur une bombe ! Partie terminée.");
            enCours = false;
            finDePartie();
        } else {
            // Afficher le nombre de bombes adjacentes ou vider les cases adjacentes si vide
            int nbBombesAdjacentes = cellule.getNbBombesAdjacentes();
            if (nbBombesAdjacentes > 0) {
                boutons[ligne][colonne].setText(String.valueOf(nbBombesAdjacentes));
            } else {
                boutons[ligne][colonne].setText(" ");
                revelerVoisins(ligne, colonne);
            }
            boutons[ligne][colonne].setEnabled(false);
        }

        if (grille.toutesCellulesRevelees()) {
            JOptionPane.showMessageDialog(frame, "Félicitations ! Vous avez gagné !");
            enCours = false;
            finDePartie();
        }
    }

    private void revelerVoisins(int ligne, int colonne) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int voisinLigne = ligne + i;
                int voisinColonne = colonne + j;

                if (voisinLigne >= 0 && voisinLigne < grille.getMatriceCellules().length &&
                        voisinColonne >= 0 && voisinColonne < grille.getMatriceCellules()[0].length) {
                    Cellule voisin = grille.getMatriceCellules()[voisinLigne][voisinColonne];
                    if (!voisin.isDevoilee() && !voisin.getPresenceBombe()) {
                        gererClic(voisinLigne, voisinColonne); // Révéler de manière récursive
                    }
                }
            }
        }
    }

    private void finDePartie() {
        for (int i = 0; i < grille.getMatriceCellules().length; i++) {
            for (int j = 0; j < grille.getMatriceCellules()[0].length; j++) {
                Cellule cellule = grille.getMatriceCellules()[i][j];
                if (cellule.getPresenceBombe()) {
                    boutons[i][j].setText("B");
                }
                boutons[i][j].setEnabled(false);
            }
        }
    }
}
