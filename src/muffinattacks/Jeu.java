package muffinattacks;

import devintAPI.FenetreAbstraite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Jeu extends FenetreAbstraite implements KeyListener {
    private Monde monde;
    private int couleur;
    private JLabel status;

    /**
     * @param title : titre de la fenetre
     */
    public Jeu(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    protected void init() {
        status = new JLabel("Etat du jeu");
        monde = new Monde(status);

        this.setLayout(new BorderLayout());
        this.add(status, BorderLayout.NORTH);
        this.add(monde, BorderLayout.CENTER);

        monde.jouer();
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
}