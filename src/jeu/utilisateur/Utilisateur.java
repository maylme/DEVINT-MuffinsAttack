package jeu.utilisateur;

import jeu.muffinattacks.Couleurs;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jicé on 06/04/2014.
 */
public class Utilisateur extends JPanel {
    private String identifiant;
    private Collection<Couleurs> couleursPreferees;

    public Utilisateur(String identifiant) {
        // création d'un utilisateur
        this.identifiant = identifiant;
        this.couleursPreferees = new ArrayList<Couleurs>();
        this.couleursPreferees.add(Couleurs.NOIRBLANC);
    }

    public Collection<Couleurs> getCouleursPreferees() {
        return couleursPreferees;
    }

    public void setCouleursPreferees(Collection<Couleurs> couleursPreferees) {
        this.couleursPreferees = couleursPreferees;
    }
}
