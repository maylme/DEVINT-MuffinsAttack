package jeu.apprentissage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PanelLettres extends JPanel {
    private Apprentissage apprentissage;
    private Map<Integer,Braille> lettres;

    public PanelLettres(Apprentissage app) {
        apprentissage = app;

        this.lettres = new HashMap<Integer, Braille>();

        JPanel braille = new JPanel(new GridLayout(0, 10, 20, 20));
        for(Caractere c:Caractere.values()) {
            Braille lettre = new Braille(c, app.getBackground());
            lettres.put(c.getKey(),lettre);
            braille.add(lettre);
        }
        this.setLayout(new FlowLayout());
        this.setBackground(Color.BLACK);
        braille.setBackground(this.getBackground());
        this.add(braille);
    }

    public void changeColor(Color texte, Color fond) {
        this.setForeground(texte);
        this.setBackground(fond);
        for(Braille b:lettres.values()) {
            b.changeCouleurs(texte,texte.darker().darker(),fond);
        }
    }

    public void changeLetterColor(int lettre) {
        Braille selected = lettres.get(lettre);
        if(selected != null) {
            selected.changeCouleur();
        } else {
            System.out.println("la touche "+lettre+" n'est pas référencée");
        }
    }
}
