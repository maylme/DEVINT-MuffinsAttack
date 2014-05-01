package jeu.configuration;

import devintAPI.MenuAbstrait;
import jeu.configuration.selection.SelectionUtilisateur;

/**
 * @author Jean-Christophe Isoard
 */
public class AssistantUtilisateur extends MenuAbstrait {
    /**
     * constructeur,
     */
    public AssistantUtilisateur(String title) {
        super(title);
    }

    @Override
    protected String[] nomOptions() {
        return new String[] {"Nouveau joueur","Choisir son image","Quitter"};
    }

    @Override
    protected void lancerOption(int i) {
        switch(i) {
            case 0:
                new NouvelUtilisateur(getTitle());
                break;
            case 1:
                new SelecteurUtilisateur(getTitle());
                break;
            case 2:
                System.exit(0);
            default:
                break;
        }
    }

    @Override
    protected String wavAccueil() {
        return null;
    }

    @Override
    protected String wavRegleJeu() {
        return null;
    }
}
