package jeu.configuration.selection;

import devintAPI.Preferences;
import jeu.configuration.selection.choix.Choix;
import jeu.configuration.selection.choix.ChoixIcone;
import jeu.global.ChargerAvatar;

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
        int limit = 18;
        for(String s: ChargerAvatar.recupererListeFichiers()) {
            liste.add(new ChoixIcone(this, s));
            limit--;
            if(limit == 0)
                break;
        }
        return liste;
    }
}
