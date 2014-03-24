package muffinattacks;

import devintAPI.FenetreAbstraite;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Jeu extends FenetreAbstraite {
    public static void main(String[] args) {
        new Jeu("Muffins Attack");
    }

    private Monde monde;

    /**
     * @param title : titre de la fenetre
     */
    public Jeu(String title) {
        super(title);
    }

    @Override
    protected void init() {
        monde = new Monde();

        this.setLayout(new BorderLayout());
        this.add(monde, BorderLayout.CENTER);

        monde.jouer();
    }

    // renvoie le fichier wave contenant le message d'accueil
    protected  String wavAccueil() {
        return "../ressources/sons/accueil.wav";
    }

    // renvoie le fichier wave contenant la r�gle du jeu
    protected  String wavRegleJeu() {
        return "../ressources/sons/aideF1.wav";
    }

    // renvoie le fichier wave contenant la r�gle du jeu
    protected  String wavAide() {
        return "../ressources/sons/aide.wav";
    }

    @Override
    public void changeColor() {

    }
}
