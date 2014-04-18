package jeu.apprentissage;

import jeu.global.caracteres.Braille;
import jeu.global.caracteres.Caractere;
import jeu.global.couleurs.Couleurs;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class PanelLettres extends JPanel {
    private ArrayList<JLabel> alphaLettres;
    private JPanel braille;
    private Apprentissage apprentissage;
    private Map<Integer,Braille> lettres;
    private Braille selected;

    public PanelLettres(Apprentissage app) {
        apprentissage = app;

        this.alphaLettres = new ArrayList<JLabel>();
        this.lettres = new HashMap<Integer, Braille>();

        braille = new JPanel(new GridLayout(0, 10, 20, 20));
        braille.setBackground(null);

        this.setLayout(new FlowLayout());
        this.add(braille);
    }

    public void afficherLettres(int i) {
        for(Caractere c:Caractere.values()) {

            if (--i< 0){
                break;
            }

            //panel de braille + lettre (unitaire)
            JPanel uneLettre = new JPanel();
            uneLettre.setBackground(null);
            uneLettre.setLayout(new BorderLayout());

            // la lettre en version braille
            Braille lettre = new Braille(apprentissage, c, apprentissage.getBackground());
            lettres.put(c.getKey(),lettre);

            //la lettre en version alpha:
            String name =c.name();
            JLabel label = new JLabel(name);
            alphaLettres.add(label);
            label.setHorizontalAlignment(0); //centrage du text

            label.setForeground(apprentissage.getForeground()); //pourquoi c'est pas bleu?!
            label.setFont(new Font("Arial", Font.BOLD, 40));

            uneLettre.add(label, BorderLayout.NORTH);
            uneLettre.add(lettre, BorderLayout.SOUTH);

            braille.add(uneLettre);

        }
    }

    public void changeColor(Color texte, Color fond) {
        this.setForeground(texte);
        this.setBackground(fond);
        for(JLabel alphaLettre:alphaLettres) {
            alphaLettre.setForeground(texte);
        }
        for(Braille b:lettres.values()) {
            b.changeCouleurs(texte, Couleurs.revertColor(texte),fond);
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
