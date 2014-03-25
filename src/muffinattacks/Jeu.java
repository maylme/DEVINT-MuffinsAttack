package muffinattacks;

import devintAPI.FenetreAbstraite;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Jeu extends FenetreAbstraite {
    private Monde monde;
    private int couleur;

    /**
     * @param title : titre de la fenetre
     */
    public Jeu(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    protected void init() {
        monde = new Monde();

        this.setLayout(new BorderLayout());
        this.add(monde, BorderLayout.CENTER);
    }

    // renvoie le fichier wave contenant le message d'accueil
    protected String wavAccueil() {
        return "../ressources/sons/accueil.wav";
    }

    // renvoie le fichier wave contenant la r�gle du jeu
    protected String wavRegleJeu() {
        return "../ressources/sons/aideF1.wav";
    }

    // renvoie le fichier wave contenant la r�gle du jeu
    protected String wavAide() {
        return "../ressources/sons/aide.wav";
    }

    @Override
    public void changeColor() {
        //TODO ne fait rien pour le moment, à faire
        Couleur.getOne(++couleur);
    }

    public void jouer() {
        monde.jouer();
    }
}
