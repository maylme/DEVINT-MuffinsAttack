/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.sauvegarde;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jeu.global.Utilisateur;
import jeu.global.couleurs.Couleurs;
import jeu.global.difficultes.Niveau;
import static jeu.sauvegarde.Config.*;
import static jeu.sauvegarde.Restauration.restoreUsers;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.*;

/**
 *
 * @author Thomas
 * @version 1.0
 */
public class Sauvegarde {

    private static Document document;
    private static Element racine;

    /**
     * Un main juste pour tester l'enregistrement. A supprimer plus tard
     *
     * @param args
     */
    public static void main(String[] args) {
        HashMap<String, Utilisateur> test = new HashMap<>();
        Utilisateur Thomas = new Utilisateur("Tom");
        Collection<Couleurs> couleur = new ArrayList<>();
        couleur.add(Couleurs.JAUNENOIR);
        couleur.add(Couleurs.BEIGEBLEU);
        Thomas.setMeilleurScore(Niveau.UN, 12);
        Thomas.setCouleursPreferees(couleur);
        Utilisateur Maylanie = new Utilisateur("Mayl");
        Collection<Couleurs> couleur1 = new ArrayList<>();
        couleur1.add(Couleurs.BEIGEMARRON);
        couleur1.add(Couleurs.NOIRBLANC);
        Maylanie.setCouleursPreferees(couleur1);
        test.put("Thomas", Thomas);
        test.put("Maylanie", Maylanie);
        saveUsers(test);
        Thomas.setMeilleurScore(Niveau.DEUX, 50);
        saveUser(Thomas);
        saveUser(Maylanie);
    }

    /**
     * Fonction principale de sauvegarde. Liste les utilisateurs et recherche
     * leurs préférences de couleurs avec saveColors(utilisateur) et leur
     * meilleur score avec saveScore(utilisateur)
     *
     * @param users
     */
    public static void saveUsers(HashMap<String, Utilisateur> users) {
        Set key = users.keySet();
        Iterator it = key.iterator();

        while (it.hasNext()) {
            Object user = it.next();
            Element userName = new Element(users.get(user).getIcone());
            RACINE_UTILISATEURS.addContent(userName);
            @SuppressWarnings("element-type-mismatch")
            Element couleur = saveColors(users.get(user));
            userName.addContent(couleur);
            Element score = saveScores(users.get(user));
            userName.addContent(score);
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
