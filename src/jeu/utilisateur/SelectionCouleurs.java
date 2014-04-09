package jeu.utilisateur;

import jeu.muffinattacks.Couleurs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jicé on 06/04/2014.
 */
public class SelectionCouleurs extends JPanel {
    private Collection<ChoixCouleurs> choixCouleurs;

    public SelectionCouleurs() {
        this.choixCouleurs = new ArrayList<ChoixCouleurs>();

        for (Couleurs c : Couleurs.values()) {
            this.choixCouleurs.add(new ChoixCouleurs(c));
        }

        this.setLayout(new GridLayout(0, 6, 10, 10));
        for (ChoixCouleurs choix : choixCouleurs) {
            this.add(choix);
        }
    }

    public void setConfiguration(Collection<Couleurs> couleurs) {
        for (ChoixCouleurs cc : choixCouleurs) {
            ChoixCouleurs actual = cc;
            for (Couleurs c : couleurs) {
                if (actual.isCouleurs(c)) {
                    actual.setSelected();
                    System.out.println(actual.getCouleurs()+" ("+c+") set to "+actual.isSelected());
                }
            }
        }
    }

    public Collection<Couleurs> getSelectedCouleurs() {
        Collection<Couleurs> couleurs = new ArrayList<Couleurs>();
        for (ChoixCouleurs c : choixCouleurs) {
            if (c.isSelected()) {
                couleurs.add(c.getCouleur());
            }
        }
        return couleurs;
    }
}
