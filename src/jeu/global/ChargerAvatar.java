package jeu.global;

import devintAPI.Preferences;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jean-Christophe Isoard
 */
public class ChargerAvatar {
    public static ImageIcon charger(String nom) {
        String lien = Preferences.getIconsdirectory() + nom + ".png";
        BufferedImage icon = null;
        boolean chargee = false;
        try {
            icon = ImageIO.read(new File(lien));
            if (icon != null) {
                chargee = true;
            } else {
                System.out.println("Image illisible !\n" + lien);
            }
        } catch (IOException e) {
            System.out.println("Erreur à la lecture de "+lien);
        }
        if (chargee)
            return new ImageIcon(icon);
        return new ImageIcon();
    }

    public static Collection<String> recupererListeFichiers() {
        File repertoire = new File(Preferences.getIconsdirectory());
        if (!repertoire.isDirectory()) return null;
        String[] listeFichiers = repertoire.list();
        if (listeFichiers.length == 0)
            return null;
        Collection<String> listeFichiersIcones = new ArrayList<>();
        for (String fichier : listeFichiers) {
            if (fichier.contains(".png")) {
                listeFichiersIcones.add(fichier.replaceAll(".png",""));
            }
        }
        return listeFichiersIcones;
    }
}
