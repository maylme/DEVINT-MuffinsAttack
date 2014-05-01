package jeu.configuration.selection;

import devintAPI.Preferences;
import jeu.configuration.selection.choix.Choix;
import jeu.configuration.selection.choix.ChoixIcone;

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
        for(String s:recupererListeFichiers()) {
            liste.add(new ChoixIcone(this, s));
        }
        return null;
    }

    private Collection<String> recupererListeFichiers() {
        File repertoire = new File(Preferences.getIconsdirectory());
        if (!repertoire.isDirectory()) return null;
        String[] listeFichiers = repertoire.list();
        if (listeFichiers.length == 0)
            return null;
        Collection<String> listeFichiersIcones = new ArrayList<>();
        for (String fichier : listeFichiers) {
            if (fichier.contains(".ico")) {
                listeFichiersIcones.add(fichier.replaceAll(".ico",""));
            }
        }
        return listeFichiersIcones;
    }
}
