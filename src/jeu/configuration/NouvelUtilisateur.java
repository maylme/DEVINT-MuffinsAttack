package jeu.configuration;

import devintAPI.FenetreAbstraite;
import jeu.MenuJeu;
import jeu.configuration.selection.Selection;
import jeu.configuration.selection.SelectionAvatar;
import jeu.configuration.selection.SelectionCouleurs;
import jeu.global.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;

/**
 * @author Jean-Christophe Isoard
 */
public class NouvelUtilisateur extends FenetreAbstraite {
    private enum Etape {AVATAR, NOM, COULEURS}

    private Etape etape = Etape.AVATAR;

    private Utilisateur utilisateur;
    private EntrerNom formulaireNom;
    private Selection selection;

    /**
     * @param title : titre de la fen�tre
     */
    public NouvelUtilisateur(String title) {
        super(title);
    }

    @Override
    protected void init() {
        this.setLayout(new BorderLayout());
        formulaireNom = new EntrerNom(this);
        selection = new SelectionAvatar();
        selection.setModeMultiple(false);
        this.add(selection, BorderLayout.CENTER);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Collection<String> collection = selection.getSelectedChoix();
            if (!collection.isEmpty()) {
                creerUtilisateur(collection);
            }
        }
    }

    /**
     * Procéde aux étapes de la création de l'utilisateur avec la collection récupérée à l'étape
     *
     * @param collection la collection des choix fait par l'utilisateur à l'étape
     */
    private void creerUtilisateur(Collection collection) {
        switch (etape) {
            case AVATAR:
                // on créer un nouvel utilisateur avec son icone
                String icone = (String) collection.iterator().next();
                utilisateur = new Utilisateur(icone);
                this.remove(selection);
                // on passe à l'étape couleurs
                etape = Etape.COULEURS;
                selection = new SelectionCouleurs();
                this.add(selection);
                revalidate();
                break;
            case COULEURS:
                // on associe les couleurs choisies de l'utilisateur
                utilisateur.setCouleursPreferees(collection);
                this.dispose();
                // on lance le menu du jeu
                (new MenuJeu(getTitle())).setUtilisateur(utilisateur);
                break;
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
