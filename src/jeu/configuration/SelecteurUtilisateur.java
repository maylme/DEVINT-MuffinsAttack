package jeu.configuration;

import devintAPI.FenetreAbstraite;
import jeu.MenuJeu;
import jeu.configuration.selection.Selection;
import jeu.configuration.selection.SelectionUtilisateur;
import jeu.global.Utilisateur;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Collection;

/**
 * @author Jean-Christophe Isoard
 */
public class SelecteurUtilisateur extends FenetreAbstraite {
    private SelectionUtilisateur selection;

    /**
     * @param title : titre de la fen�tre
     */
    public SelecteurUtilisateur(String title) {
        super(title);
    }

    @Override
    protected void init() {
        selection = new SelectionUtilisateur();
        selection.setModeMultiple(false);
        this.add(selection);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Collection<Utilisateur> collection = selection.getSelectedChoix();
            if (!collection.isEmpty()) {
                (new MenuJeu(getTitle())).setUtilisateur(collection.iterator().next());
            }
        }
    }

    @Override
    protected String wavAide() {
        return null;
    }

    @Override
    protected String wavAccueil() {
        return null;
    }

    @Override
    protected String wavRegleJeu() {
        return null;
    }

    @Override
    public void changeColor() {

    }
}
