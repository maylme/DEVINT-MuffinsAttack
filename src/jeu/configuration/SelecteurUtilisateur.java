package jeu.configuration;

import devintAPI.FenetreAbstraite;
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
        this.add(new SelectionUtilisateur());
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
