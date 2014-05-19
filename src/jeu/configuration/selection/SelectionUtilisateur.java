package jeu.configuration.selection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import jeu.configuration.selection.choix.Choix;
import jeu.configuration.selection.choix.ChoixIcone;
import jeu.configuration.selection.choix.ChoixUtilisateur;
import jeu.global.Utilisateur;
import jeu.sauvegarde.Restauration;

/**
 * @author Jean-Christophe Isoard
 */
public class SelectionUtilisateur extends Selection<Utilisateur> {
   
    private static HashMap<String, Utilisateur> listUtilisateur = Restauration.restoreUsers();
    
     @Override
    public Collection<Choix> getChoix() {
        Collection<Choix> choixUtilisateurs = new ArrayList<>();
        Collection<Utilisateur> collectionUtilisateur = listUtilisateur.values();
        for(Utilisateur u: collectionUtilisateur) {
            choixUtilisateurs.add(new ChoixUtilisateur(this, u));
        }
        return choixUtilisateurs;
    }
    
    public static HashMap<String, Utilisateur> getListUtilisateur(){
        return listUtilisateur;
    }
}
