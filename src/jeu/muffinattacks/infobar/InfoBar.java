package jeu.muffinattacks.infobar;

import jeu.global.couleurs.Couleurs;
import jeu.global.difficultes.Niveau;
import jeu.muffinattacks.Jeu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jicé on 30/04/2014.
 */
public class InfoBar extends JPanel {
    private Vies vies;
    private Temps temps;
    private Niveaux niveaux;

    public InfoBar(Jeu jeu) {
        vies = new Vies();
        temps = new Temps();
        niveaux = new Niveaux(jeu);

        this.setLayout(new BorderLayout());
        this.add(vies, BorderLayout.WEST);
        this.add(niveaux, BorderLayout.CENTER);
        this.add(temps, BorderLayout.SOUTH);

        this.setBackground(Color.BLACK);
    }

    public void viePerdue() {
        vies.viePerdue();
    }

    public int getNbVies() {
        return vies.getVies();
    }

    public void changeCouleurs(Couleurs couleurs) {
        this.setBackground(couleurs.getCouleurFond());
        temps.setCouleurs(couleurs);
        niveaux.setCouleurs(couleurs);
    }

    public void setTempsTotal(int tempsRestant) {
        temps.setTempsTotal(tempsRestant);
    }

    public void setVies(int n) {
        vies.setVies(n);
    }

    public void setNiveau(Niveau niveau) {
        niveaux.setNiveau(niveau);
    }

    public void setScore(int points) {
        niveaux.setScore(points);
    }

    public void forwardTime(int t) {
        temps.forward(t);
    }

    public void resetTime() {
        temps.reset();
    }

    public void resetVies() {
        vies.resetVies();
    }
}
