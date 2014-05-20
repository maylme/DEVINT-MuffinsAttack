package jeu;

import devintAPI.FenetreAbstraite;
import jeu.configuration.selection.SelectionCouleurs;
import jeu.global.Utilisateur;
import jeu.global.voix.Voix;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Cette classe représente les options
 */
public class OptionCouleurs extends FenetreAbstraite {
    private Utilisateur utilisateur;
    private SelectionCouleurs selectioncouleurs;

    public OptionCouleurs(String title) {
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
        Voix.dire("La selection se fait avec la souris, on valide avec la touche Entrée.");
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

        this.setLayout(new BorderLayout());
        this.add(selectioncouleurs, BorderLayout.CENTER);
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        System.out.println(utilisateur.getCouleursPreferees());
        selectioncouleurs.setConfigurationCouleurs(utilisateur.getCouleursPreferees());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (utilisateur != null) {
                utilisateur.setCouleursPreferees(selectioncouleurs.getSelectedCouleurs());
            }
            dispose();
        }

    }

    @Override
    public void changeColor() {

    }
}
