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
    private int vies;
    private int tempsRestant;

    /**
     * @param title : titre de la fenetre
     */
    public Jeu(String title) {
        super(title);
    }

    @Override
    protected void init() {
        points = 0;
        vies = 3;
        tempsRestant = 15;

        rand = new Random();
        status = new JLabel("Vies:"+vies+" Points:"+points+" Temps restant:"+tempsRestant);
        monde = new Monde(this, Couleur.NOIRBLANC);

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
        dire("Tu cherche la lettre "+monde.getMuffin().getLettre());
        return "";
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

    public void updateStatusBar() {
        status.setText("Vies:"+vies+" Points:"+points+" Temps restant:"+tempsRestant);
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

    public void viePerdue() {
        this.vies--;
    }

    public void secondeEcoulee() {
        this.tempsRestant--;
    }

    public int getVies() {
        return vies;
    }

    public boolean getTimeOut() {
        return tempsRestant < 0;
    }

    public void timeReset() {
        this.tempsRestant = 15;
    }
}
