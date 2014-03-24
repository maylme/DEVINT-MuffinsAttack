package muffinattacks;

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
     */
    public Muffin(char lettre, int taille, int largeurMonde) {
        this.lettre = lettre;
        this.taille = taille;
        this.rand = new Random();
        this.position = new Point(rand.nextInt(largeurMonde), -taille);
        this.largeurMonde = largeurMonde;
    }

    public void replaceOnTop() {
        position.move(rand.nextInt(zoneX()), -taille);
    }

    public int zoneX() {
        return largeurMonde - taille;
    }

    public boolean toucheSol(int sol) {
        return position.getY() + taille > sol;
    }

    public void fallOnce() {
        position.move((int) position.getX(), (int) position.getY() + 1);
    }

    public Point getPosition() {
        return position;
    }

    public int getTaille() {
        return taille;
    }

    public void paint(Graphics g) {
        int x = (int) position.getX();
        int y = (int) position.getY();
        g.drawRect(x, y, taille, taille);
        g.drawString(String.valueOf(lettre),x+taille/2, y+taille/2);
    }
}
