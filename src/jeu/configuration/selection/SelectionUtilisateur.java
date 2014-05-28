package jeu.configuration.selection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import jeu.configuration.AssistantUtilisateur;
import jeu.configuration.selection.choix.Choix;
import jeu.configuration.selection.choix.ChoixIcone;
import jeu.configuration.selection.choix.ChoixUtilisateur;
import jeu.global.ChargerAvatar;
import jeu.global.Utilisateur;
import jeu.sauvegarde.Restauration;

/**
 * @author Jean-Christophe Isoard
 */
public class SelectionUtilisateur extends Selection<Utilisateur> {

     @Override
    public Collection<Choix> getChoix() {
        Collection<Choix> choixUtilisateurs = new ArrayList<>();
        Collection<Utilisateur> collectionUtilisateur = AssistantUtilisateur.getMapUtilisateur().values();
        Collection<String> listeAvatars = ChargerAvatar.recupererListeFichiers();
        for(Utilisateur u: collectionUtilisateur) {
            if(listeAvatars.contains(u.getIcone())) {
                choixUtilisateurs.add(new ChoixUtilisateur(this, u));
            }
        }
        return choixUtilisateurs;
    }
}
