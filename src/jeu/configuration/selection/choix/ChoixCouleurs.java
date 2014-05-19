package jeu.configuration.selection.choix;

import jeu.configuration.selection.Selection;
import jeu.configuration.selection.SelectionCouleurs;
import jeu.global.couleurs.Couleurs;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jicé on 06/04/2014.
 */
public class ChoixCouleurs extends Choix<Couleurs> {
    private JLabel jlabel;

    public ChoixCouleurs(SelectionCouleurs s, Couleurs o) {
        super(s,o);
    }

    @Override
    public void init() {
        jlabel = new JLabel("Aa", SwingConstants.CENTER);
        jlabel.setForeground(getCouleurs().getCouleurTexte());
        jlabel.setFont(new java.awt.Font("Arial", Font.BOLD, 60));

        this.add(jlabel, BorderLayout.CENTER);

        this.setBackground(getCouleurs().getCouleurFond());
    }

    @Override
    public void refresh() {
        if(isSelected()) {
            this.setBorder(BorderFactory.
                    createCompoundBorder(BorderFactory.createLineBorder(getCouleurs().getCouleurFond(), 5),
                            BorderFactory.createLineBorder(getCouleurs().getCouleurTexte(), 20)));
        } else {
            this.setBorder(null);
        }
    }

    public Couleurs getCouleurs() {
        return getObjetChoix();
    }
}
