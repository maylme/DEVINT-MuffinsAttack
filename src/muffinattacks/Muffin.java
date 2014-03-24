package muffinattacks;

import java.awt.*;
import java.util.Random;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Muffin {
    private static Random rand;
    private static final int VALEURDECHUTE = 10;
    private char lettre;
    private Point position;
    private int taille;
    private int largeurMonde;

    /**
     * Instancie un muffin avec une lettre
     *
     * @param lettre
     */
    public Muffin(char lettre, int taille, int largeurMonde) {
        this.lettre = lettre;
        this.taille = taille;
        this.rand = new Random();
        this.position = new Point(rand.nextInt(largeurMonde), -taille);
        this.largeurMonde = largeurMonde;
    }

    public void replace() {
        position.move(rand.nextInt(largeurMonde), -taille);
    }

    public int zoneX() {
        return largeurMonde - taille;
    }

    public void grandir() {
        this.taille += 5;
    }

    public boolean toucheSol(int sol) {
        return position.getY() + taille > sol;
    }

    public void fallOnce() {
        position.move((int) position.getX(), (int) position.getY() + VALEURDECHUTE);
        System.out.println("New position: " + position);
    }

    public Point getPosition() {
        return position;
    }

    public int getTaille() {
        return taille;
    }
}
