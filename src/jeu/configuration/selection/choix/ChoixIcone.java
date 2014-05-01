package jeu.configuration.selection.choix;

import devintAPI.Preferences;
import jeu.configuration.NouvelUtilisateur;
import jeu.configuration.selection.Selection;
import jeu.configuration.selection.SelectionCouleurs;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jean-Christophe Isoard
 */
public class ChoixIcone extends Choix<String> {

    public ChoixIcone(Selection<String> n, String i) {
        super(n,i);
    }

    @Override
    public void init() {
        this.add(new JLabel(new ImageIcon(Preferences.getIconsdirectory()+getObjetChoix()+".ico")));
    }

    @Override
    public void refresh() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public String getIcone() {
        return getObjetChoix();
    }
}
