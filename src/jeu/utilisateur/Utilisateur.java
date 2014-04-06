package jeu.utilisateur;

import jeu.muffinattacks.Couleur;

import javax.swing.*;

/**
 * Created by Jicé on 06/04/2014.
 */
public class Utilisateur extends JPanel {
    private String identifiant;
    private Couleur couleurPreferee;

    public Utilisateur(String identifiant) {
        this.identifiant = identifiant;
        this.couleurPreferee = Couleur.NOIRBLANC;
    }

    public Couleur getCouleurPreferee() {
        return couleurPreferee;
    }

    public void setCouleurPreferee(Couleur c) {
        this.couleurPreferee = c;
    }
}
