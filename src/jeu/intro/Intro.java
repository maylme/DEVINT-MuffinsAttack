package jeu.intro;

import devintAPI.DevintFrameListener;
import devintAPI.FenetreAbstraite;
import jeu.global.couleurs.Couleurs;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Intro extends DevintFrameListener{
    //liste des images:
    private final String img0 = "../ressources/images/intro/image0-fini.png";
    private final String img1 = "../ressources/images/intro/image1-fini.png";
    private final String img2 = "../ressources/images/intro/image2-fini.png";
    private final String img3 = "../ressources/images/intro/image3-fini.png";
    private final String img4 = "../ressources/images/intro/image-4-fini.png";
    private final String img5 = "../ressources/images/intro/image-5-fini.png";


    // Label de L'image :
    private JLabel image;



    // appel au constructeur de la classe mère
    public Intro(String title) throws Exception{

            super(title);
        this.setBackground(Color.black);

            voix.playWav("../ressources/sons/intro1.wav");
            String path = img0;
            BufferedImage image = ImageIO.read(new File(path));
            Image contentPane = new Image(image);



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(contentPane);


        this.setLocation(100, 100);
        this.setVisible(true);

        Thread.sleep(10000); // suspendu pendant 35 seconde (chiffre en millisecondes)

        contentPane = new Image(ImageIO.read(new File(img1)));
        this.setContentPane(contentPane);
        this.revalidate();
        this.repaint();

        Thread.sleep(25000); // suspendu pendant 35 seconde (chiffre en millisecondes)
        voix.stop();

        voix.playWav("../ressources/sons/intro2.wav");

        contentPane = new Image(ImageIO.read(new File(img2)));
        this.setContentPane(contentPane);
        this.revalidate();
        this.repaint();


        Thread.sleep(9000); // suspendu pendant 9 seconde (chiffre en millisecondes)
        voix.stop();
        voix.playWav("../ressources/sons/intro3.wav");

        contentPane = new Image(ImageIO.read(new File(img3)));
        this.setContentPane(contentPane);
        this.revalidate();
        this.repaint();


        this.setBackground(Color.black);
        Thread.sleep(16000); // suspendu pendant 20 seconde (chiffre en millisecondes)
        voix.stop();
        voix.playWav("../ressources/sons/intro4.wav");
        contentPane = new Image(ImageIO.read(new File(img4)));
        this.setContentPane(contentPane);
        this.revalidate();
        this.repaint();


        Thread.sleep(13000); // suspendu pendant 2 seconde (chiffre en millisecondes)
        voix.stop();
        voix.playWav("../ressources/sons/intro5.wav");
        contentPane = new Image(ImageIO.read(new File(img5)));
        this.setContentPane(contentPane);
        this.revalidate();
        this.repaint();



        Thread.sleep(22000); // suspendu pendant 2 seconde (chiffre en millisecondes)

        //fermer la fenetre et appeller une autre fenetre.
    }

    /**
     * Pour modifier les couleurs de fond et de premier plan de la fenêtre Cette
     * fonction est appelée par la fonction "changeColor" de la classe
     * "Preferences" à chaque fois que l'on presse F3 on change la couleur du
     * texte principal
     *
     */
    @Override
    public void changeColor() {}

    // renvoie le fichier wave contenant le message d'accueil
    @Override
    protected String wavAccueil() {
        return null;
    }

    // renvoie le fichier wave contenant la règle du jeu
    @Override
    protected String wavRegleJeu() {
        return null;
    }

}
