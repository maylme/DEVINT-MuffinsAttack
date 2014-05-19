package jeu.configuration.selection;

import jeu.configuration.selection.choix.Choix;
import jeu.configuration.selection.choix.ChoixCouleurs;
import jeu.global.couleurs.Couleurs;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jean-Christophe Isoard
 */
public class SelectionCouleurs extends Selection<Couleurs> {
    @Override
    public Collection<Choix> getChoix() {
        Collection<Choix> c = new ArrayList<>();
        for(Couleurs couleurs: Couleurs.values()) {
            c.add(new ChoixCouleurs(this,couleurs));
        }
        return c;
    }

    public Collection<Couleurs> getSelectedCouleurs() {
        return getSelectedChoix();
    }

    public void setConfigurationCouleurs(Collection<Couleurs> couleursPreferees) {
        setConfiguration(couleursPreferees);
    }
}
