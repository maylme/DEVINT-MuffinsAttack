package jeu.muffinattacks.infobar;

import jeu.global.couleurs.Couleurs;
import jeu.global.difficultes.Niveau;
import jeu.muffinattacks.Jeu;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jean-Christophe Isoard
 */
public class Niveaux extends JPanel {
    private JLabel niveau;
    private Score score;
    private String temp;
    private Jeu jeu;

    public Niveaux(Jeu jeu) {
        this.jeu = jeu;
        this.temp = null;
        this.niveau = new JLabel("NOUVEAU JEU");
        this.niveau.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        this.niveau.setHorizontalAlignment(SwingConstants.CENTER);
        this.score = new Score(100);

        this.setLayout(new BorderLayout());
        this.add(niveau,BorderLayout.WEST);
        this.add(score, BorderLayout.CENTER);
        this.setBackground(null);
    }

    public void setScore(int n) {
        this.score.setScore(n);
    }

    public void setCouleurs(Couleurs couleurs) {
        niveau.setForeground(couleurs.getCouleurTexte());
        score.setCouleurs(couleurs);
    }

    public void setNiveau(Niveau niveau) {
        this.niveau.setText(" - Niveau: "+niveau.name()+"");
        this.score.setObjectif(niveau.getObjectif());
    }
}
