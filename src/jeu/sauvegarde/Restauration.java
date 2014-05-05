/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.sauvegarde;

import java.io.IOException;
import java.util.*;
import jeu.global.Utilisateur;
import jeu.global.couleurs.Couleurs;
import org.jdom2.*;
import org.jdom2.input.*;

/**
 *
 * @author Thomas
 */
public class Restauration {

    public static void main(String[] args) {
        HashMap<String, Utilisateur> test = restoreUsers();
    }

    /**
     * Récupération de l'ensemble des utilisateurs sauvegardés.
     *
     * @return HashMap contenant l'ensemble des utilisateurs
     */
    public static HashMap<String, Utilisateur> restoreUsers() {
        HashMap<String, Utilisateur> users = new HashMap<>();
        SAXBuilder sxb = new SAXBuilder();
        try {
            Document document = sxb.build(Config.FILE_NAME);
            Element racine = document.getRootElement();
            for (Element elem : racine.getChildren()) {
                Utilisateur current = new Utilisateur(elem.getName());
                current.setCouleursPreferees(restoreColors(elem));
                users.put(elem.getName(), current);
            }
        } catch (JDOMException | IOException ex) {
        }
        return users;
    }

    /**
     * Retourne l'ensemble des couleurs préférées de l'utilisateur passé en
     * paramètre. Contrôle si la couleur existe dans l'enum listant les
     * couleurs.
     *
     * @param user
     * @return Collection content les couleurs préférées de l'utilisateur
     */
    public static Collection<Couleurs> restoreColors(Element user) {
        Collection<Couleurs> couleur = new ArrayList<>();
        for (Element tmp : user.getChildren()) {
            for (Element colorTmp : tmp.getChildren()) {
                for (Couleurs color : Couleurs.values()) {
                    if (colorTmp.getName().equals(color.toString())) {
                        couleur.add(color);
                        break;
                    }
                }
            }
        }
        return couleur;
    }
}
