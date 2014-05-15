/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.sauvegarde;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import jeu.global.Utilisateur;
import jeu.global.couleurs.Couleurs;
import jeu.global.difficultes.Niveau;
import static jeu.sauvegarde.Config.*;
import org.jdom2.*;
import org.jdom2.output.*;

/**
 *
 * @author Thomas
 * @version 1.0
 */
public class Sauvegarde {

    

    /**
     * Un main juste pour tester l'enregistrement. A supprimer plus tard
     *
     * @param args
     */
    public static void main(String[] args) {
        HashMap<String, Utilisateur> test = new HashMap<>();
        HashMap<Niveau, Integer> scores = new HashMap<>();
        scores.put(Niveau.UN, 12);
        Utilisateur Thomas = new Utilisateur("Thomas");
        Collection<Couleurs> couleur = new ArrayList<>();
        couleur.add(Couleurs.JAUNENOIR);
        couleur.add(Couleurs.BEIGEBLEU);
        Thomas.setMeilleursScores(scores);
        Thomas.setCouleursPreferees(couleur);
        Utilisateur Maylanie = new Utilisateur("Maylanie");
        Collection<Couleurs> couleur1 = new ArrayList<>();
        couleur1.add(Couleurs.BEIGEMARRON);
        couleur1.add(Couleurs.NOIRBLANC);
        Maylanie.setCouleursPreferees(couleur1);
        test.put("Thomas", Thomas);
        test.put("Maylanie", Maylanie);
        saveUsers(test);
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
            Element userName = new Element(user.toString());
            RACINE_UTILISATEURS.addContent(userName);
            @SuppressWarnings("element-type-mismatch")
            Element couleur = saveColors(users.get(user));
            userName.addContent(couleur);
            Element score = saveScores(users.get(user));
            userName.addContent(score);
        }
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(UTILISATEUR, new FileOutputStream(FILE_NAME));
        } catch (IOException ex) {
        }
    }

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

    public static Element saveScores(Utilisateur user) {
        Element score = new Element(SCORE_UTILISATEUR);
        Map<Niveau,Integer> scoreASauvegarder = user.getMeilleursScores();
        Set key = scoreASauvegarder.keySet();
        Iterator it = key.iterator();
        while (it.hasNext()) {
            Object niv = it.next();
            Element niveau = new Element(((Niveau)niv).getName());
            niveau.setAttribute(SCORE_VALUE, Integer.toString(scoreASauvegarder.get(niv)));
            score.addContent(niveau);
        }
        return score;
    }
}
