package jeu.global;

import jeu.global.couleurs.Couleurs;
import jeu.global.difficultes.Niveau;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jicé on 06/04/2014.
 */
public class Utilisateur extends JPanel {
    private String identifiant;
    private Niveau niveau;
    private Collection<Couleurs> couleursPreferees;
    private int couleursChoisies;

    public Utilisateur(String identifiant) {
        // création d'un utilisateur
        this.identifiant = identifiant;
        this.couleursPreferees = new ArrayList<Couleurs>();
        this.couleursChoisies = 0;
        this.niveau = Niveau.UN;
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

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public void couleursSuivantes() {
        if(++couleursChoisies > couleursPreferees.toArray().length) {
            couleursChoisies = 1;
        }
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void niveauSuivant() {
        niveau = niveau.suivant();
    }
}
