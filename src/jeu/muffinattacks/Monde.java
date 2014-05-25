package jeu.muffinattacks;

import jeu.global.couleurs.Couleurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

/**
 * @author Jean-Christophe Isoard
 */
public class Monde extends JPanel {
    private final Jeu jeu;
    private javax.swing.Timer timerGraphic;
    private java.util.Timer timerPause;

    private Muffin muffin;
    private boolean effacement;
    private int effaceActuel;
    private javax.swing.Timer timerEffacement;

    private boolean isStarted;
    private boolean isPaused;

    private Couleurs couleurs;

    private int delay;
    private final static double pauseEntreMuffins = 1;

    public Monde(Jeu jeu, Couleurs couleurs) {
        this.jeu = jeu;
        this.couleurs = couleurs;

        timerGraphic = new Timer(0, new GraphicsTime());
        timerPause = new java.util.Timer();
        timerEffacement = new Timer(100,new EffacementTime());
        this.effaceActuel = 0;

        muffin = new Muffin();

        isStarted = false;
        isPaused = true;

        //this.setBorder(BorderFactory.createLineBorder(couleurs.getCouleurTexte()));
        this.setBackground(couleurs.getCouleurFond());
    }

    public void jouer(int t) {
        changerTemps(t);
        isStarted = true;
        timerGraphic.start();
        pause();
        newMuffinPause(5);
        jeu.jouerEnregistrement("debutjeu");
        jeu.jouerEnregistrementPause("Pour_les_detruires", 2);
    }

    /**
     * Change le temps de chute du muffin (en millisecondes)
     * @param t
     */
    public void changerTemps(int t) {
        delay = t/(this.getHeight()-muffin.getTaille());
        this.timerGraphic.setDelay(delay);
        if(delay == 0) {
            muffin.changerSaut((this.getHeight()-muffin.getTaille()) / t);
            delay = 1;
            this.timerGraphic.setDelay(0);
        }
    }

    public void arreter() {
        pause();
        timerGraphic.stop();
        isStarted = false;
        repaint();
    }

    /**
     * Crée une pause dans le jeu avant de créer un nouveau muffin
     * @param seconds le temps (en secondes)
     */
    public void newMuffinPause(double seconds) {
        TimerTask unpauseTask = new TimerTask() {
            @Override
            public void run() {
                newMuffin(); pause();
            }
        };
        pause();
        timerPause.schedule(unpauseTask, (int) seconds * 1000);
    }

    /**
     * Crée une pause de la durée donnée en paramètre
     * @param seconds le temps (en secondes)
     */
    public void attendre(double seconds) {
        TimerTask unpauseTask = new TimerTask() {
            @Override
            public void run() {
                pause();
            }
        };
        pause();
        timerPause.schedule(unpauseTask, (int) seconds * 1000);
    }

    /**
     * Met en ou sort le jeu de pause
     */
    public void pause() {
        if (!isStarted) return;

        isPaused = !isPaused;
        if (isPaused) {
            timerGraphic.stop();
            System.out.println("Pause");
        } else {
            timerGraphic.start();
            System.out.println("Reprise");

        }
        repaint();
    }

    /**
     * Recycle l'objet Muffin existant en un muffin avec une nouvelle lettre
     * <br />Instancie un muffin si aucun n'existe
     */
    public void newMuffin() {
        if (jeu.getVies() <= 0) return;
        String lettre = String.valueOf(jeu.getRandomLetter());
        jeu.timeReset();
        muffin.replaceOnTop(jeu.getWidth());
        muffin.setLettre(lettre);
        //jeu.jouerEnregistrement("vite_appuie_sur");
        if(jeu.aideActive()) {
            jeu.direLettre(String.valueOf(muffin.getLettre()));
        }
    }

    public void muffinFall() {
        muffin.moveOnce();
        jeu.tempsEcoule(delay);
        repaint();
    }

    public void killMuffin() {
        jeu.jouerEnregistrement("muffin_detruit");
        effacerMuffin();
        newMuffinPause(pauseEntreMuffins);
        jeu.ajouterPoint(1);
    }

    /**
     * Efface le muffin avec une animation graphique
     * <br />A appeler quand on a pressé sur la bonne touche
     */
    public void effacerMuffin() {
        int delay = (int) ((muffin.getTaille()/2)/(pauseEntreMuffins*1000));
        timerEffacement.setDelay(delay);
        timerEffacement.start();
        effacement = true;
        effaceActuel = 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(!isStarted) return;
        if(effacement) {
            muffin.dessineMuffinPartiel(g, effaceActuel);
            effaceActuel++;
        } else {
            if (muffin != null) {
                muffin.paint(g);
                g.setColor(couleurs.getCouleurTexte());
                // affiche la lettre en bas à droite de l'écran
                //g.drawString(String.valueOf(muffin.getLettre()), this.getWidth() - 20, this.getHeight() - 20);
            }
        }
    }

    public void setColors(Couleurs c) {
        this.couleurs = c;
        this.setBackground(couleurs.getCouleurFond());
        muffin.setCouleurs(c);
        repaint();
    }

    public boolean getStarted() {
        return isStarted;
    }

    public boolean getPaused() {
        return isPaused;
    }

    public void lettreEntree(char lettre) {
        if (Character.compare(muffin.getLettre().charAt(0),lettre) == 0) {
            killMuffin();
        } else {
            jeu.mauvaiseTouche();
        }
    }

    public Muffin getMuffin() {
        return muffin;
    }

    public void repeterLettre() {
        jeu.direLettre(muffin.getLettre());
    }

    private class EffacementTime implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(effaceActuel <= muffin.getTaille()/2) {
                repaint();
            } else {
                timerEffacement.stop();
                effacement=false;
                effaceActuel = 0;
            }
        }
    }

    private class GraphicsTime implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (jeu.getVies() <= 0) {
                jeu.jeuFini();
            } else if (jeu.getTimeOut()) {
                jeu.timeOut();
            } else {
                muffinFall();
            }
        }
    }
}
