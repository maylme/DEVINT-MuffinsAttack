package jeu.muffinattacks;

import jeu.global.caracteres.Caractere;
import jeu.global.couleurs.Couleurs;

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
    private Couleurs couleurs;
    private Caractere caractere;

    /**
     * Instancie un muffin avec une lettre et sa taille
     *
     * @param lettre
     * @param taille
     */
    public Muffin(char lettre, int taille) {
        this.lettre = lettre;
        this.caractere = Caractere.valueOf(String.valueOf(lettre));
        this.taille = taille;
        this.rand = new Random();
        this.position = new Point(0,0);
        this.couleurs = Couleurs.NOIRBLANC;
    }

    /**
     * Réinitialise la valeur de position
     */
    public void replaceOnTop(int largeurMonde) {
        position.move(rand.nextInt(largeurMonde-taille), -taille);
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
        g.setColor(couleurs.getCouleurTexte());
        g.fillRect(x, y, taille, taille);
        Caractere.paint(g, position, new Dimension(taille-taille/3, taille), caractere, false, couleurs.getCouleurFond(), null, 4);
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
        this.caractere = Caractere.valueOf(String.valueOf(lettre));
    }

    public void setCouleurs(Couleurs couleurs) {
        this.couleurs = couleurs;
    }
}
