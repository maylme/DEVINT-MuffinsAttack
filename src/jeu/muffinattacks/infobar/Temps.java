package jeu.muffinattacks.infobar;

import jeu.global.couleurs.Couleurs;
import sun.font.FontFamily;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jicé on 04/04/2014.
 */
public class Temps extends JPanel {
    private JLabel temps;

    public Temps(int t) {
        this.temps = new JLabel(String.valueOf(t));
        this.temps.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        this.setBackground(null);
        this.add(temps,CENTER_ALIGNMENT);
    }

    public void setCouleurs(Couleurs couleurs) {
        this.temps.setForeground(couleurs.getCouleurTexte());
    }

    public void update(int t) {
        this.temps.setText(String.valueOf(t));
    }
}
