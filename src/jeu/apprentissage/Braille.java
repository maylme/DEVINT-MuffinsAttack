package jeu.apprentissage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jicé on 31/03/2014.
 */
public class Braille extends JPanel {
    Caractere caractere;
    int espacementPoints = 4;
    Color defaut;
    Color change;
    Boolean couleurChangee;
    Apprentissage apprentissage;
    private static final Point positionBraille = new Point(0,0);

    public Braille(Apprentissage a, Caractere c, Color defaut, Color change, Color fond) {
        this.caractere = c;
        this.couleurChangee = false;
        this.apprentissage = a;

        this.setPreferredSize(new Dimension(80, 120));
        changeCouleurs(defaut, change, fond);
    }

    public Braille(Apprentissage a,Caractere c, Color fond) {
        this(a,c, Color.WHITE, Color.GRAY, fond);
    }

    public void changeCouleur() {
        couleurChangee = !couleurChangee;

        if (couleurChangee == true){
            apprentissage.direLettre(caractere.name());
        }
        this.repaint();
    }

    public void resetCouleur() {
        couleurChangee = false;
        this.repaint();
    }

    public void changeCouleurs(Color defaut, Color change, Color fond) {
        this.defaut = defaut;
        this.change = change;
        this.setBackground(fond);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Caractere.paint(g, positionBraille,this.getSize(),caractere,couleurChangee,defaut,change,espacementPoints);
    }

    public int getTouche() {
        return caractere.getKey();
    }
}
