package jeu.muffinattacks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Monde extends JPanel implements ActionListener {
    private final Jeu jeu;
    private Timer timer;
    private Muffin muffin;
    private boolean isStarted;
    private boolean isPaused;
    private Couleur couleur;
    private int count;
    private int delay;

    public Monde(Jeu jeu, Couleur couleur) {
        this.jeu = jeu;

        count = 0;

        isStarted = false;
        isPaused = false;

        this.couleur = couleur;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(couleur.getCouleurFond());
    }

    public void jouer() {
        delay = (int) (15000/(this.getHeight()-100*2));
        this.timer = new Timer(delay,this);

        isStarted = true;
        newMuffin();
        timer.start();
    }

    /**
     * Met en ou sort le jeu de pause
     */
    public void pause() {
        if (!isStarted) return;

        isPaused = !isPaused;
        if (isPaused) {
            timer.stop();
        } else {
            timer.start();
        }
        repaint();
    }

    public void newMuffin() {
        jeu.timeReset();
        char lettre = jeu.getRandomLetter();
        jeu.dire("Un nouveau mufine s'attake à la ville ! Appuie sur la touche "+lettre+", pour le détruire.");
        if (muffin == null) {
            muffin = new Muffin(lettre, 100, jeu.getWidth());
        } else {
            muffin.setLettre(lettre);
            muffin.replaceOnTop();
        }
    }

    public void muffinFall() {
        muffin.fallOnce();
        count++;
        if(count > 1000/delay) {
            jeu.secondeEcoulee();
            count=0;
        }
        repaint();
    }

    public void killMuffin() {
        jeu.ajouterPoint(1);
        newMuffin();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(couleur.getCouleurTexte());
        if (muffin != null) {
            muffin.paint(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(jeu.getVies() <= 0) {
            jeu.dire("Le jeu est terminé ! Tu n'as plus de vies.");
            timer.stop();
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
             jeu.dire("Non ça c'est la lettre "+lettre+", cherche encore.");
        }
    }

    public Muffin getMuffin() {
        return muffin;
    }
}