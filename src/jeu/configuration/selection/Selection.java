package jeu.configuration.selection;

import jeu.configuration.selection.choix.Choix;
import jeu.configuration.selection.choix.ChoixCouleurs;
import jeu.global.couleurs.Couleurs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jean-Christophe Isoard
 */
public abstract class Selection<E> extends JPanel {
    private Collection<Choix> choix;
    private boolean modeMultiple;
    private boolean locked;

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
    }

    public abstract Collection<Choix> getChoix();

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

    private void resetAll() {
        for(Choix c:choix) {
            c.setUnselected();
        }
    }

    public void nouveauSelectionne(E nouveauChoix) {
        if(modeMultiple)
            return;
        Collection<E> config = new ArrayList<E>();
        config.add(nouveauChoix);
        setConfiguration(config);
    }

    public Collection<E> getSelectedChoix() {
        Collection<E> choixSelection = new ArrayList<E>();
        for (Choix<E> c : choix) {
            if (c.isSelected()) {
                choixSelection.add(c.getObjetChoix());
            }
        }
        return choixSelection;
    }

    public void setModeMultiple(boolean b) {
        this.modeMultiple = b;
    }
}
