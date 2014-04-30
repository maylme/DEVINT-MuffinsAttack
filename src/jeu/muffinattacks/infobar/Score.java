package jeu.muffinattacks.infobar;

import jeu.global.couleurs.Couleurs;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jicé on 30/04/2014.
 */
public class Score extends JPanel {
    private int score;
    private int objectif;
    private Couleurs couleurs;
    private JLabel texte;

    public Score(int n) {
        this.score = 0;
        this.objectif = n;
        this.texte = new JLabel("SCORE 0");
        this.texte.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        this.texte.setHorizontalAlignment(SwingConstants.CENTER);
        this.couleurs = Couleurs.NOIRBLANC;
        this.add(texte);
        this.setBackground(null);
        this.setPreferredSize(new Dimension(200,80));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int atteint = score*360/objectif;
        int taille = this.getHeight()-10;
        g.setColor(couleurs.getCouleurTexte());
        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        g.drawArc(texte.getX()-(taille+30), 5, taille, taille, 90, 360);
        g.fillArc(texte.getX()-(taille+30), 5, taille, taille, 90, atteint);
    }

    public void setCouleurs(Couleurs couleurs) {
        this.couleurs = couleurs;
        this.texte.setForeground(couleurs.getCouleurTexte());
    }

    public void setScore(int score) {
        this.score = score;
        this.texte.setText("SCORE "+String.valueOf(score));
        repaint();
    }

    public void setObjectif(int objectif) {
        this.objectif = objectif;
        repaint();
    }
}
