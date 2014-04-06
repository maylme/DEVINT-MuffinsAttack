package jeu.apprentissage;

import jeu.muffinattacks.Couleur;

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
    private JPanel braille;
    private Apprentissage apprentissage;
    private Map<Integer,Braille> lettres;
    private Braille selected;

    public PanelLettres(Apprentissage app) {
        apprentissage = app;

        this.lettres = new HashMap<Integer, Braille>();

        braille = new JPanel(new GridLayout(0, 10, 20, 20));
        braille.setBackground(null);
        for(Caractere c:Caractere.values()) {
            //panel de braille + lettre (unitaire)
            JPanel uneLettre = new JPanel();
            uneLettre.setBackground(null);
            uneLettre.setLayout(new BorderLayout());

            // la lettre en version braille
            Braille lettre = new Braille(app, c, app.getBackground());
            lettres.put(c.getKey(),lettre);

            //la lettre en version alpha:
            String name =c.name();
            JLabel alphaLettre = new JLabel(name);
            alphaLettre.setHorizontalAlignment(0); //centrage du text

            //NE MARCHE PAS////////////////////////////////////////////////////////////////////////////
            alphaLettre.setForeground(app.getForeground()); //ne marche pas: accorder la couleur
            Font font = new Font("Arial",Font.BOLD,12);
            alphaLettre.setFont(font);
            ///////////////////////////////////////////////////////////////////////////////////////////

            uneLettre.add(alphaLettre, BorderLayout.NORTH);
            uneLettre.add(lettre, BorderLayout.SOUTH);

            braille.add(uneLettre);

        }
        this.setLayout(new FlowLayout());
        this.add(braille);

    }

    public void changeColor(Color texte, Color fond) {
        this.setForeground(texte);
        this.setBackground(fond);
        for(Braille b:lettres.values()) {
            b.changeCouleurs(texte, Couleur.revertColor(texte),fond);
        }
    }

    public void changeLetterColor(int lettre) {
        if(selected != null) {
            if(selected.getTouche() != lettre) {
                selected.resetCouleur();
            }
        }
        selected = lettres.get(lettre);
        if(selected != null) {
            selected.changeCouleur();
        } else {
            System.out.println("la touche "+lettre+" n'est pas référencée");
        }
    }
}
