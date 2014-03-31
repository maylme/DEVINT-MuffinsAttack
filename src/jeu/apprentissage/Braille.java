package jeu.apprentissage;

import jeu.muffinattacks.Couleur;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jicé on 31/03/2014.
 */
public class Braille extends JPanel {
    Caractere caractere;
    int espacementPoints = 4;
    Couleur c;

    public Braille(Caractere c) {
        this.caractere = c;
        this.c = Couleur.NOIRBLANC;

        this.setPreferredSize(new Dimension(80,120));
        //this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void changeCouleur() {
        c = Couleur.NOIRORANGE;
        this.setBackground(c.getCouleurFond());
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(c.getCouleurTexte());
        int xMove = this.getWidth()/2;
        int yMove = this.getHeight()/3;
        int taille = xMove-2*espacementPoints;
        for(int i:caractere.getPoints()) {
            switch (i) {
                case 1:
                    g.fillOval(espacementPoints, espacementPoints, taille, taille);
                    break;
                case 2:
                    g.fillOval(xMove+espacementPoints,espacementPoints, taille, taille);
                    break;
                case 3:
                    g.fillOval(espacementPoints,yMove+espacementPoints, taille, taille);
                    break;
                case 4:
                    g.fillOval(xMove+espacementPoints,yMove+espacementPoints, taille, taille);
                    break;
                case 5:
                    g.fillOval(espacementPoints,2*yMove+espacementPoints, taille, taille);
                    break;
                case 6:
                    g.fillOval(xMove+espacementPoints,2*yMove+espacementPoints, taille, taille);
                    break;
                default:
                    break;
            }
        }
    }
}
