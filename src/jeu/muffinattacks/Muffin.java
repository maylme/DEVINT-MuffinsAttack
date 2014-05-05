package jeu.muffinattacks;

import jeu.global.caracteres.Caractere;
import jeu.global.couleurs.Couleurs;

import java.awt.*;
import java.util.Random;

/**
 * @author Jean-Christophe Isoard
 */
public class Muffin {
    private static Random rand;
    private String lettre;
    private Point position;
    private int taille;
    private Couleurs couleurs;
    private Caractere caractere;
    private int sautMuffin;

    /**
     * Instancie un muffin non affiché de taille 100px
     */
    public Muffin() {
        this.rand = new Random();
        this.lettre = null;
        this.caractere = null;
        this.taille = 100;
        this.position = new Point(0,0);
        this.sautMuffin = 1;
    }

    /**
     * Réinitialise la valeur de position
     */
    public void replaceOnTop(int largeurMonde) {
        position.move(rand.nextInt(largeurMonde-taille), -taille);
    }

    /**
     * Permet de faire tomber le muffin d'un pixel
     */
    public void moveOnce() {
        position.move((int) position.getX(), (int) position.getY() + sautMuffin);
    }

    /**
     * Renvoie la taille du muffin
     *
     * @return la largeur/hauteur occupée par le muffin
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Redessine le muffin de façon réduite à chaque appel
     * <br />Pour réinitialiser l'action de cette fonction, il faut réinitialiser la variable effacement à 0
     * @param g
     */
    public void dessineMuffinPartiel(Graphics g, int efface) {
        int x1 = (int) position.getX();
        int y1 = (int) position.getY()+efface;
        int x2 = taille;
        int y2 = taille-efface*2;
        g.setColor(couleurs.getCouleurTexte());
        g.fillRect(x1, y1, x2, y2);
    }

    public void paint(Graphics g) {
        if(lettre == null || caractere == null)
            return;
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
    public String getLettre() {
        return lettre;
    }

    public void setLettre(String lettre) {
        this.lettre = lettre;
        this.caractere = Caractere.valueOf(lettre);
    }

    public void setCouleurs(Couleurs couleurs) {
        this.couleurs = couleurs;
    }

    public void changerSaut(int n) {
        this.sautMuffin = n;
    }
}
