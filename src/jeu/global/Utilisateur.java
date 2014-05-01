package jeu.global;

import jeu.global.couleurs.Couleurs;
import jeu.global.difficultes.Niveau;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jean-Christophe Isoard
 */
public class Utilisateur extends JPanel {
    private String identifiant;
    private Niveau niveau;
    private Collection<Couleurs> couleursPreferees;
    private int couleursChoisies;
    private static final String iconsLocation = "../ressources/images/utilisateurs/";
    private String icon;

    public Utilisateur(String identifiant) {
        // création d'un utilisateur
        this.identifiant = identifiant;
        this.couleursPreferees = new ArrayList<Couleurs>();
        this.couleursChoisies = 0;
        this.niveau = Niveau.UN;
        this.icon = "alligator";
    }

    public Collection<Couleurs> getCouleursPreferees() {
        return couleursPreferees;
    }

    public void setCouleursPreferees(Collection<Couleurs> couleursPreferees) {
        this.couleursPreferees = couleursPreferees;
    }

    public Couleurs getCouleursChoisies() {
        if(couleursPreferees.isEmpty()) return Couleurs.BLEUBLANC;
        return (Couleurs) (couleursPreferees.toArray())[couleursChoisies-1];
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

    public String getIdentifiant() {
        return identifiant;
    }

    public String getIcon() {
        return icon;
    }
}
