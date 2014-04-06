package jeu.utilisateur;

import jeu.muffinattacks.Couleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Jicé on 06/04/2014.
 */
public class ChoixCouleur extends JPanel implements MouseListener {
    private Couleur couleurs;
    private static final int ESPACEMENT = 20;
    private boolean selected;

    public ChoixCouleur(Couleur c) {
        this.couleurs = c;
        this.selected = false;

        this.setBackground(c.getCouleurFond());
        this.setPreferredSize(new Dimension(100,0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(couleurs.getCouleurTexte());
        g.drawRect(ESPACEMENT, ESPACEMENT, this.getWidth()-ESPACEMENT, this.getHeight()-ESPACEMENT);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.selected = true;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
