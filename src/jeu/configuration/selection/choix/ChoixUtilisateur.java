package jeu.configuration.selection.choix;

import jeu.configuration.selection.SelectionUtilisateur;
import jeu.global.Utilisateur;

import javax.swing.*;

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
        this.add(new JLabel(getObjetChoix().getIdentifiant()));
    }

    @Override
    public void refresh() {

    }
}
