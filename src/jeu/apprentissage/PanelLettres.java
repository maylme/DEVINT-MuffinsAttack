package jeu.apprentissage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PanelLettres extends JPanel {

    //liste des images:
    private JLabel a;
    private JLabel b;
    private Apprentissage apprentissage;

    public PanelLettres(Apprentissage app) {
        apprentissage = app;

        try {
            a = new JLabel(new ImageIcon(ImageIO.read(new File("../ressources/images/apprentissage/a.png"))));
            a.setName("a");

            b = new JLabel(new ImageIcon(ImageIO.read(new File("../ressources/images/apprentissage/b.png"))));
            b.setName("b");
        } catch (IOException e) {
            System.err.print("Image manquante ");
        }
        JPanel braille = new JPanel(new GridLayout(0, 10, 20, 20));
        for(Caractere c:Caractere.values()) {
            Braille lettre = new Braille(c);
            braille.add(lettre);
            //TODO methode à lettre pour changer sa couleur
        }
        this.setLayout(new FlowLayout());
        this.setBackground(Color.BLACK);
        braille.setBackground(this.getBackground());
        this.add(braille);
    }

    public void releaseLetter(int lettre) {
        switch (lettre) {
            case KeyEvent.VK_A:
                lettreLabelReleased(a);
                break;
            case KeyEvent.VK_B:
                lettreLabelReleased(b);
                break;
            default:
                break;
        }

    }

    // �v�nements clavier
    public void presseLetter(int lettre) {
        // cas particulier pour ce jeu : la touche A
        switch (lettre) {
            case KeyEvent.VK_A:
                lettreLabelSelected(a);
                apprentissage.dire("a");
                break;
            case KeyEvent.VK_B:
                lettreLabelSelected(b);
                apprentissage.dire("bé");
                break;
            default:
                break;
        }
    }

    /**
     * Methode appelée si il n'y a pas d'image. Change la couleur de la lettre
     *
     * @throws //PasDimageException
     */
    private void lettreLabelSelected(JLabel lettre) {
        String titreImage = lettre.getName() + "-selected.png";
        String chemin = "../ressources/images/apprentissage/" + titreImage;
        try {
            BufferedImage myPic = ImageIO.read(new File(chemin));
            lettre.setIcon(new ImageIcon(myPic));
        } catch (IOException e) {
            System.err.println("Image manquante: " + titreImage);
        }
    }

    private void lettreLabelReleased(JLabel lettre) {
        String titreImage = lettre.getName() + ".png";
        String chemin = "../ressources/images/apprentissage/" + titreImage;
        try {
            BufferedImage myPic = ImageIO.read(new File(chemin));
            lettre.setIcon(new ImageIcon(myPic));
        } catch (IOException e) {
            System.err.println("Image manquante: " + titreImage);
        }
    }

}
