package jeu.apprentissage;

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
    Apprentissage apprentissage;

    public Braille(Apprentissage a, Caractere c, Color defaut, Color change, Color fond) {
        this.caractere = c;
        this.couleurChangee = false;
        this.apprentissage = a;

        this.setPreferredSize(new Dimension(80, 120));
        changeCouleurs(defaut, change, fond);
    }

    public Braille(Apprentissage a,Caractere c, Color fond) {
        this(a,c, Color.WHITE, Color.GRAY, fond);
    }

    public void changeCouleur() {
        couleurChangee = !couleurChangee;

        if (couleurChangee == true){
            apprentissage.direLettre(caractere.name());
        }
        this.repaint();
    }

    public void resetCouleur() {
        couleurChangee = false;
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
        if (!couleurChangee) {
            g.setColor(defaut);
        } else {
            g.setColor(change);
        }
        int xMove = this.getWidth() / 2;
        int yMove = this.getHeight() / 3;
        int taille = xMove - 2 * espacementPoints;
        for (int i : caractere.getPoints()) {
            switch (i) {
                case 1:
                    g.fillOval(espacementPoints, espacementPoints, taille, taille);
                    if (couleurChangee) {
                        g.setColor(defaut);
                        g.drawOval(espacementPoints, espacementPoints, taille, taille);
                        g.setColor(change);
                    }
                    break;
                case 2:
                    g.fillOval(xMove + espacementPoints, espacementPoints, taille, taille);
                    if (couleurChangee) {
                        g.setColor(defaut);
                        g.drawOval(xMove + espacementPoints, espacementPoints, taille, taille);
                        g.setColor(change);
                    }
                    break;
                case 3:
                    g.fillOval(espacementPoints, yMove + espacementPoints, taille, taille);
                    if (couleurChangee) {
                        g.setColor(defaut);
                        g.drawOval(espacementPoints, yMove + espacementPoints, taille, taille);
                        g.setColor(change);
                    }
                    break;
                case 4:
                    g.fillOval(xMove + espacementPoints, yMove + espacementPoints, taille, taille);
                    if (couleurChangee) {
                        g.setColor(defaut);
                        g.drawOval(xMove + espacementPoints, yMove + espacementPoints, taille, taille);
                        g.setColor(change);
                    }
                    break;
                case 5:
                    g.fillOval(espacementPoints, 2 * yMove + espacementPoints, taille, taille);
                    if (couleurChangee) {
                        g.setColor(defaut);
                        g.drawOval(espacementPoints, 2 * yMove + espacementPoints, taille, taille);
                        g.setColor(change);
                    }
                    break;
                case 6:
                    g.fillOval(xMove + espacementPoints, 2 * yMove + espacementPoints, taille, taille);
                    if (couleurChangee) {
                        g.setColor(defaut);
                        g.drawOval(xMove + espacementPoints, 2 * yMove + espacementPoints, taille, taille);
                        g.setColor(change);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public int getTouche() {
        return caractere.getKey();
    }
}
