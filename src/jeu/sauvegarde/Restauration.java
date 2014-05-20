/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.sauvegarde;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import jeu.global.Utilisateur;
import jeu.global.couleurs.Couleurs;
import jeu.global.difficultes.Niveau;
import static jeu.sauvegarde.Config.COULEUR_PREFEREE;
import static jeu.sauvegarde.Config.COULEUR_UTILISATEUR;
import static jeu.sauvegarde.Config.NIVEAU_EN_COURS;
import static jeu.sauvegarde.Config.SCORE_UTILISATEUR;
import static jeu.sauvegarde.Config.SCORE_VALUE;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Thomas
 */
public class Restauration {

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
                current.setMeilleursScores(restoreScores(elem));
                current.setCouleursPreferee(Integer.parseInt(elem.getChildren(COULEUR_UTILISATEUR).get(0).getAttribute(COULEUR_PREFEREE).getValue()));
                current.setNiveau(Niveau.getById(Integer.parseInt(elem.getChildren(SCORE_UTILISATEUR).get(0).getAttribute(NIVEAU_EN_COURS).getValue())));
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
     * @return Collection contenant les couleurs préférées de l'utilisateur
     */
    public static Collection<Couleurs> restoreColors(Element user) {
        Collection<Couleurs> couleur = new ArrayList<>();
        for (Element tmp : user.getChildren(COULEUR_UTILISATEUR)) {
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

    /**
     * Retourne l'ensemble des meilleurs scores de l'utilisateur. Le meilleur
     * score par niveau.
     *
     * @param user
     * @return HashMap contenant le meilleur score par niveau
     */
    public static HashMap<Niveau, Integer> restoreScores(Element user) {
        HashMap<Niveau, Integer> scores = new HashMap<>();
        for (Element tmp : user.getChildren()) {
            if (tmp.getName().equals(SCORE_UTILISATEUR)) {
                for (Element scoreTmp : tmp.getChildren()) {
                    for (Niveau niv : Niveau.values()) {
                        if (scoreTmp.getName().equals(niv.getName())) {
                            scores.put(niv, Integer.parseInt(scoreTmp.getAttribute(SCORE_VALUE).getValue()));
                        }
                    }
                }
            }
        }
        return scores;
    }

    public static Niveau restoreNiveau(Element user) {
        for (Niveau niv : Niveau.values()) {
            if (user.getChildren(SCORE_UTILISATEUR).get(0).getAttribute(NIVEAU_EN_COURS).getValue().equals(niv.getName())){
                return niv;
            }
        }
        return Niveau.UN;
    }
}
