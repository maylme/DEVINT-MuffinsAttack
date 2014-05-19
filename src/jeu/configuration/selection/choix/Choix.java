package jeu.configuration.selection.choix;

import jeu.configuration.selection.Selection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Jean-Christophe Isoard
 */
public abstract class Choix<E> extends JPanel {
    private final Selection<E> selecteur;
    private boolean selected;
    private E objetChoix;

    /**
     * Instancie un choix avec pour paramètre son objet choisi
     *
     * @param o L'objet choisi
     */
    public Choix(Selection<E> selecteur, E o) {
        this.selecteur = selecteur;
        this.selected = false;
        this.objetChoix = o;

        this.setLayout(new BorderLayout());
        this.addMouseListener(new EcouteurSouris(this));
        init();
    }

    public Selection<E> getSelecteur() {
        return selecteur;
    }

    public abstract void init();

    public void setSelected() {
        this.selected = true;
        refresh();
    }

    public boolean isSelected() {
        return selected;
    }

    public void overlay() {
        if (selected) {
            setUnselected();
        } else {
            selecteur.nouveauSelectionne(objetChoix);
            setSelected();
        }
        refresh();
    }

    public abstract void refresh();

    public boolean isChoix(E choixPerso) {
        if (choixPerso.equals(objetChoix)) {
            return true;
        }
        return false;
    }

    public E getObjetChoix() {
        return objetChoix;
    }

    public void setUnselected() {
        this.selected = false;
        refresh();
    }

    private class EcouteurSouris extends MouseAdapter {
        private Choix<E> c;

        public EcouteurSouris(Choix<E> c) {
            this.c = c;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            c.overlay();
        }
    }
}
