package jeu.muffinattacks.infobar;

import jeu.global.couleurs.Couleurs;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * @author Jean-Christophe Isoard
 */
public class Temps extends JPanel {
    private static DecimalFormat formateur;
    private int tempsTotal;
    private int tempsEcoule;
    private Couleurs couleurs;

    /**
     * Configure un nouvel afficheur avec le temps en millisecondes
     * @param t
     */
    public Temps(int t) {
        this.tempsTotal = t;
        this.tempsEcoule = 0;
        this.setBackground(null);
        this.setPreferredSize(new Dimension(100,0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int atteint = tempsEcoule*360/tempsTotal;
        int taille = this.getHeight()-10;
        g.setColor(couleurs.getCouleurTexte());
        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        g.drawArc(0, 5, taille, taille, 0, 360);
        g.fillArc(0, 5, taille, taille, 0, 360-atteint);
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
