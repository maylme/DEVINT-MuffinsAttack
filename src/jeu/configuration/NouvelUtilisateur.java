package jeu.configuration;

import devintAPI.FenetreAbstraite;
import jeu.MenuJeu;
import jeu.configuration.selection.SelectionAvatar;
import jeu.configuration.selection.choix.ChoixIcone;
import jeu.global.Utilisateur;

import javax.swing.*;
import java.awt.*;
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
        this.setLayout(new BorderLayout());
        SelectionAvatar selectionAvatar = new SelectionAvatar();
        selectionAvatar.setModeMultiple(false);
        this.add(selectionAvatar, BorderLayout.CENTER);
        this.add(new JButton("Valider"), BorderLayout.SOUTH);
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
