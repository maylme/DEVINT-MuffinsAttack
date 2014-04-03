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
        this.timerGraphic = new Timer(200, this);
        timerPause = new java.util.Timer();

        isStarted = false;
        isPaused = false;

        this.couleur = couleur;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(couleur.getCouleurFond());
    }

    public void jouer(int t) {
        changerTemps(t);
        newMuffin();
        timerGraphic.start();
        isStarted = true;
    }

    public void changerTemps(int t) {
        delay = (int) ((t*1000) / (this.getHeight() - 40));
        this.timerGraphic.setDelay(delay);
    }

    public void arreter() {
        timerGraphic.stop();
        timerPause.cancel();
    }

    /**
     * Crée une pause dans le jeu avant de créer un nouveau muffin
     *
     * @param seconds le temps (en secondes)
     */
    public void pauseNewMuffin(int seconds) {
        TimerTask unpauseTask = new TimerTask() {
            @Override
            public void run() {
                newMuffin();
                pause();
            }
        };
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
        if(jeu.getVies() <= 0) return;
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
        pauseNewMuffin(3);
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
            // on fait une pause de 3 secondes pour ne pas trop perturber le joueur
            pauseNewMuffin(3);
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
            killMuffin();
            jeu.dire("C'est très bien !");
        } else {
            jeu.dire("Non ça c'est la lettre " + lettre + ", cherche encore.");
        }
    }

    public Muffin getMuffin() {
        return muffin;
    }
}
