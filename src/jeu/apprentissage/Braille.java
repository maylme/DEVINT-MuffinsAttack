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
    Color defaut;
    Color change;
    Boolean couleurChangee;

    public Braille(Caractere c, Color defaut, Color change, Color fond) {
        this.caractere = c;
        this.couleurChangee = false;

        this.setPreferredSize(new Dimension(80,120));
        changeCouleurs(defaut, change, fond);
    }

    public Braille(Caractere c, Color defaut, Color fond) {
        this(c, defaut, new Color(defaut.getRGB()/2), fond);
    }

    public Braille(Caractere c, Color fond) {
        this(c, Color.WHITE, Color.GRAY, fond);
    }

    public void changeCouleur() {
        couleurChangee = !couleurChangee;
        this.repaint();
    }

    public void changeCouleurs(Color defaut, Color change, Color fond) {
        this.defaut = defaut;
        this.change = change;
        this.setBackground(fond);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(!couleurChangee) {
            g.setColor(defaut);
        } else {
            g.setColor(change);
        }
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
