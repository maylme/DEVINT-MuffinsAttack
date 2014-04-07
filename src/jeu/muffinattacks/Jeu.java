package jeu.muffinattacks;

import devintAPI.FenetreAbstraite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Jeu extends FenetreAbstraite implements KeyListener {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random rand;
    private Vies vies;
    private Temps temps;
    private Monde monde;
    private int couleur;
    private JLabel status;

    private int points;
    private int tempsRestant;
    private int tempsTotal;
    private Timer timerPause;

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
        temps = new Temps(0);
        monde = new Monde(this, Couleur.NOIRBLANC);
        timerPause = new Timer();

        this.setLayout(new BorderLayout());

        JPanel statsJeu = new JPanel(new BorderLayout());
        statsJeu.setBackground(Color.BLACK);
        statsJeu.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        statsJeu.add(vies,BorderLayout.WEST);
        statsJeu.add(temps,BorderLayout.EAST);

        this.add(statsJeu, BorderLayout.NORTH);
        this.add(monde, BorderLayout.CENTER);
        this.add(status, BorderLayout.SOUTH);

        dire("Presse la touche ESPACE pour démarrer le jeu.");
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

    /**
     * Utilise sivox pour lire la phrase donnée en paramètres
     * <br />La phrase est lue aprés le paramètres secondes
     * @param chaine la phrase à lire
     * @param secondes le temps à attendre avant la lecture
     */
    public void direPause(final String chaine, double secondes) {
        TimerTask unpauseTask = new TimerTask() {
            @Override
            public void run() {
                dire(chaine);
            }
        };
        timerPause.schedule(unpauseTask, (int) secondes * 1000);
    }

    /**
     * Lit la lettre correspondante du fichier audio dans le dossier
     * <br />ressources/sons/alphabet/
     * @param lettre la lettre à dire
     * @param secondes le temps à attendre avant la lecture
     */
    public void direLettrePause(final String lettre, double secondes) {
        TimerTask unpauseTask = new TimerTask() {
            @Override
            public void run() {
                direLettre(lettre);
            }
        };
        timerPause.schedule(unpauseTask, (int) secondes * 1000);
    }

    /**
     * Joue l'enregistrement du dossier donné par le fichier string.wav
     * <br />Après le temps donnée secondes
     * @param string Enregistrement
     * @param secondes
     */
    public void jouerEnregistrementPause(final String string, int secondes) {
        TimerTask unpauseTask = new TimerTask() {
            @Override
            public void run() {
                jouerEnregistrement(string);
            }
        };
        timerPause.schedule(unpauseTask, (int) secondes * 1000);
    }

    public void dire(String s) {
        voix.stop();
        voix.playShortText(s);
    }

    public void direLettre(String lettre) {
        voix.stop();
        String chemin = "../ressources/sons/alphabet/"+lettre.toLowerCase()+".wav";
        voix.playWav(chemin);
    }

    public void jouerEnregistrement(String nom) {
        voix.stop();
        voix.playWav("../ressources/sons/jeu/"+nom+".wav");
    }

    // renvoie le fichier wave contenant le message d'accueil
    @Override
    protected String wavAccueil() {
        return "";
    }

    // renvoie le fichier wave contenant la règle du jeu
    @Override
    protected String wavRegleJeu() {
        return "";
    }

    // renvoie le fichier wave contenant l'aide du jeu
    protected String wavAide() {
        return "../ressources/sons/aide.wav";
    }

    @Override
    public void changeColor() {
        if (couleur == Couleur.values().length) {
            couleur = 0;
        }
        Couleur c = Couleur.getOne(++couleur);
        this.setBackground(c.getCouleurFond());
        monde.setColors(c);
    }

    public JLabel getStatusBar() {
        return status;
    }

    public void updateStatusBar() {
        temps.update(tempsRestant);
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

        if(keycode == KeyEvent.VK_F1 || keycode == KeyEvent.VK_F2 || keycode == KeyEvent.VK_F3 || keycode == KeyEvent.VK_F4) {
            return;
        }

        if (!(monde.getStarted()) && (keycode == KeyEvent.VK_SPACE)) {
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
            timerPause.cancel();
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
        updateStatusBar();
    }

    public void viePerdue() {
        dire("Tu as perdu une vie ...");
        vies.viePerdue();
        updateStatusBar();
    }

    public void secondeEcoulee() {
        //TODO Ajouter des bips pour informer de l'écoulement du temps ?
        this.tempsRestant--;
        updateStatusBar();
    }

    public int getVies() {
        return vies.getVies();
    }

    public boolean getTimeOut() {
        return tempsRestant <= 0;
    }

    public void timeReset() {
        this.tempsRestant = tempsTotal;
        updateStatusBar();
    }
}
