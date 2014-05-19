package jeu.muffinattacks;

import jeu.global.couleurs.Couleurs;
import jeu.global.difficultes.Niveau;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jean-Christophe Isoard
 */
public class Scores extends JPanel {
    private static final int ESPACEMENT = 20;
    private static final String MODELE = "Niveau 0 : XXX";
    private Map<Niveau, Integer> meilleursScores;

    public Scores() {
        this.meilleursScores = new HashMap<Niveau, Integer>();
        this.meilleursScores.put(Niveau.UN, 0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(this.getForeground());
        Niveau[] niveaux = Niveau.values();

        int hauteurString = this.getHeight() / (niveaux.length+1) - ESPACEMENT;
        g.setFont(new Font("Arial", Font.PLAIN, hauteurString));

        Font font = getFont();
        FontMetrics fm = getFontMetrics(font);
        int size = fm.stringWidth(MODELE);

        int x = (this.getHeight() - size) / 2;
        int y = 0;

        g.drawString("Tes meilleurs scores :", 0, hauteurString);
        y += hauteurString;
        for (Niveau n : niveaux) {
            y += hauteurString;
            g.drawString("Niveau " + n.toString() + " : " + meilleursScores.get(n), x, y);
        }
    }

    public void setMeilleursScores(Map<Niveau, Integer> meilleursScores) {
        this.meilleursScores = meilleursScores;
        repaint();
    }

    public void changeCouleurs(Couleurs c) {
        this.setBackground(c.getCouleurFond());
        this.setForeground(c.getCouleurTexte());
    }
}
