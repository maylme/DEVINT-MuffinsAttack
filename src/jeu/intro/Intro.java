package jeu.intro;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Intro {
    //liste des images:
    private final String img1 = "ressources/images/intro/image1-fini.png";
    private final String img2 = "ressources/images/intro/image2-fini.png";
    private final String img3 = "ressources/images/intro/image3-fini.png";
    private final String img4 = "ressources/images/intro/image-4-fini.png";
    private final String img5 = "ressources/images/intro/image-5-fini.png";


    // Label de L'image :
    private JLabel image;


    // appel au constructeur de la classe mère
    public Intro() throws Exception{

        String path = img1;
        BufferedImage image = ImageIO.read(new File(path));
        Image contentPane = new Image(image);

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(contentPane);

        f.setSize(842, 596);
        f.setLocation(100, 100);
        f.setResizable(false);

        f.setVisible(true);



        Thread.sleep(2000); // suspendu pendant 2 seconde (chiffre en millisecondes)
        contentPane = new Image(ImageIO.read(new File(img2)));
        f.setContentPane(contentPane);
        f.revalidate();
        f.repaint();


        Thread.sleep(2000); // suspendu pendant 2 seconde (chiffre en millisecondes)
        contentPane = new Image(ImageIO.read(new File(img3)));
        f.setContentPane(contentPane);
        f.revalidate();
        f.repaint();


        Thread.sleep(2000); // suspendu pendant 2 seconde (chiffre en millisecondes)
        contentPane = new Image(ImageIO.read(new File(img4)));
        f.setContentPane(contentPane);
        f.revalidate();
        f.repaint();


        Thread.sleep(2000); // suspendu pendant 2 seconde (chiffre en millisecondes)
        contentPane = new Image(ImageIO.read(new File(img5)));
        f.setContentPane(contentPane);
        f.revalidate();
        f.repaint();



        Thread.sleep(2000); // suspendu pendant 2 seconde (chiffre en millisecondes)

        //fermer la fenetre et appeller une autre fenetre.
    }

}