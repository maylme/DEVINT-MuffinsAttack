package jeu;

import javax.swing.*;
import devintAPI.FenetreAbstraite;
import java.awt.*;

/**
 * Etend DevintFrame pour avoir un Frame et réagir aux événements claviers
 * Contient un exemple d'affichage d'image proportionnel à la taille de l'�cran
 *
 * @author helene
 *
 */
public class UneImage extends FenetreAbstraite {

    public UneImage(String title) {
        super(title);
    }

    // renvoie le fichier wave contenant le message d'accueil
    @Override
    protected String wavAccueil() {
        return "../ressources/sons/accueilImage.wav";
    }

    // renvoie le fichier wave contenant la règle du jeu
    @Override
    protected String wavRegleJeu() {
        return "../ressources/sons/accueilImage.wav";
    }

    // renvoie le fichier wave contenant la règle du jeu
    @Override
    protected String wavAide() {
        return "../ressources/sons/aide.wav";
    }

    // initialise le frame 
    @Override
    protected void init() {
        // FlowLayout : les composants ont leur taille fixée par setPreferredSize
        // et sont ajoutés de gauche à droite, de haut en bas
        setLayout(new FlowLayout());

        // la largeur et la hauteur actuelle de la fen�tre
        // si vous fixez la taille des éléments graphiques 
        // faites le en utilisant des valeurs proportionnelles à la taille
        // de la fen�tre pour que diff�rentes r�solutions d'écran soient possibles
        int largeur = Toolkit.getDefaultToolkit().getScreenSize().width;
        int hauteur = Toolkit.getDefaultToolkit().getScreenSize().height;

        String texte = "\nIci le layout est un \"FlowLayout\". Les composants sont ajoutés de gauche à droite et de haut en bas.";
        texte += "\nLa taille des composants est celle de \"setPreferredSize\" ou bien la taille optimale pour obtenir un frame le plus petit possible.";
        texte += "\n\nVoici les personnages du jeu Léa et Théo, 2007.";
        JTextArea theoTexte = new JTextArea(texte);
        theoTexte.setLineWrap(true);
        theoTexte.setEditable(false);
        add(theoTexte);

        // une image, voir http://java.sun.com/docs/books/tutorial/uiswing/components/icon.html
        ImageIcon icon = new ImageIcon("../ressources/images/theo.JPG");
        texte = "Théo est dans un label a un fond bleu qui occupe la moitié de la largeur et le tiers de la hauteur.";
        // on met l'image dans un label
        JLabel jl = new JLabel(texte, icon, JLabel.CENTER);
        jl.setAutoscrolls(true);
        jl.add(new Scrollbar());
        // fond bleu
        jl.setBackground(Color.BLUE);
        //composant opaque pour voir le fond bleu
        jl.setOpaque(true);
        // (largeur de la fenetre)/2 et (hauteur fenetre)/3
        jl.setPreferredSize(new Dimension(largeur / 2, hauteur / 3));
        add(jl);

        // Léa
        icon = new ImageIcon("../ressources/images/lea.JPG");
        texte = "Ceci est Léa";
        JLabel jl2 = new JLabel(texte, icon, JLabel.CENTER);
        add(jl2);
    }

    @Override
    /**
     * pour cette fenêtre, changer la couleur n'a pas de sens, alors la méthode
     * ne fait rien
     */
    public void changeColor() {
        // TODO Auto-generated method stub
    }

}
