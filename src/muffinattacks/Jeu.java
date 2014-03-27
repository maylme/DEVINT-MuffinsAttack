package muffinattacks;

import devintAPI.FenetreAbstraite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Jeu extends FenetreAbstraite implements KeyListener {
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random rand;
    private Monde monde;
    private int couleur;
    private JLabel status;
    private int points;

    /**
     * @param title : titre de la fenetre
     */
    public Jeu(String title) {
        super(title);
    }

    @Override
    protected void init() {
        status = new JLabel("Etat du jeu");
        monde = new Monde(this, Couleur.NOIRBLANC);
        rand = new Random();

        this.setLayout(new BorderLayout());
        this.add(status, BorderLayout.NORTH);
        this.add(monde, BorderLayout.CENTER);
        dire("Presse la touche EFFE dice pour démarrer le jeu.");
    }

    public void dire(String s) {
        voix.playShortText(s);
    }

    // renvoie le fichier wave contenant le message d'accueil
    protected String wavAccueil() {
        return "ressources/sons/accueil.wav";
    }

    // renvoie le fichier wave contenant la r�gle du jeu
    protected String wavRegleJeu() {
        return "../ressources/sons/aideF1.wav";
    }

    // renvoie le fichier wave contenant la r�gle du jeu
    protected String wavAide() {
        return "ressources/sons/aide.wav";
    }

    @Override
    public void changeColor() {
        if(couleur == Couleur.values().length) {
            couleur = 0;
        }
        monde.setColors(Couleur.getOne(++couleur));
    }

    public JLabel getStatusBar() {
        return status;
    }

    public boolean isInAlphabet(char lettre) {
        return alphabet.contains(String.valueOf(lettre));
    }

    public char getRandomLetter() {
        return alphabet.charAt(rand.nextInt(alphabet.length()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int keycode = e.getKeyCode();

        if(!(monde.getStarted()) && (keycode == KeyEvent.VK_F10)) {
            dire("Le jeu démarre.");
            monde.jouer();
            return;
        }

        if(!(monde.getStarted())) return;

        if (keycode == KeyEvent.VK_PAUSE) {
            monde.pause();
            if(monde.getPaused()) dire("Le jeu est en pause.");
            else dire("Le jeu reprends.");
            return;
        }

        if (monde.getPaused()) return;

        if(!isInAlphabet((char) keycode)) {
            dire("Attention, c'est une lettre que tu cherches.");
        } else {
            monde.lettreEntree((char) keycode);
        }
    }

    public void ajouterPoint(int i) {
        this.points++;
        status.setText("Points: "+points);
    }
}
