package jeu.global.couleurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Jicé on 06/04/2014.
 */
public class ChoixCouleurs extends JPanel {
    private static final int ESPACEMENT = 20;
    private Couleurs couleurs;
    private JLabel jlabel;
    private boolean selected;

    public ChoixCouleurs(Couleurs c) {
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
        refresh();
    }

    public void refresh() {
        if (selected) {
            this.setBorder(BorderFactory.
                    createCompoundBorder(BorderFactory.createLineBorder(couleurs.getCouleurFond(), 5),
                            BorderFactory.createLineBorder(couleurs.getCouleurTexte(), 20)));
        } else {
            this.setBorder(null);
        }
    }

    public Couleurs getCouleurs() {
        return couleurs;
    }

    public boolean isCouleurs(Couleurs couleurs) {
        return this.couleurs.equals(couleurs);
    }

    public void setSelected() {
        this.selected = true;
        refresh();
    }

    public boolean isSelected() {
        return selected;
    }

    public Couleurs getCouleur() {
        return couleurs;
    }


    private class EcouteurSouris implements MouseListener {
        private ChoixCouleurs cc;

        public EcouteurSouris(ChoixCouleurs cc) {
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
