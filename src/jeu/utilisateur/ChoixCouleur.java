package jeu.utilisateur;

import jeu.muffinattacks.Couleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Jicé on 06/04/2014.
 */
public class ChoixCouleur extends JPanel {
    private static final int ESPACEMENT = 20;
    private Couleur couleurs;
    private JLabel jlabel;
    private boolean selected;

    public ChoixCouleur(Couleur c) {
        this.couleurs = c;
        this.selected = false;

        this.setLayout(new BorderLayout());

        jlabel = new JLabel("Aa");
        jlabel.setHorizontalAlignment(0); //centrage du text
        jlabel.setForeground(c.getCouleurTexte());
        jlabel.setFont(new java.awt.Font("Arial", Font.BOLD, 60));

        this.add(jlabel, BorderLayout.CENTER);

        this.addMouseListener(new EcouteurSouris(this));
        this.setBackground(c.getCouleurFond());
    }

    public void overlay() {
        this.selected = !selected;
        if (selected) {
            this.setBorder(BorderFactory.
                    createCompoundBorder(BorderFactory.createLineBorder(couleurs.getCouleurFond(), 5),
                            BorderFactory.createLineBorder(couleurs.getCouleurTexte(), 20)));
        } else {
            this.setBorder(null);
        }
    }


    private class EcouteurSouris implements MouseListener {
        private ChoixCouleur cc;

        public EcouteurSouris(ChoixCouleur cc) {
            this.cc = cc;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            cc.overlay();
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

}
