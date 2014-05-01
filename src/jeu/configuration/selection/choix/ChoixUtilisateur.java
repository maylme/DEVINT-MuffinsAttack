package jeu.configuration.selection.choix;

import devintAPI.Preferences;
import jeu.MenuJeu;
import jeu.configuration.selection.SelectionUtilisateur;
import jeu.global.Utilisateur;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jean-Christophe Isoard
 */
public class ChoixUtilisateur extends Choix<Utilisateur> {
    /**
     * Instancie un choix avec pour paramètre son objet choisi
     * @param o         L'objet choisi
     */
    public ChoixUtilisateur(SelectionUtilisateur s, Utilisateur o) {
        super(s, o);
    }

    @Override
    public void init() {
        JLabel icon = new JLabel(Preferences.getIconsdirectory()+getObjetChoix().getIcon()+".ico");
        icon.setHorizontalAlignment(SwingConstants.CENTER);
        icon.setText(getObjetChoix().getIdentifiant());
        this.add(icon, BorderLayout.CENTER);
    }

    @Override
    public void refresh() {
        if(isSelected()) {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            this.setBorder(null);
        }
    }
}
