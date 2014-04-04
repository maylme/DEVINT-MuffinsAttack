package jeu.muffinattacks;

import devintAPI.FenetreAbstraite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Jeu extends FenetreAbstraite implements KeyListener {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random rand;
    private Vies vies;
    private Monde monde;
    private int couleur;
    private JLabel status;

    private int points;
    private int tempsRestant;
    private int tempsTotal;

    /**
     * @param title : titre de la fenetre
     */
    public Jeu(String title) {
        super(title);
    }

    @Override
    protected void init() {
        rand = new Random();
        status = new JLabel("Attends le démarrage du jeu");
        vies = new Vies(3);
        monde = new Monde(this, Couleur.NOIRBLANC);

        this.setLayout(new BorderLayout());

        JPanel statsJeu = new JPanel(new BorderLayout());
        statsJeu.add(status, BorderLayout.NORTH);
        statsJeu.add(vies,BorderLayout.CENTER);

        this.add(statsJeu, BorderLayout.NORTH);
        this.add(monde, BorderLayout.CENTER);

        dire("Presse la touche EFFE dice pour démarrer le jeu.");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void preparerJeu() {
        points = 0;
        tempsTotal = 15;
        updateStatusBar();
    }

    private void preparerMonde() {
        tempsRestant = tempsTotal;
        monde.changerTemps(tempsTotal);
    }

    public void dire(String s) {
        voix.stop();
        voix.playShortText(s);
    }

    // renvoie le fichier wave contenant le message d'accueil
    @Override
    protected String wavAccueil() {
        return "ressources/sons/accueil.wav";
    }

    // renvoie le fichier wave contenant la règle du jeu
    @Override
    protected String wavRegleJeu() {
        dire("Tu cherches la lettre " + monde.getMuffin().getLettre());
        return "";
    }

    // renvoie le fichier wave contenant l'aide du jeu
    protected String wavAide() {
        return "ressources/sons/aide.wav";
    }

    @Override
    public void changeColor() {
        if (couleur == Couleur.values().length) {
            couleur = 0;
        }
        monde.setColors(Couleur.getOne(++couleur));
    }

    public JLabel getStatusBar() {
        return status;
    }

    public void updateStatusBar() {
        status.setText("Temps total:"+tempsTotal+" Vies:" + vies.getVies() + " Points:" + points + " Temps restant:" + tempsRestant);
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

        if (!(monde.getStarted()) && (keycode == KeyEvent.VK_F10)) {
            preparerJeu();
            preparerMonde();
            dire("Le jeu démarre.");
            monde.jouer(tempsTotal);
            return;
        }

        if (!(monde.getStarted())) {
            return;
        }

        if(keycode == KeyEvent.VK_ESCAPE) {
            monde.arreter();
            dire("La partie a été interrompue. La reprise n'est pas encore gérée.");
            return;
        }

        if (keycode == KeyEvent.VK_PAUSE) {
            monde.pause();
            if (monde.getPaused()) {
                dire("Le jeu est en pause.");
            } else {
                dire("Le jeu reprends.");
            }
            return;
        }

        if (monde.getPaused()) {
            return;
        }

        if(keycode == KeyEvent.VK_UP) {
            tempsTotal += 3;
            tempsRestant += 3;
            monde.changerTemps(tempsTotal);
            return;
        }

        if(keycode == KeyEvent.VK_DOWN) {
            if(tempsTotal > 3) {
                tempsTotal -= 3;
                tempsRestant -= 3;
                monde.changerTemps(tempsTotal);
            }
            return;
        }

        if (keycode == KeyEvent.VK_SPACE) {
            dire("Tu cherches la lettre " + monde.getMuffin().getLettre());
        } else {
            if (!isInAlphabet((char) keycode)) {
                dire("Attention, c'est une lettre que tu cherches.");
            } else {
                monde.lettreEntree((char) keycode);
            }
        }
    }

    public void ajouterPoint(int i) {
        this.points++;
    }

    public void viePerdue() {
        dire("Tu as perdu une vie ...");
        vies.viePerdue();
    }

    public void secondeEcoulee() {
        //TODO Ajouter des bips pour informer de l'écoulement du temps ?
        this.tempsRestant--;
    }

    public int getVies() {
        return vies.getVies();
    }

    public boolean getTimeOut() {
        return tempsRestant <= 0;
    }

    public void timeReset() {
        this.tempsRestant = tempsTotal;
    }
}
