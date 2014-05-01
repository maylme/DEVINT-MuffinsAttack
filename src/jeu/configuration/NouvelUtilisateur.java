package jeu.configuration;

import devintAPI.FenetreAbstraite;
import jeu.MenuJeu;
import jeu.configuration.selection.choix.ChoixIcone;
import jeu.global.Utilisateur;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jean-Christophe Isoard
 */
public class NouvelUtilisateur extends FenetreAbstraite {
    /**
     * @param title : titre de la fen�tre
     */
    public NouvelUtilisateur(String title) {
        super(title + "\n Nouveau joueur");
    }

    @Override
    protected void init() {

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
