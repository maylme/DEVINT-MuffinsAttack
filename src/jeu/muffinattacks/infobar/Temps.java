package jeu.muffinattacks.infobar;

import jeu.global.couleurs.Couleurs;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jean-Christophe Isoard
 */
public class Temps extends JPanel {
    private int tempsTotal;
    private int tempsEcoule;
    private Couleurs couleurs;
    private static final int epaisseur = 30;

    /**
     * Configure un nouvel afficheur
     */
    public Temps() {
        this.tempsTotal = 1;
        this.tempsEcoule = 0;
        this.setBackground(null);
        this.setPreferredSize(new Dimension(0,epaisseur+2));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int atteint = tempsEcoule*this.getWidth()/tempsTotal;
        g.setColor(couleurs.getCouleurTexte());
        g.drawRect(0, 0, this.getWidth()-1, epaisseur);
        g.fillRect(0, 0, atteint, epaisseur);
    }

    public void setCouleurs(Couleurs couleurs) {
        this.couleurs = couleurs;
    }

    /**
     * Met à jour le temps total
     * @param tempsTotal
     */
    public void setTempsTotal(int tempsTotal) {
        this.tempsTotal = tempsTotal;
        repaint();
    }

    /**
     * Fait avancer le temps écouler de la valeur donnée en millisecondes
     * @param t
     */
    public void forward(int t) {
        tempsEcoule += t;
        repaint();
    }

    /**
     * Réinitialise le temps écoulé
     */
    public void reset() {
        tempsEcoule = 0;
        repaint();
    }
}
