package jeu.configuration;

import devintAPI.MenuAbstrait;
import jeu.MenuJeu;
import jeu.configuration.selection.SelectionUtilisateur;
import jeu.global.Utilisateur;
import jeu.global.voix.Voix;
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
        return new String[] {"Nouveau joueur","Charger un joueur", "Partie Rapide (Invité)","Quitter"};
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
                Utilisateur invite = Restauration.restoreUsers().get("Invité");
                if(invite == null) {
                    invite = new Utilisateur("Invité");
                }
                new MenuJeu("Muffins Attack").setUtilisateur(invite);
                break;
            case 3:
                System.exit(0);
            default:
                break;
        }
    }

    @Override
    protected String wavAccueil() {
        voix.stop();
        return "../ressources/sons/assistantUtilisateur.wav";
    }

    @Override
    protected String wavRegleJeu() {
        Voix.dire("Si tu est Non Voyant, tu peux sélectionner, Partie Rapide, pour accéder directement au jeu.");
        return "";
    }

    public static Map<String, Utilisateur> getMapUtilisateur(){
        return listUtilisateur;
    }
}
