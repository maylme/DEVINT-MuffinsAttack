package jeu;

import java.awt.*;
import javax.swing.JTextArea;
import devintAPI.*;
import jeu.utilisateur.SelectionCouleur;

/**
 * Cette classe est un exemple d'interface pour les options
 *
 */
public class Option extends FenetreAbstraite {

    public Option(String title) {
        super(title);
    }

    // renvoie le fichier wave contenant le message d'accueil
    @Override
    protected String wavAccueil() {
        return "../ressources/sons/accueilOption.wav";
    }

    // renvoie le fichier wave contenant la règle du jeu
    @Override
    protected String wavRegleJeu() {
        return "../ressources/sons/accueilOption.wav";
    }

    // renvoie le fichier wave contenant la règle du jeu
    @Override
    protected String wavAide() {
        return "../ressources/sons/aide.wav";
    }

    @Override
    public void init() {
        this.setLayout(new BorderLayout());
        this.add(new SelectionCouleur(), BorderLayout.CENTER);
    }

    @Override
    public void changeColor() {}
}
