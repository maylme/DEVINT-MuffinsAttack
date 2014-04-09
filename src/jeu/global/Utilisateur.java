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
        this.couleursPreferees.add(Couleurs.VERTBLANC);
        this.couleursPreferees.add(Couleurs.NOIRJAUNE);
        this.couleursPreferees.add(Couleurs.BEIGEROUGE);
        this.couleursChoisies = 0;
    }

    public Collection<Couleurs> getCouleursPreferees() {
        return couleursPreferees;
    }

    public void setCouleursPreferees(Collection<Couleurs> couleursPreferees) {
        this.couleursPreferees = couleursPreferees;
    }

    public Couleurs getCouleursChoisies() {
        return (Couleurs) (couleursPreferees.toArray())[couleursChoisies-1];
    }

    public void couleursSuivantes() {
        if(++couleursChoisies > couleursPreferees.toArray().length) {
            couleursChoisies = 1;
        }
    }
}
