/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.sauvegarde;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jeu.global.Utilisateur;
import jeu.global.difficultes.Niveau;
import static jeu.sauvegarde.Config.COULEUR_PREFEREE;
import static jeu.sauvegarde.Config.COULEUR_UTILISATEUR;
import static jeu.sauvegarde.Config.FILE_NAME;
import static jeu.sauvegarde.Config.SCORE_UTILISATEUR;
import static jeu.sauvegarde.Config.SCORE_VALUE;
import static jeu.sauvegarde.Config.UTILISATEUR;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Thomas
 * @version 1.0
 */
public class Sauvegarde {

    private static Document document;
    private static Element racine;

    /**
     * Fonction principale de sauvegarde. Liste les utilisateurs et recherche
     * leurs préférences de couleurs avec saveColors(utilisateur) et leur
     * meilleur score avec saveScore(utilisateur)
     *
     * @param users
     */
    public static void saveUsers(Map<String, Utilisateur> users) {
        Set key = users.keySet();
        Iterator it = key.iterator();

        while (it.hasNext()) {
            Object user = it.next();
            saveUser(users.get(user));
        }
        try {
            enregistreFichier(FILE_NAME);
        } catch (Exception ex) {
            Logger.getLogger(Sauvegarde.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permet de sauvegarder un utilisateur.
     *
     * @param user l'utilisateur à sauvegarder
     */
    public static void saveUser(Utilisateur user) {
        SAXBuilder sxb = new SAXBuilder();
        try {
            lireFichier(FILE_NAME);
            List listEtudiant = racine.getChildren();
            Iterator i = listEtudiant.iterator();
            while (i.hasNext()) {
                Element courant = (Element) i.next();
                if (courant.getName().equals(user.getIcone())) {
                    racine.removeChild(courant.getName());
                }
            }
            Element userName = new Element(user.getIcone());
            racine.addContent(userName);
            Element couleur = saveColors(user);
            userName.addContent(couleur);
            Element score = saveScores(user);
            userName.addContent(score);
            enregistreFichier(FILE_NAME);
        } catch (Exception e) {
        }
    }

    /**
     * Sauvegarde les préférence de couleurs associées à l'utilisateur
     *
     * @param user
     * @return un Element contenant les couleurs préférées
     */
    public static Element saveColors(Utilisateur user) {
        Element couleur = new Element(COULEUR_UTILISATEUR);
        couleur.setAttribute(COULEUR_PREFEREE, Integer.toString(user.getCouleursPreferee()));
        int i = 0;
        do {
            Element nameColor = new Element(user.getCouleursChoisies().toString());
            user.couleursSuivantes();
            couleur.addContent(nameColor);
            i++;
        } while (i < user.getCouleursPreferees().size());
        return couleur;
    }

    /**
     * Sauvegarde le meilleur score par niveau de l'utilisateur.
     *
     * @param user
     * @return un Element contenant les meilleurs scores de l'utilisateur
     */
    public static Element saveScores(Utilisateur user) {
        Element score = new Element(SCORE_UTILISATEUR);
        Map<Niveau, Integer> scoreASauvegarder = user.getMeilleursScores();
        Set key = scoreASauvegarder.keySet();
        Iterator it = key.iterator();
        while (it.hasNext()) {
            Object niv = it.next();
            Element niveau = new Element(((Niveau) niv).getName());
            niveau.setAttribute(SCORE_VALUE, Integer.toString(scoreASauvegarder.get(niv)));
            score.addContent(niveau);
        }
        return score;
    }

    public static void lireFichier(String fichier) throws Exception {
        SAXBuilder sxb = new SAXBuilder();
        document = Config.UTILISATEUR;
        racine = document.getRootElement();
    }

    public static void enregistreFichier(String fichier) throws Exception {
        XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
        sortie.output(UTILISATEUR, new FileOutputStream(FILE_NAME));
    }
}
