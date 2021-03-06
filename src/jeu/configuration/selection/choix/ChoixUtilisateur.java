package jeu.configuration.selection.choix;

import devintAPI.Preferences;
import jeu.MenuJeu;
import jeu.configuration.selection.SelectionUtilisateur;
import jeu.global.ChargerAvatar;
import jeu.global.Utilisateur;
import jeu.global.voix.Voix;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Jean-Christophe Isoard
 */
public class ChoixUtilisateur extends Choix<Utilisateur> {
    /**
     * Instancie un choix avec pour paramètre son objet choisi
     * @param o         L'objet choisi
     */
    public ChoixUtilisateur(SelectionUtilisateur s, Utilisateur o) {
        super(s, o);
    }

    @Override
    public void init() {
        Utilisateur u = getObjetChoix();
        JLabel labelNom = new JLabel(u.getNom());
        JLabel labelIcone = new JLabel(ChargerAvatar.charger(u.getIcone(), 200, 200), SwingConstants.CENTER);
        this.add(labelNom, BorderLayout.NORTH);
        this.add(labelIcone, BorderLayout.CENTER);
    }

    @Override
    public void refresh() {
        if(isSelected()) {
            Voix.dire(getObjetChoix().getIcone());
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            this.setBorder(null);
        }
    }
}
