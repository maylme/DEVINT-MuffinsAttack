package jeu.muffinattacks;

import java.awt.*;
import java.util.Random;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Muffin {

    private static Random rand;
    private char lettre;
    private Point position;
    private int taille;
    private int largeurMonde;

    /**
     * Instancie un muffin avec une lettre
     *
     * @param lettre
     * @param taille
     * @param largeurMonde
     */
    public Muffin(char lettre, int taille, int largeurMonde) {
        this.lettre = lettre;
        this.taille = taille;
        this.rand = new Random();
        this.position = new Point();
        this.largeurMonde = largeurMonde;
    }

    /**
     * Réinitialise la valeur de position
     */
    public void replaceOnTop() {
        position.move(rand.nextInt(zoneX()), -taille);
    }

    /**
     * Permet de calculer la zone autorisée pour la position horizontale du
     * muffin
     *
     * @return valeur maximale que peut prendre la valeur x
     */
    private int zoneX() {
        return largeurMonde - taille;
    }

    /**
     * Renvoie true si le muffin à dépassé la valeur sol donnée en paramètre
     *
     * @param sol la position du sol, un entier
     * @return un booléen indiquant si le muffin à dépassé la valeur sol ou non
     */
    public boolean toucheSol(int sol) {
        return position.getY() + taille > sol;
    }

    /**
     * Permet de faire tomber le muffin d'un pixel
     */
    public void moveOnce() {
        position.move((int) position.getX(), (int) position.getY() + 1);
    }

    /**
     * Récupére la position du muffin (point en haut à gauche)
     *
     * @return Point coordonnées du point supérieur gauche du muffin
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Renvoie la taille du muffin
     *
     * @return la largeur/hauteur occupée par le muffin
     */
    public int getTaille() {
        return taille;
    }

    public void paint(Graphics g) {
        int x = (int) position.getX();
        int y = (int) position.getY();
        g.fillRect(x, y, taille, taille);
        //g.drawString(String.valueOf(lettre), x + taille / 2, y + taille / 2);
    }

    /**
     * Renvoie la lettre qui doit être tapée pour tuer ce muffin
     *
     * @return lettre du muffin
     */
    public char getLettre() {
        return lettre;
    }

    public void setLettre(char lettre) {
        this.lettre = lettre;
    }

}
