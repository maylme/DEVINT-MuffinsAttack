package jeu.muffinattacks.infobar;

import jeu.global.couleurs.Couleurs;
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
        vies = new Vies(jeu);
        temps = new Temps(0);
        niveaux = new Niveaux(jeu);

        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(vies, BorderLayout.WEST);
        this.add(temps, BorderLayout.EAST);
    }

    public void viePerdue() {
        vies.viePerdue();
    }

    public int getNbVies() {
        return vies.getVies();
    }

    public void changeCouleur(Couleurs couleurs) {
        this.setBackground(couleurs.getCouleurFond());
        temps.setForeground(couleurs.getCouleurTexte());
    }

    public void updateTime(int tempsRestant) {
        temps.update(tempsRestant);
    }

    public void setVies(int n) {
        vies.setVies(n);
    }
}
