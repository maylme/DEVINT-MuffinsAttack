package jeu;

import jeu.utilisateur.Utilisateur;

/**
 * classe pour lancer le jeu Elle créé simplement une instance de MenuJeu
 *
 * @author helene
 *
 */
public class LancementJeu {

    public static void main(String args[]) {
        //TODO charger utilisateur depuis un fichier
        Utilisateur u = new Utilisateur("Testeur");
        MenuJeu mj = new jeu.MenuJeu("Muffins Attack");
        mj.setUtilisateur(u);
    }
}
