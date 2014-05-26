package jeu.configuration.selection;

import jeu.configuration.selection.choix.Choix;
import jeu.configuration.selection.choix.ChoixCouleurs;
import jeu.global.ChangerCurseur;
import jeu.global.couleurs.Couleurs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Représente un panneau de selection d'un objet
 * @author Jean-Christophe Isoard
 */
public abstract class Selection<E> extends JPanel {
    private Collection<Choix> choix;
    private boolean modeMultiple;

    /**
     * Crée un panneau de selection quadrié
     * <br />Par défaut, la selection est multiple
     */
    public Selection() {
        this.choix = getChoix();
        this.modeMultiple = true;
        this.setLayout(new GridLayout(0, 6, 10, 10));
        for (Choix c:choix) {
            this.add(c);
        }
        new ChangerCurseur(this).grosseSouris();
    }

    /**
     * Doit renvoyer la collection des choix possibles (la représentation d'une case)
     */
    public abstract Collection<Choix> getChoix();

    /**
     * Permet de mettre les élements de la Collection d'objets contenu dans le panneau dans l'état selectionné
     * @param choixPersonnels la collection d'élements à passer en état sélectionné
     */
    public void setConfiguration(Collection<E> choixPersonnels) {
        resetAll();
        for (E choixPerso : choixPersonnels) {
            for (Choix c : choix) {
                if (c.isChoix(choixPerso)) {
                    c.setSelected();
                }
            }
        }
    }

    /**
     * Reinitialise tous les élements à l'état non selectionné
     */
    private void resetAll() {
        for(Choix c:choix) {
            c.setUnselected();
        }
    }

    /**
     * Met l'element passé en paramètre dans l'état selectionné
     * @param nouveauChoix l'element à passer en état sélectionné
     */
    public void nouveauSelectionne(E nouveauChoix) {
        if(modeMultiple)
            return;
        Collection<E> config = new ArrayList<E>();
        config.add(nouveauChoix);
        setConfiguration(config);
    }

    /**
     * Renvoie les élements selectionné dans une collection
     * @return les élement selectionnés sur le panneau
     */
    public Collection<E> getSelectedChoix() {
        Collection<E> choixSelection = new ArrayList<E>();
        for (Choix<E> c : choix) {
            if (c.isSelected()) {
                choixSelection.add(c.getObjetChoix());
            }
        }
        return choixSelection;
    }

    /**
     * Change le mode de selection
     * @param b booléen: true = multiple, false = un seul choix possible
     */
    public void setModeMultiple(boolean b) {
        this.modeMultiple = b;
    }
}
