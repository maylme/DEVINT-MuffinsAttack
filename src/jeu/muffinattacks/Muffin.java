package jeu.muffinattacks;

import jeu.global.caracteres.Caractere;
import jeu.global.couleurs.Couleurs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author Jean-Christophe Isoard
 */
public class Muffin {
    private static final String srcImageMuffin = "../ressources/images/muffinsattack/muffin.png";
    private static BufferedImage originalMuffinImage;
    private static BufferedImage coloredMuffinImage;
    private static Random rand;
    private String lettre;
    private Point position;
    private int taille;
    private Dimension tailleCaractere;
    private Couleurs couleurs;
    private Caractere caractere;
    private int sautMuffin;

    /**
     * Instancie un muffin non affiché de taille 100px
     */
    public Muffin() {
        rand = new Random();
        this.lettre = null;
        this.caractere = null;
        this.setTaille(200);
        try {
            originalMuffinImage = ImageIO.read(new File(srcImageMuffin));
        } catch (IOException e) {
            System.out.println("Impossible de lire :"+srcImageMuffin);
            originalMuffinImage = null;
        }
        coloredMuffinImage = null;
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
     * Change la taille du muffin
     */
    public void setTaille(int taille) {
        this.taille = taille;
        int hauteurBraille = taille/2;
        tailleCaractere = new Dimension(hauteurBraille - hauteurBraille/3, hauteurBraille);
    }

    /**
     * Renvoie la taille du muffin
     *
     * @return la largeur/hauteur occupée par le muffin
     */
    public int getTaille() {
        return taille;
    }

    private void dessinerMuffin(Graphics g, Point position) {
        int x = (int) position.getX();
        int y = (int) position.getY();
        g.setColor(couleurs.getCouleurTexte());
        if(originalMuffinImage == null) {
            g.fillRect(x, y, taille, taille);
        } else {
            if(coloredMuffinImage != null) {
                g.drawImage(coloredMuffinImage, x, y, taille, taille, null);
            } else {
                g.drawImage(originalMuffinImage, x, y, taille, taille, null);
            }
        }
    }

    /**
     * Redessine le muffin de façon réduite à chaque appel
     * <br />Pour réinitialiser l'action de cette fonction, il faut réinitialiser la variable effacement à 0
     * @param g Le Graphics sur lequel dessiner le muffin
     */
    public void dessineMuffinPartiel(Graphics g, int efface) {
        int x1 = (int) position.getX();
        int y1 = (int) position.getY()+efface;
        int x2 = taille;
        int y2 = taille-efface*2;
        g.setColor(couleurs.getCouleurTexte());
        if(originalMuffinImage == null) {
            g.fillRect(x1, y1, x2, y2);
        } else {
            if(coloredMuffinImage != null) {
                g.drawImage(coloredMuffinImage, x1, y1, x2, y2, null);
            } else {
                g.drawImage(originalMuffinImage, x1, y1, x2, y2, null);
            }
        }
        dessinerCaractere(g);
    }

    /**
     * Méthode à appeler sur le Graphics du panneau sur lequel on veut dessiner le muffin
     * @param g Le Graphics sur lequel dessiner le muffin
     */
    public void paint(Graphics g) {
        if(lettre == null || caractere == null) {
            return;
        }
        dessinerMuffin(g, position);
        dessinerCaractere(g);
    }

    private void dessinerCaractere(Graphics g) {
        int x = (int) (position.getX() - tailleCaractere.getWidth());
        int y = (int) (position.getY() + tailleCaractere.getHeight());
        if(position.getX() < taille) {
            x = (int) position.getX() + taille;
        }
        g.setColor(couleurs.getCouleurFond());
        g.fillRect(x,y,(int) tailleCaractere.getWidth(), (int) tailleCaractere.getHeight());
        g.setColor(couleurs.getCouleurTexte());
        g.drawRect(x,y,(int) tailleCaractere.getWidth(), (int) tailleCaractere.getHeight());
        Caractere.paint(g, new Point(x,y), tailleCaractere, caractere, false, couleurs.getCouleurTexte(), null, 4);
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
        if(originalMuffinImage != null) {
            coloredMuffinImage = colorImage(originalMuffinImage);
        }
    }

    /**
     * Change la valeur de pas de déplacement de l'image (en pixels)
     * @param n le nombre de pixels dont le muffin devra se déplacer
     */
    public void changerSaut(int n) {
        this.sautMuffin = n;
    }

    private BufferedImage colorImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage nouvelleImage = deepCopy(image);
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                Color originalColor = new Color(nouvelleImage.getRGB(xx, yy), true);
                if(originalColor.equals(Color.WHITE) && originalColor.getAlpha() == 255) {
                    nouvelleImage.setRGB(xx, yy, couleurs.getCouleurFond().getRGB());
                }
                if(originalColor.equals(Color.BLACK) && originalColor.getAlpha() == 255) {
                    nouvelleImage.setRGB(xx, yy, couleurs.getCouleurTexte().getRGB());
                }
            }
        }
        return nouvelleImage;
    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}