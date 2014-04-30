package jeu.muffinattacks.infobar;

import jeu.global.difficultes.Niveau;
import jeu.muffinattacks.Jeu;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jean-Christophe Isoard
 */
public class Niveaux extends JPanel {
    private Jeu jeu;

    public Niveaux(Jeu jeu) {
        this.setBackground(null);
        this.jeu = jeu;
        this.setPreferredSize(new Dimension(0,0));
    }

    public void paint(Graphics g) {
        int x=0;
        int y=0;
        int taille = 0;
        for(Niveau n: Niveau.values()) {
            g.drawRect(x,y,taille,taille);
            x+=taille;
            y+=taille;
        }
    }
}
