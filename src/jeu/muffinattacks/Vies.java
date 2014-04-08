package jeu.muffinattacks;

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
    private static final String fileImageCoeur = "../ressources/images/muffinsattack/coeur.png";
    private static final int ESPACEMENT = 10;
    private int vies;
    private BufferedImage coeurImg;
    private boolean imageChargee;
    private Jeu jeu;

    public Vies(Jeu jeu) {
        this.jeu = jeu;
        this.vies = 3;
        this.imageChargee = false;
        try {
            this.coeurImg = ImageIO.read(new File(fileImageCoeur));
            this.imageChargee = true;
        } catch (IOException e) {
           System.out.println(fileImageCoeur+" n'a pas pu être chargé");
        }

        this.setBackground(null);
        this.setPreferredSize(new Dimension((coeurImg.getWidth()+ESPACEMENT)*vies, 40));
    }

    public void viePerdue() {
        this.vies--;
        jeu.jouerEnregistrementPause(String.valueOf(vies),1);
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

    public int getVies() {
        return vies;
    }

    public void setVies(int n) {
        this.vies = n;
        repaint();
    }
}
