package jeu.muffinattacks;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Monde extends JPanel implements ActionListener {

    private final Jeu jeu;
    private javax.swing.Timer timerGraphic;
    private java.util.Timer timerPause;
    private Muffin muffin;
    private boolean isStarted;
    private boolean isPaused;
    private Couleur couleur;
    private int count;
    private int delay;

    public Monde(Jeu jeu, Couleur couleur) {
        this.jeu = jeu;

        count = 0;
        timerPause = new java.util.Timer();

        isStarted = false;
        isPaused = false;

        this.couleur = couleur;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(couleur.getCouleurFond());
    }

    public void jouer() {
        delay = (int) (15000 / (this.getHeight() - 100 * 2));
        this.timerGraphic = new Timer(delay, this);

        isStarted = true;

        newMuffin();
        timerGraphic.start();
    }

    /**
     * Crée une pause (non annoncée) dans le jeu
     *
     * @param seconds le temps (en secondes)
     */
    public void pause(int seconds) {
        TimerTask unpauseTask = new TimerTask() {
            @Override
            public void run() {
                pause();
            }
        };
        System.out.println("Le jeu se met en pause pour " + seconds + " secondes");
        pause();
        timerPause.schedule(unpauseTask, seconds * 1000);
    }

    /**
     * Met en ou sort le jeu de pause
     */
    public void pause() {
        if (!isStarted) {
            return;
        }

        isPaused = !isPaused;
        if (isPaused) {
            timerGraphic.stop();
        } else {
            timerGraphic.start();
        }
        repaint();
    }

    public void newMuffin() {
        jeu.timeReset();
        char lettre = jeu.getRandomLetter();
        jeu.dire("Un nouveau mufine attake la ville ! Appuie sur la touche " + lettre + ", pour le détruire.");
        if (muffin == null) {
            muffin = new Muffin(lettre, 100, jeu.getWidth());
        } else {
            muffin.setLettre(lettre);
        }
        muffin.replaceOnTop();
    }

    public void muffinFall() {
        muffin.moveOnce();
        count++;
        if (count > 1000 / delay) {
            jeu.secondeEcoulee();
            count = 0;
        }
        repaint();
    }

    public void killMuffin() {
        jeu.ajouterPoint(1);
        pause(3);
        newMuffin();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(couleur.getCouleurTexte());
        if (muffin != null) {
            muffin.paint(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jeu.getVies() <= 0) {
            jeu.dire("Le jeu est terminé ! Tu n'as plus de vies.");
            timerGraphic.stop();
        } else if (jeu.getTimeOut()) {
            jeu.viePerdue();
            newMuffin();
        } else {
            muffinFall();
        }
        jeu.updateStatusBar();
    }

    public void setColors(Couleur c) {
        this.couleur = c;
        this.setBackground(couleur.getCouleurFond());
        repaint();
    }

    public boolean getStarted() {
        return isStarted;
    }

    public boolean getPaused() {
        return isPaused;
    }

    public void lettreEntree(char lettre) {
        if (Character.compare(muffin.getLettre(), lettre) == 0) {
            jeu.dire("Bien !");
            killMuffin();
        } else {
            jeu.dire("Non ça c'est la lettre " + lettre + ", cherche encore.");
        }
    }

    public Muffin getMuffin() {
        return muffin;
    }
}
