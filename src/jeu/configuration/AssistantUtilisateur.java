package jeu.configuration;

import devintAPI.MenuAbstrait;
import jeu.MenuJeu;
import jeu.configuration.selection.SelectionUtilisateur;
import jeu.global.Utilisateur;
import jeu.sauvegarde.Restauration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jean-Christophe Isoard
 */
public class AssistantUtilisateur extends MenuAbstrait {
    private static HashMap<String, Utilisateur> listUtilisateur = Restauration.restoreUsers();

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
        return "../ressources/sons/menus/souris.wav";
    }

    @Override
    protected String wavRegleJeu() {
        return "../ressources/sons/menus/souris.wav";
    }

    public static Map<String, Utilisateur> getMapUtilisateur(){
        return listUtilisateur;
    }
}
