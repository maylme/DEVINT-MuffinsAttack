package jeu.muffinattacks.infobar;

import sun.font.FontFamily;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jicé on 04/04/2014.
 */
public class Temps extends JPanel {
    private int temps;
    private Color texte;

    public Temps(int t) {
        this.temps = t;
        texte = Color.WHITE;

        this.setBackground(null);
        this.setPreferredSize(new Dimension(100,84));
    }

    public void changeCouleur(Color texte) {
        this.texte = texte;
    }

    public void update(int t) {
        this.temps = t;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(texte);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 84));
        g.drawString(String.valueOf(temps), 0, 74);
    }
}
