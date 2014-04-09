package jeu;

import java.awt.*;
import java.awt.event.KeyEvent;

import devintAPI.*;
import jeu.utilisateur.SelectionCouleurs;
import jeu.utilisateur.Utilisateur;

/**
 * Cette classe est un exemple d'interface pour les options
 *
 */
public class Option extends FenetreAbstraite {
    private Utilisateur utilisateur;
    private SelectionCouleurs selectioncouleurs;

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
        selectioncouleurs = new SelectionCouleurs();
        selectioncouleurs.setConfiguration(utilisateur.getCouleursPreferees());

        this.setLayout(new BorderLayout());
        this.add(selectioncouleurs, BorderLayout.CENTER);
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            utilisateur.setCouleursPreferees(selectioncouleurs.getSelectedCouleurs());
        }

    }

    @Override
    public void changeColor() {}
}
