package jeu.utilisateur;

import jeu.muffinattacks.Couleur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jicé on 06/04/2014.
 */
public class SelectionCouleur extends JPanel {
    private Collection<ChoixCouleur> choixCouleur;

    public SelectionCouleur() {
        this.choixCouleur = new ArrayList<ChoixCouleur>();

        for(Couleur c:Couleur.values()) {
            this.choixCouleur.add(new ChoixCouleur(c));
        }

        this.setLayout(new GridLayout(0,6,10,10));
        for(ChoixCouleur choix:choixCouleur) {
            this.add(choix);
        }
    }
}
