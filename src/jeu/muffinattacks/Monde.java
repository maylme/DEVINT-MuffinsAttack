package jeu.muffinattacks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

/**
 * Created by Jicé on 24/03/2014.
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

    private Couleur couleur;

    private int count;
    private int delay;
    private final static double pauseEntreMuffins = 1.5;

    public Monde(Jeu jeu, Couleur couleur) {
        this.jeu = jeu;
        this.couleur = couleur;

        count = 0;
        timerGraphic = new Timer(200, new GraphicsTime());
        timerPause = new java.util.Timer();
        timerEffacement = new Timer(100,new EffacementTime());
        this.effaceActuel = 0;

        muffin = new Muffin('A',100);

        isStarted = false;
        isPaused = true;

        this.setBorder(BorderFactory.createLineBorder(couleur.getCouleurTexte()));
        this.setBackground(couleur.getCouleurFond());
    }

    public void jouer(int t) {
        changerTemps(t);

        newMuffinPause(5);
        jeu.jouerEnregistrement("attention_muffins_attaquent_la_ville");
        jeu.jouerEnregistrementPause("Pour_les_detruires", 2);
    }

    public void changerTemps(int t) {
        delay = (int) ((t * 1000) / (this.getHeight() - (muffin.getTaille()+40)));
        this.timerGraphic.setDelay(delay);
    }

    public void arreter() {
        timerGraphic.stop();
        timerPause.cancel();
    }

    /**
     * Crée une pause dans le jeu avant de créer un nouveau muffin
     * @param seconds le temps (en secondes)
     */
    public void newMuffinPause(double seconds) {
        TimerTask unpauseTask = new TimerTask() {
            @Override
            public void run() {
                newMuffin();
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
        } else {
            timerGraphic.start();
        }
        repaint();
        System.out.println("Pause");
    }

    /**
     * Recycle l'objet Muffin existant en un muffin avec une nouvelle lettre
     * <br />Instancie un muffin si aucun n'existe
     */
    public void newMuffin() {
        if(!isStarted) {
            isStarted = true;
            timerGraphic.start();
        }
        if (jeu.getVies() <= 0) return;
        char lettre = jeu.getRandomLetter();
        jeu.timeReset();
        muffin.replaceOnTop(jeu.getWidth());
        muffin.setLettre(lettre);
        //jeu.jouerEnregistrement("vite_appuie_sur");
        jeu.direLettre(String.valueOf(muffin.getLettre()));
        System.out.println("Nouveau muffin lancé");
    }

    public void muffinFall() {
        muffin.moveOnce();
        count++;
        if (count > 1000 / delay) {
            count = 0;
            jeu.secondeEcoulee();
        }
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

    /**
     * Redessine le muffin de façon réduite à chaque appel
     * <br />Pour réinitialiser l'action de cette fonction, il faut réinitialiser la variable effacement à 0
     * @param g
     */
    private void dessineMuffinPartiel(Graphics g) {
        Point position = muffin.getPosition();
        int x1 = (int) position.getX();
        int y1 = (int) position.getY()+effaceActuel;
        int x2 = (int) muffin.getTaille();
        int y2 = (int) muffin.getTaille()-effaceActuel*2;
        g.setColor(Color.RED);
        g.fillRect(x1, y1, x2, y2);
        effaceActuel++;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(!isStarted) return;
        if(effacement) {
            dessineMuffinPartiel(g);
        } else {
            g.setColor(couleur.getCouleurTexte());
            if (muffin != null) {
                muffin.paint(g);
                g.setColor(couleur.getCouleurTexte());
                g.drawString(String.valueOf(muffin.getLettre()), this.getWidth() - 20, this.getHeight() - 20);
            }
        }
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
        } else {
            //TODO Voix quand ce n'est pas la bonne lettre

        }
    }

    public Muffin getMuffin() {
        return muffin;
    }

    public boolean getDemarre() {
        return isStarted;
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
