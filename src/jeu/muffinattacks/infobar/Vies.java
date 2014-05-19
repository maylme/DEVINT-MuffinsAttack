package jeu.muffinattacks.infobar;

import jeu.muffinattacks.Jeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jicé on 04/04/2014.
 */
public class Vies extends JPanel {
    public int VIESDEBUT = 3;

    private static final String fileImageCoeur = "../ressources/images/muffinsattack/coeur_86.png";
    private static final int ESPACEMENT = 10;
    private int vies;
    private BufferedImage coeurImg;
    private boolean imageChargee;
    private Jeu jeu;

    /**
     * Initialise un JPanel affichant le nombre de coeurs configuré par défaut
     * <br />La hauteur dépend de l'image mise dans le dossier ressources
     * @param jeu le jeu afin de jouer les enregistrements
     */
    public Vies(Jeu jeu) {
        this.jeu = jeu;
        this.vies = VIESDEBUT;
        this.imageChargee = false;
        try {
            this.coeurImg = ImageIO.read(new File(fileImageCoeur));
            this.imageChargee = true;
        } catch (IOException e) {
           System.out.println(fileImageCoeur+" n'a pas pu être chargé");
        }

        this.setBackground(null);
        this.setPreferredSize(new Dimension((coeurImg.getWidth()+ESPACEMENT)*vies, hauteurVies()));
    }

    /**
     * Attends le temps passé en paramètre (en secondes) avant d'annoncer et effectuer la perte d'une vie
     * @param wait le temps en secondes
     */
    public void viePerdue(int wait) {
        this.vies--;
        if(vies > 0) {
            jeu.jouerEnregistrementPause(String.valueOf(vies), wait);
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = ESPACEMENT;
        for(int i=0; i < vies; i++) {
            if(imageChargee) {
                g.drawImage(coeurImg, x, 0, null);
                x += coeurImg.getWidth()+ESPACEMENT;
            }
        }
    }

    /**
     * Revoie le nombre de vies actuel
     * @return le nombre de vies
     */
    public int getVies() {
        return vies;
    }

    public int hauteurVies() {
        return coeurImg.getHeight();
    }

    /**
     * Permet de mettre une valeur de nombre de vies à la main
     * @param n le nombre de vies à afficher
     */
    public void setVies(int n) {
        this.vies = n;
        repaint();
    }

    /**
     * Réinitialise le nombre de vies à celui du début de partie
     */
    public void resetVies() {
        this.vies = VIESDEBUT;
    }
}
