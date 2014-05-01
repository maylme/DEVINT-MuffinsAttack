package jeu.configuration.selection;

import jeu.configuration.selection.choix.Choix;
import jeu.configuration.selection.choix.ChoixIcone;
import jeu.configuration.selection.choix.ChoixUtilisateur;
import jeu.global.Utilisateur;
import jeu.sauvegarde.Restauration;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jean-Christophe Isoard
 */
public class SelectionUtilisateur extends Selection<Utilisateur> {
    @Override
    public Collection<Choix> getChoix() {
        Collection<Choix> choixUtilisateurs = new ArrayList<>();
        for(Utilisateur u: Restauration.loadUsers()) {
            choixUtilisateurs.add(new ChoixUtilisateur(this, u));
        }
        return choixUtilisateurs;
    }
}
