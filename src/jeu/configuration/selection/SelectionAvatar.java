package jeu.configuration.selection;

import devintAPI.Preferences;
import jeu.configuration.AssistantUtilisateur;
import jeu.configuration.selection.choix.Choix;
import jeu.configuration.selection.choix.ChoixIcone;
import jeu.global.ChargerAvatar;
import jeu.global.Utilisateur;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Jean-Christophe Isoard
 */
public class SelectionAvatar extends Selection<String> {
    @Override
    public Collection<Choix> getChoix() {
        Collection<Choix> liste = new ArrayList<>();
        Collection<String> iconesUtilisees = getIconesUtilisees();
        int limit = 18;
        for(String s: ChargerAvatar.recupererListeFichiers()) {
            if(!iconesUtilisees.contains(s)) {
                liste.add(new ChoixIcone(this, s));
                limit--;
                if(limit == 0)
                    break;
            }
        }
        return liste;
    }

    /**
     * Retourne dans une collection la liste des icones déjà utilisées par les utilisateurs enregistrés
     * @return
     */
    private static Collection<String> getIconesUtilisees() {
        Collection<String> iconesUtilisees = new ArrayList<>();
        for(Utilisateur u: AssistantUtilisateur.getMapUtilisateur().values()) {
            iconesUtilisees.add(u.getIcone());
        }
        return iconesUtilisees;
    }
}
