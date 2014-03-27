package muffinattacks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Monde extends JPanel implements ActionListener {
    private final Jeu jeu;
    private Timer timer;
    private final int muffinSpeed = 100; // pixel/grow by seconds
    private Muffin muffin;
    private JLabel status;
    private boolean isStarted;
    private boolean isPaused;
    private boolean isFallingFinished;
    private Couleur couleur;

    public Monde(Jeu jeu, Couleur couleur) {
        this.jeu = jeu;

        isStarted = false;
        isPaused = false;
        isFallingFinished = false;

        this.timer = new Timer(1000 / muffinSpeed, this);
        this.status = jeu.getStatusBar();
        this.couleur = couleur;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void jouer() {
        if (isPaused) return;
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
            status.setText("pause");
        } else {
            timer.start();
            status.setText(String.valueOf(timer.getDelay()));
        }
        repaint();
    }

    public void muffinTouchedGround() {
        isFallingFinished = true;
    }

    public void newMuffin() {
        char lettre = jeu.getRandomLetter();
        System.out.println(lettre);
        jeu.dire("Un nouveau mufine s'attake à la ville ! Appuie sur la touche "+lettre+". Pour le détruire.");
        if (muffin == null) {
            muffin = new Muffin(lettre, 40, 500);
        } else {
            muffin.setLettre(lettre);
            muffin.replaceOnTop();
        }
    }

    public void muffinFall() {
        if (muffin.toucheSol((int) this.getSize().getHeight() - 60)) {
            //jeu.dire("Mince. Le mufine a touché la ville.");
            muffinTouchedGround();
        }
        muffin.fallOnce();
        repaint();
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
        if (isFallingFinished) {
            isFallingFinished = false;
            newMuffin();
        } else {
            muffinFall();
        }
    }

    public void setColors(Couleur c) {
        this.couleur = c;
        this.setBackground(couleur.getCouleurFond());
    }


    public boolean getStarted() {
        return isStarted;
    }

    public boolean getPaused() {
        return isPaused;
    }

    public void lettreEntree(char lettre) {
        if (Character.compare(muffin.getLetter(), lettre) == 0) {
            jeu.dire("Le mufine est indestructible pour le moment. Mais c'est très bien !");
            muffin.killMuffin();
        }
    }
}
