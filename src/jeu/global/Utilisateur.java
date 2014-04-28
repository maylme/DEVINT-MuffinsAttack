package jeu.global;

import jeu.global.couleurs.Couleurs;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jicé on 06/04/2014.
 */
public class Utilisateur extends JPanel {
    private String identifiant;
    private Collection<Couleurs> couleursPreferees;
    private int couleursChoisies;

    public Utilisateur(String identifiant) {
        // création d'un utilisateur
        this.identifiant = identifiant;
        this.couleursPreferees = new ArrayList<Couleurs>();
        this.couleursChoisies = 0;
    }

    public Collection<Couleurs> getCouleursPreferees() {
        return couleursPreferees;
    }

    public void setCouleursPreferees(Collection<Couleurs> couleursPreferees) {
        this.couleursPreferees = couleursPreferees;
    }

    public Couleurs getCouleursChoisies() {
        if(couleursPreferees.isEmpty()) return Couleurs.BLEUBLANC;
        return (Couleurs) (couleursPreferees.toArray())[couleursChoisies];
    }

    public void couleursSuivantes() {
        if(++couleursChoisies > couleursPreferees.toArray().length) {
            couleursChoisies = 1;
        }
    }
}
