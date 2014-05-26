package jeu.intro;

import devintAPI.DevintFrameListener;
import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;
import jeu.MenuJeu;
import jeu.global.couleurs.Couleurs;
import t2s.SIVOXDevint;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Intro extends JFrame implements Runnable {
    private SIVOXDevint voix = Preferences.getData().getVoice();
    private boolean finished;

    //liste des images:
    private final String img0 = "../ressources/images/intro/image0-fini.png";
    private final String img1 = "../ressources/images/intro/image1-fini.png";
    private final String img2 = "../ressources/images/intro/image2-fini.png";
    private final String img3 = "../ressources/images/intro/image3-fini.png";
    private final String img4 = "../ressources/images/intro/image-4-fini.png";
    private final String img5 = "../ressources/images/intro/image-5-fini.png";


    // Label de L'image :
    private JLabel image;



    // appel au constructeur la classe mère
    public Intro(MenuJeu parent) {
        this.setBackground(Color.BLACK);

        this.setLayout(new BorderLayout());

        this.image = new JLabel();
        this.add(image, BorderLayout.CENTER);

        this.image.setBackground(null);
        this.image.setHorizontalAlignment(SwingConstants.CENTER);

        this.addKeyListener(new EchapListener(parent));

        this.setExtendedState(MAXIMIZED_BOTH);

       // this.setSize(new Dimension(850,600));
        this.setLocation(100, 100);
        this.setVisible(true);
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public void run() {
        ImageIcon contentPane;

        voix.playWav("../ressources/sons/intro1.wav");
        String path = img0;
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane = new ImageIcon(image);
        this.image.setIcon(contentPane);


        try {
            Thread.sleep(10000); // suspendu pendant 35 seconde (chiffre en millisecondes)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            contentPane = new ImageIcon(ImageIO.read(new File(img1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image.setIcon(contentPane);
        this.revalidate();
        this.repaint();

        try {
            Thread.sleep(25000); // suspendu pendant 35 seconde (chiffre en millisecondes)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        voix.stop();

        voix.playWav("../ressources/sons/intro2.wav");

        try {
            contentPane = new ImageIcon(ImageIO.read(new File(img2)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image.setIcon(contentPane);
        this.revalidate();
        this.repaint();


        try {
            Thread.sleep(9000); // suspendu pendant 9 seconde (chiffre en millisecondes)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        voix.stop();
        voix.playWav("../ressources/sons/intro3.wav");

        try {
            contentPane = new ImageIcon(ImageIO.read(new File(img3)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image.setIcon(contentPane);
        this.revalidate();
        this.repaint();


        this.setBackground(Color.black);
        try {
            Thread.sleep(16000); // suspendu pendant 20 seconde (chiffre en millisecondes)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        voix.stop();
        voix.playWav("../ressources/sons/intro4.wav");
        try {
            contentPane = new ImageIcon(ImageIO.read(new File(img4)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image.setIcon(contentPane);
        this.revalidate();
        this.repaint();


        try {
            Thread.sleep(13000); // suspendu pendant 2 seconde (chiffre en millisecondes)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        voix.stop();
        voix.playWav("../ressources/sons/intro5.wav");
        try {
            contentPane = new ImageIcon(ImageIO.read(new File(img5)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image.setIcon(contentPane);
        this.revalidate();
        this.repaint();


        try {
            Thread.sleep(22000); // suspendu pendant 2 seconde (chiffre en millisecondes)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        voix.stop();
        this.finished = true;
        dispose();
    }

    class EchapListener extends KeyAdapter {

        private final MenuJeu parent;

        public EchapListener(MenuJeu parent) {
            this.parent = parent;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                parent.stopCinematique();
        }
    }
}
