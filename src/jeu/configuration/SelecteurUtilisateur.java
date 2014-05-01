package jeu.configuration;

import devintAPI.FenetreAbstraite;
import jeu.configuration.selection.Selection;
import jeu.configuration.selection.SelectionUtilisateur;

import javax.swing.*;

/**
 * @author Jean-Christophe Isoard
 */
public class SelecteurUtilisateur extends FenetreAbstraite {

    /**
     * @param title : titre de la fen�tre
     */
    public SelecteurUtilisateur(String title) {
        super(title);
    }

    @Override
    protected void init() {
        Selection selection = new SelectionUtilisateur();
        selection.setModeMultiple(false);
        this.add(selection);
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
